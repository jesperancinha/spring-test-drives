package org.jesperancinha.sftd.action.aop.aspects;

import org.aspectj.lang.JoinPoint;
import org.jesperancinha.sftd.action.aop.beans.OysterService;
import org.jesperancinha.sftd.action.aop.model.Oyster;
import org.jesperancinha.sftd.action.aop.pickers.OysterPicker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith({SpringExtension.class})
@ContextConfiguration(classes = {
        OysterAspect.class,
        OysterService.class,
        OysterPicker.class,
})
@EnableAspectJAutoProxy(proxyTargetClass = true)
class OysterAspectTest {

    @Autowired
    private OysterPicker oysterPicker;

    @MockitoBean
    private OysterService oysterService;

    @Captor
    private ArgumentCaptor<JoinPoint> joinPointArgumentCaptor;

    @Captor
    private ArgumentCaptor<Oyster> oysterArgumentCaptor;

    @Test
    void testOysterProcessing() {
        final var oyster = new Oyster();

        final var pickup = oysterPicker.pickup(oyster);

        assertThat(pickup).isEqualTo(oyster);
        verify(oysterService, only())
                .oysterProcessing(
                        joinPointArgumentCaptor.capture(), oysterArgumentCaptor.capture());
        final var joinPointArgumentCaptorValue = joinPointArgumentCaptor.getValue();
        assertThat(joinPointArgumentCaptorValue).isNotNull();
        assertThat(joinPointArgumentCaptorValue.getSignature().toString())
                .isEqualTo("Oyster org.jesperancinha.sftd.action.aop.pickers.OysterPicker.pickup(Oyster)");
        final var value = oysterArgumentCaptor.getValue();
        assertThat(value).isNotNull();
        verifyNoMoreInteractions(oysterService);
    }

    @Test
    void testOysterQualityProcessing() {
        final var oyster = new Oyster();

        final var pickup = oysterPicker.pickWithQuality(oyster);

        assertThat(pickup).isEqualTo(oyster);
        verify(oysterService, only())
                .oysterProcessing(
                        joinPointArgumentCaptor.capture(), oysterArgumentCaptor.capture());
        final var joinPointArgumentCaptorValue = joinPointArgumentCaptor.getValue();
        assertThat(joinPointArgumentCaptorValue).isNotNull();
        assertThat(joinPointArgumentCaptorValue.getSignature().toString())
                .isEqualTo("Oyster org.jesperancinha.sftd.action.aop.pickers.OysterPicker.pickWithQuality(Oyster)");
        final var value = oysterArgumentCaptor.getValue();
        assertThat(value).isNotNull();
        verifyNoMoreInteractions(oysterService);
    }
}