package org.jesperancinha.spring.action.aop.catchers;

import org.aspectj.lang.JoinPoint;
import org.jesperancinha.spring.action.aop.aspects.TunaAspect;
import org.jesperancinha.spring.action.aop.beans.Bonito4Service;
import org.jesperancinha.spring.action.aop.beans.TunaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import org.springframework.test.annotation.DirtiesContext;

@ExtendWith({SpringExtension.class})
@ContextConfiguration(classes = {
        MegaTunaCatcher.class,
        TunaAspect.class
})
@EnableAspectJAutoProxy(proxyTargetClass = true)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class MegaTunaCatcherTest {

    @MockitoBean
    private TunaService tunaService;

    @MockitoBean
    private Bonito4Service bonito4Service;

    @Autowired
    private MegaTunaCatcher megaTunaCatcher;

        private final ArgumentCaptor<JoinPoint> joinPointArgumentCaptor = ArgumentCaptor.forClass(JoinPoint.class);

    @Test
    void catchWithNet() {
        megaTunaCatcher.catchWithNet();

        verify(tunaService, only()).beforeCatching(joinPointArgumentCaptor.capture());
        final var joinPointArgumentCaptorValue = joinPointArgumentCaptor.getValue();
        assertThat(joinPointArgumentCaptorValue.getSignature().toString())
                .isEqualTo("Tuna org.jesperancinha.spring.action.aop.catchers.MegaTunaCatcher.catchWithNet()");
        verifyNoInteractions(bonito4Service);
    }

    @Test
    void catchWithFishingPole() {
    }

    @Test
    void catchByHand() {
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