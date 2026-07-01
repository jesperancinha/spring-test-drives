package org.jesperancinha.sftd.action.aop.catchers;

import org.aspectj.lang.JoinPoint;
import org.jesperancinha.sftd.action.aop.aspects.BonitoAspect2;
import org.jesperancinha.sftd.action.aop.beans.Bonito2Service;
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
        BonitoCatcher.class,
        BonitoAspect2.class
})
@EnableAspectJAutoProxy(proxyTargetClass = true)
class BonitoCatcherTest {

    @MockitoBean
    private Bonito2Service bonito2Service;

    @Autowired
    private BonitoCatcher bonitoCatcher;

    @Captor
    private ArgumentCaptor<JoinPoint> joinPointArgumentCaptor;

    @Test
    void testCatchWithNetWhenCalledThenTriggerAllAdvices() {
        bonitoCatcher.catchWithNet();

        verify(bonito2Service, only()).beforeAnyCatch(joinPointArgumentCaptor.capture());
        final var joinPointArgumentCaptorValue = joinPointArgumentCaptor.getValue();
        assertThat(joinPointArgumentCaptorValue.getSignature().toString()).isEqualTo("Bonito org.jesperancinha.sftd.action.aop.catchers.BonitoCatcher.catchWithNet()");
    }

    @Test
    void testCatchWithFishingPoleWhenCalledThenTriggerAllAdvices() {
        bonitoCatcher.catchWithFishingPole();

        verify(bonito2Service, only()).beforeAnyCatch(joinPointArgumentCaptor.capture());
        final var joinPointArgumentCaptorValue = joinPointArgumentCaptor.getValue();
        assertThat(joinPointArgumentCaptorValue.getSignature().toString()).isEqualTo("Bonito org.jesperancinha.sftd.action.aop.catchers.BonitoCatcher.catchWithFishingPole()");
    }

    @Test
    void testCatchByHandWhenCalledThenTriggerAllAdvices() {
        bonitoCatcher.catchByHand();

        verify(bonito2Service, only()).beforeAnyCatch(joinPointArgumentCaptor.capture());
        final var joinPointArgumentCaptorValue = joinPointArgumentCaptor.getValue();
        assertThat(joinPointArgumentCaptorValue.getSignature().toString()).isEqualTo("Bonito org.jesperancinha.sftd.action.aop.catchers.BonitoCatcher.catchByHand()");
    }

    @Test
    void testCatchByHandExtraWhenCalledThenTriggerAllAdvices() {
        bonitoCatcher.catchByHandExtra();

        verify(bonito2Service, only()).beforeAnyCatch(joinPointArgumentCaptor.capture());
        final var joinPointArgumentCaptorValue = joinPointArgumentCaptor.getValue();
        assertThat(joinPointArgumentCaptorValue.getSignature().toString()).isEqualTo("void org.jesperancinha.sftd.action.aop.catchers.BonitoCatcher.catchByHandExtra()");
    }

    @Test
    void testCatchWithClawsWhenCalledThenTriggerAllAdvices() {
        bonitoCatcher.catchWithClaws();

        verify(bonito2Service, times(1)).beforeAnyCatch(joinPointArgumentCaptor.capture());
        verify(bonito2Service, times(1)).waitForFishCatch(joinPointArgumentCaptor.capture());
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

        verify(bonito2Service, only()).beforeAnyCatch(joinPointArgumentCaptor.capture());
        final var joinPointArgumentCaptorValue = joinPointArgumentCaptor.getValue();
        assertThat(joinPointArgumentCaptorValue.getSignature().toString()).isEqualTo("Bonito org.jesperancinha.sftd.action.aop.catchers.BonitoCatcher.catchWithSuperSonicWaves()");
    }

    @Test
    void testCatchWithABucketWhenCalledThenTriggerAllAdvices() {
        bonitoCatcher.catchWithABucket();

        verify(bonito2Service, only()).beforeAnyCatch(joinPointArgumentCaptor.capture());
        final var joinPointArgumentCaptorValue = joinPointArgumentCaptor.getValue();
        assertThat(joinPointArgumentCaptorValue.getSignature().toString()).isEqualTo("Bonito org.jesperancinha.sftd.action.aop.catchers.BonitoCatcher.catchWithABucket()");
    }

    @Test
    void testCatchWithLoveWhenCalledThenTriggerAllAdvices() {
        bonitoCatcher.catchWithLove();

        verify(bonito2Service, only()).beforeAnyCatch(joinPointArgumentCaptor.capture());
        final var joinPointArgumentCaptorValue = joinPointArgumentCaptor.getValue();
        assertThat(joinPointArgumentCaptorValue.getSignature().toString()).isEqualTo("Bonito org.jesperancinha.sftd.action.aop.catchers.BonitoCatcher.catchWithLove()");
    }
}