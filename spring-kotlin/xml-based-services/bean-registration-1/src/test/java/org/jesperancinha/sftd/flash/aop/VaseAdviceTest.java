package org.jesperancinha.sftd.flash.aop;

import org.aopalliance.intercept.MethodInvocation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VaseAdviceTest {

    @Mock
    private MethodInvocation methodInvocation;

    @Test
    void testInvokeWhenCallThenCallandReturnObject() throws Throwable {
        when(methodInvocation.proceed()).thenReturn("Proceed");

        final VaseAdvice vaseAdvice = new VaseAdvice();
        final Object invoke = vaseAdvice.invoke(methodInvocation);

        assertThat(invoke).isInstanceOf(String.class);
        assertThat(invoke).isEqualTo("Proceed");

        verify(methodInvocation, times(1)).proceed();
    }
}