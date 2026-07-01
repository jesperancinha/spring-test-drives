package org.jesperancinha.sftd.action.aop.catchers;

import org.aspectj.lang.JoinPoint;
import org.jesperancinha.sftd.action.aop.aspects.*;
import org.jesperancinha.sftd.action.aop.beans.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


@ExtendWith({SpringExtension.class})
@ContextConfiguration(classes = {
        GambaFoodCatcher.class,
        BonitoCatcher.class,
        MackerelCatcher.class,
        MegaTunaCatcher.class,
        SardineCatcher.class,
        ShrimpCatcher.class,
        TunaCatcher.class,
        GambaAspect.class,
        MasterAspect.class,
        BonitoAspect.class,
        BonitoAspect2.class,
        BonitoAspect3.class,
        BonitoAspect4.class
})
@EnableAspectJAutoProxy(proxyTargetClass = true)
class GambaFoodMasterCatcherTest {

    @MockitoBean
    private GambaService gambaService;

    @MockitoBean
    private MasterService masterService;

    @MockitoBean
    private TunaService tunaService;

    @MockitoBean
    private CodService codService;

    @MockitoBean
    private Bonito1Service bonito1Service;

    @MockitoBean
    private Bonito2Service bonito2Service;

    @MockitoBean
    private Bonito3Service bonito3Service;

    @MockitoBean
    private Bonito4Service bonito4Service;

    @Autowired
    private GambaFoodCatcher gambaFoodCatcher;

    @Autowired
    private BonitoCatcher bonitoCatcher;

    @Autowired
    private MackerelCatcher mackerelCatcher;

    @Autowired
    private MegaTunaCatcher megaTunaCatcher;

    @Autowired
    private SardineCatcher sardineCatcher;

    @Autowired
    private ShrimpCatcher shrimpCatcher;

    @Autowired
    private TunaCatcher tunaCatcher;

    @Captor
    private ArgumentCaptor<JoinPoint> joinPointArgumentCaptor;

    @Test
    void testCatchWithNetWhenCallingMasterAnnotatedMethodThenTriggerTheRightAdvices() {
        gambaFoodCatcher.catchWithNet();
        bonitoCatcher.catchWithNet();
        mackerelCatcher.catchWithNet();
        megaTunaCatcher.catchWithNet();
        sardineCatcher.catchWithNet();
        shrimpCatcher.catchWithNet();
        tunaCatcher.catchWithNet();

        verify(gambaService, times(1)).beforeWithin(joinPointArgumentCaptor.capture());
        final var joinPointArgumentCaptorValue = joinPointArgumentCaptor.getValue();
        assertThat(joinPointArgumentCaptorValue.getSignature().toString())
                .isEqualTo("Gamba org.jesperancinha.sftd.action.aop.catchers.GambaFoodCatcher.catchWithNet()");
        verify(gambaService, times(1)).beforeWithinAnnotation(joinPointArgumentCaptor.capture());
        final var joinPointArgumentCaptorValue2 = joinPointArgumentCaptor.getValue();
        assertThat(joinPointArgumentCaptorValue2.getSignature().toString())
                .isEqualTo("Gamba org.jesperancinha.sftd.action.aop.catchers.GambaFoodCatcher.catchWithNet()");
        verify(masterService, only()).masterize(joinPointArgumentCaptor.capture());
        final var joinPointArgumentCaptorValue1 = joinPointArgumentCaptor.getValue();
        assertThat(joinPointArgumentCaptorValue1.getSignature().toString())
                .isEqualTo("Gamba org.jesperancinha.sftd.action.aop.catchers.GambaFoodCatcher.catchWithNet()");
        verifyNoInteractions(tunaService);
        verifyNoInteractions(codService);
        verifyNoInteractions(bonito4Service);
        verifyNoInteractions(bonito3Service);
        verify(bonito2Service, atLeast(1)).beforeAnyCatch(joinPointArgumentCaptor.capture());
        final var allValuesForBonito2Service = joinPointArgumentCaptor.getAllValues();
        IntStream.range(3, allValuesForBonito2Service.size() - 1)
                .forEach(i -> assertThat(joinPointArgumentCaptorValue1.getSignature().toString())
                        .isNotEqualTo("Gamba org.jesperancinha.sftd.action.aop.catchers.GambaFoodCatcher.catchWithNet()"));
        verifyNoInteractions(bonito1Service);
    }
}