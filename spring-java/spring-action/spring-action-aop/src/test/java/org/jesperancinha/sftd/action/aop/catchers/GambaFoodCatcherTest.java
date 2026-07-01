package org.jesperancinha.sftd.action.aop.catchers;

import org.aspectj.lang.JoinPoint;
import org.jesperancinha.sftd.action.aop.aspects.GambaAspect;
import org.jesperancinha.sftd.action.aop.beans.GambaService;
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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith({SpringExtension.class})
@ContextConfiguration(classes = {
        GambaFoodCatcher.class,
        GambaAspect.class
})
@EnableAspectJAutoProxy(proxyTargetClass = true)
class GambaFoodCatcherTest {

    @MockitoBean
    private GambaService gambaService;

    @Autowired
    private GambaFoodCatcher gambaFoodCatcher;

    @Captor
    private ArgumentCaptor<JoinPoint> joinPointArgumentCaptor;

    @Test
    void testCatchWithNetWhenCallingThenTriggerAllMatchingBeforeAdvices() {
        gambaFoodCatcher.catchWithNet();

        verify(gambaService, times(1)).beforeWithin(joinPointArgumentCaptor.capture());
        final var joinPointArgumentCaptorValue = joinPointArgumentCaptor.getValue();
        assertThat(joinPointArgumentCaptorValue.getSignature().toString())
                .isEqualTo("Gamba org.jesperancinha.sftd.action.aop.catchers.GambaFoodCatcher.catchWithNet()");
        verify(gambaService, times(1)).beforeWithinAnnotation(joinPointArgumentCaptor.capture());
        final var joinPointArgumentCaptorValue2 = joinPointArgumentCaptor.getValue();
        assertThat(joinPointArgumentCaptorValue2.getSignature().toString())
                .isEqualTo("Gamba org.jesperancinha.sftd.action.aop.catchers.GambaFoodCatcher.catchWithNet()");
    }

    @Test
    void catchWithFishingPole() {
    }

    @Test
    void testCatchByHandWhenCallingThenTriggerAllMatchingBeforeAdvices() {
        gambaFoodCatcher.catchByHand();

        verify(gambaService, times(1)).beforeAnnotation(joinPointArgumentCaptor.capture());
        verify(gambaService, times(1)).beforeWithinNoAtAnnotation(joinPointArgumentCaptor.capture());
        verify(gambaService, times(1)).beforeExecution(joinPointArgumentCaptor.capture());
        verify(gambaService, times(1)).beforeAnnotation(joinPointArgumentCaptor.capture());

        final var joinPointArgumentCaptorAllValues = joinPointArgumentCaptor.getAllValues();
        assertThat(joinPointArgumentCaptorAllValues).hasSize(4);
        final var joinPointArgumentCaptorValue = joinPointArgumentCaptorAllValues.get(0);
        assertThat(joinPointArgumentCaptorValue.getSignature().toString())
                .isEqualTo("Gamba org.jesperancinha.sftd.action.aop.catchers.GambaFoodCatcher.catchByHand()");
        final var joinPointArgumentCaptorValue1 = joinPointArgumentCaptorAllValues.get(1);
        assertThat(joinPointArgumentCaptorValue1.getSignature().toString())
                .isEqualTo("Gamba org.jesperancinha.sftd.action.aop.catchers.GambaFoodCatcher.catchByHand()");
        final var joinPointArgumentCaptorValue2 = joinPointArgumentCaptorAllValues.get(2);
        assertThat(joinPointArgumentCaptorValue2.getSignature().toString())
                .isEqualTo("Gamba org.jesperancinha.sftd.action.aop.catchers.GambaFoodCatcher.catchByHand()");
        final var joinPointArgumentCaptorValue3 = joinPointArgumentCaptorAllValues.get(3);
        assertThat(joinPointArgumentCaptorValue3.getSignature().toString())
                .isEqualTo("Gamba org.jesperancinha.sftd.action.aop.catchers.GambaFoodCatcher.catchByHand()");
    }

    @Test
    void catchByHandExtra() {
    }

    @Test
    void catchWithClaws() {
    }

    @Test
    void catchWithSuperSonicWaves() {
    }

    @Test
    void catchWithABucket() {
    }

    @Test
    void catchWithLove() {
    }
}