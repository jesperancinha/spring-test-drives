package org.jesperancinha.sftd.flash37.aop.detail.service;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.jesperancinha.sftd.flash37.aop.detail.aspects.TicketAspectAfter;
import org.jesperancinha.sftd.flash37.aop.detail.aspects.TicketAspectAround;
import org.jesperancinha.sftd.flash37.aop.detail.aspects.TicketAspectBefore;
import org.jesperancinha.sftd.flash37.aop.detail.beans.TicketAfterBean;
import org.jesperancinha.sftd.flash37.aop.detail.beans.TicketAroundBean;
import org.jesperancinha.sftd.flash37.aop.detail.beans.TicketBeforeBean;
import org.jesperancinha.sftd.flash37.aop.detail.configuration.TicketConfiguration;
import org.jesperancinha.sftd.flash37.aop.detail.dto.TicketDto;
import org.jesperancinha.sftd.flash37.aop.detail.model.Ticket;
import org.jesperancinha.sftd.flash37.aop.detail.repository.TicketRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        TicketConfiguration.class, TicketService.class,
        TicketAspectAfter.class, TicketAspectAround.class, TicketAspectBefore.class,
})
public class TicketServiceTest {

    @Autowired
    private TicketService ticketService;

    @MockitoBean
    private TicketRepository ticketRepository;

    @MockitoBean
    private TicketAfterBean ticketAfterBean;

    @MockitoBean
    private TicketAroundBean ticketAroundBean;

    @MockitoBean
    private TicketBeforeBean ticketBeforeBean;

    @Captor
    private ArgumentCaptor<JoinPoint> joinPointArgumentCaptor;

    @Captor
    private ArgumentCaptor<ProceedingJoinPoint> proceedingJoinPointArgumentCaptor;

    /**
     * In this unit test, only the around and after advices are executed by the aspect at the respective cutpoints.
     * None of the before's get executed.
     */
    @Test
    public void testCreateTicket() {
        when(ticketRepository.save(any(Ticket.class))).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        final var ticketDto = TicketDto
                .builder()
                .id(1L)
                .artist("Placebo")
                .show("Bearded Theory's Spring Gathering 2022")
                .uuid(UUID.randomUUID())
                .build();
        ticketService.createTicket(ticketDto);

        verify(ticketBeforeBean, never()).logBeforeTicket(joinPointArgumentCaptor.capture());
        verify(ticketBeforeBean, never()).logBeforeTicketNoAround(joinPointArgumentCaptor.capture());
        assertThat(joinPointArgumentCaptor.getAllValues()).hasSize(0);
        verify(ticketAroundBean, times(1)).aroundTicket(proceedingJoinPointArgumentCaptor.capture());
        assertThat(proceedingJoinPointArgumentCaptor.getAllValues()).hasSize(1);
        final var proceedingJoinPointArgumentCaptorValue = proceedingJoinPointArgumentCaptor.getValue();
        assertThat(proceedingJoinPointArgumentCaptorValue.getArgs()[0]).isSameAs(ticketDto);
        verify(ticketAfterBean, times(1)).logAfterTicket(joinPointArgumentCaptor.capture());
        assertThat(joinPointArgumentCaptor.getAllValues()).hasSize(1);
        final var joinPointArgumentCaptorValue = joinPointArgumentCaptor.getValue();
        assertThat(joinPointArgumentCaptorValue.getArgs()[0]).isSameAs(ticketDto);
        verify(ticketAfterBean, never()).logAfterTicketNoAround(joinPointArgumentCaptor.capture());
    }

    /**
     * In this uni test, we are testing that both the before and after advices are executed by the aspect since there is no overlap
     */
    @Test
    public void testCreateTicketNoAround() {
        when(ticketRepository.save(any(Ticket.class))).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        final var ticketDto = TicketDto
                .builder()
                .id(1L)
                .artist("Placebo")
                .show("Bearded Theory's Spring Gathering 2022")
                .uuid(UUID.randomUUID())
                .build();
        ticketService.createTicketNoAround(ticketDto);

        verify(ticketBeforeBean, times(1)).logBeforeTicketNoAround(joinPointArgumentCaptor.capture());
        assertThat(joinPointArgumentCaptor.getAllValues()).hasSize(1);
        final var joinPointArgumentCaptorValue = joinPointArgumentCaptor.getValue();
        assertThat(joinPointArgumentCaptorValue.getArgs()[0]).isSameAs(ticketDto);
        verify(ticketBeforeBean, never()).logBeforeTicket(joinPointArgumentCaptor.capture());
        verify(ticketAroundBean, never()).aroundTicket(proceedingJoinPointArgumentCaptor.capture());
        verify(ticketAfterBean, times(1)).logAfterTicketNoAround(joinPointArgumentCaptor.capture());
        assertThat(joinPointArgumentCaptor.getAllValues()).hasSize(2);
        final var joinPointArgumentAfterCaptorValue = joinPointArgumentCaptor.getValue();
        assertThat(joinPointArgumentAfterCaptorValue.getArgs()[0]).isSameAs(ticketDto);
        verify(ticketAfterBean, never()).logAfterTicket(joinPointArgumentCaptor.capture());

    }
}