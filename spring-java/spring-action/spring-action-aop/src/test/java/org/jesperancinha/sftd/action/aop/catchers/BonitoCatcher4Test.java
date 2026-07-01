package org.jesperancinha.sftd.action.aop.catchers;

import org.aspectj.lang.JoinPoint;
import org.jesperancinha.sftd.action.aop.aspects.BonitoAspect4;
import org.jesperancinha.sftd.action.aop.beans.Bonito4Service;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith({SpringExtension.class})
@ContextConfiguration(classes = {
        BonitoCatcher.class,
        BonitoAspect4.class
})
@ImportResource("classpath:bean.xml")
@EnableAspectJAutoProxy(proxyTargetClass = true)
class BonitoCatcher4Test {

    @MockitoBean
    private Bonito4Service bonito4Service;

    @Autowired
    private BonitoCatcher bonitoCatcher;

    @Captor
    private ArgumentCaptor<JoinPoint> joinPointArgumentCaptor;

    @Test
    void testCatchWithNetwhenCalledThenTriggerAllAdvices() {
        bonitoCatcher.catchWithNet();

        verify(bonito4Service, only()).beforeAnyCatch(joinPointArgumentCaptor.capture());
        final var joinPointArgumentCaptorValue = joinPointArgumentCaptor.getValue();
        assertThat(joinPointArgumentCaptorValue.getSignature().toString()).isEqualTo("Bonito org.jesperancinha.sftd.action.aop.catchers.BonitoCatcher.catchWithNet()");
    }

    @Test
    void testCatchWithFishingPoleWhenCalledThenTriggerAllAdvices() {
        bonitoCatcher.catchWithFishingPole();

        verify(bonito4Service, only()).beforeAnyCatch(joinPointArgumentCaptor.capture());
        final var joinPointArgumentCaptorValue = joinPointArgumentCaptor.getValue();
        assertThat(joinPointArgumentCaptorValue.getSignature().toString()).isEqualTo("Bonito org.jesperancinha.sftd.action.aop.catchers.BonitoCatcher.catchWithFishingPole()");
    }

    @Test
    void testCatchByHandWhenCalledThenTriggerAllAdvices() {
        bonitoCatcher.catchByHand();

        verify(bonito4Service, only()).beforeAnyCatch(joinPointArgumentCaptor.capture());
        final var joinPointArgumentCaptorValue = joinPointArgumentCaptor.getValue();
        assertThat(joinPointArgumentCaptorValue.getSignature().toString()).isEqualTo("Bonito org.jesperancinha.sftd.action.aop.catchers.BonitoCatcher.catchByHand()");
    }

    @Test
    void testCatchByHandExtraWhenCalledThenTriggerAllAdvices() {
        bonitoCatcher.catchByHandExtra();

        verify(bonito4Service, only()).beforeAnyCatch(joinPointArgumentCaptor.capture());
        final var joinPointArgumentCaptorValue = joinPointArgumentCaptor.getValue();
        assertThat(joinPointArgumentCaptorValue.getSignature().toString()).isEqualTo("void org.jesperancinha.sftd.action.aop.catchers.BonitoCatcher.catchByHandExtra()");
    }

    @Test
    void testCatchWithClawsWhenCalledThenTriggerAllAdvices() {
        bonitoCatcher.catchWithClaws();

        verify(bonito4Service, times(1)).beforeAnyCatch(joinPointArgumentCaptor.capture());
        verify(bonito4Service, times(1)).waitPrivatelyForFishCatch(joinPointArgumentCaptor.capture());
        final var allValues = joinPointArgumentCaptor.getAllValues();
        assertThat(allValues).hasSize(2);
        assertThat(allValues.stream()
                .map(JoinPoint::getSignature)
                .map(Object::toString))
                .contains("Bonito org.jesperancinha.sftd.action.aop.catchers.BonitoCatcher.catchWithClaws()", "Bonito org.jesperancinha.sftd.action.aop.catchers.BonitoCatcher.catchWithClaws()");
    }

    @Test
    void testCatchWithSuperSonicWavesWhenCalledThenTriggerAllAdvices() {
        bonitoCatcher.catchWithSuperSonicWaves();

        verify(bonito4Service, only()).beforeAnyCatch(joinPointArgumentCaptor.capture());
        final var joinPointArgumentCaptorValue = joinPointArgumentCaptor.getValue();
        assertThat(joinPointArgumentCaptorValue.getSignature().toString()).isEqualTo("Bonito org.jesperancinha.sftd.action.aop.catchers.BonitoCatcher.catchWithSuperSonicWaves()");
    }

    @Test
    void testCatchWithABucketWhenCalledThenTriggerAllAdvices() {
        bonitoCatcher.catchWithABucket();

        verify(bonito4Service, only()).beforeAnyCatch(joinPointArgumentCaptor.capture());
        final var joinPointArgumentCaptorValue = joinPointArgumentCaptor.getValue();
        assertThat(joinPointArgumentCaptorValue.getSignature().toString()).isEqualTo("Bonito org.jesperancinha.sftd.action.aop.catchers.BonitoCatcher.catchWithABucket()");
    }

    @Test
    void testCatchWithLoveWhenCalledThenTriggerAllAdvices() {
        bonitoCatcher.catchWithLove();

        verify(bonito4Service, only()).beforeAnyCatch(joinPointArgumentCaptor.capture());
        final var joinPointArgumentCaptorValue = joinPointArgumentCaptor.getValue();
        assertThat(joinPointArgumentCaptorValue.getSignature().toString()).isEqualTo("Bonito org.jesperancinha.sftd.action.aop.catchers.BonitoCatcher.catchWithLove()");
    }
}