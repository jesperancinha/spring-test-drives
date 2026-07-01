package org.jesperancinha.sftd.flash18.aop.afterthrowing.aspect;

import org.aspectj.lang.JoinPoint;
import org.jesperancinha.sftd.flash18.aop.afterthrowing.beans.JoinPointService;
import org.jesperancinha.sftd.flash18.aop.afterthrowing.service.impl.LyricsService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Unit tests for the Aspect Class
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LyricAspectTest {

    @Autowired
    private LyricsService lyricsService;

    @MockitoBean
    private JoinPointService joinPointService;

    @Captor
    private ArgumentCaptor<JoinPoint> joinPointArgumentCaptor;

    @Captor
    private ArgumentCaptor<Exception> exceptionArgumentCaptor;

    /**
     * Tests the Advices that are supposed to respond to method {@link LyricsService#resultLyric1()}
     */
    @Test
    void testAfterThrowingAdviceWhenCallingEnumerateLyric1ThenRegisterAllAdvices() {
        final RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> lyricsService.enumerateLyric1());

        verify(joinPointService, times(1)).afterThrowingAdvice(joinPointArgumentCaptor.capture(), exceptionArgumentCaptor.capture());
        verify(joinPointService, times(1)).afterThrowingAdvice1(joinPointArgumentCaptor.capture(), exceptionArgumentCaptor.capture());
        final List<JoinPoint> allValues = joinPointArgumentCaptor.getAllValues();
        assertThat(allValues).hasSize(2);
        assertThat(allValues.get(0).toString()).isEqualTo("execution(void org.jesperancinha.sftd.flash18.aop.afterthrowing.service.impl.LyricsServiceImpl.enumerateLyric1())");
        assertThat(allValues.get(1).toString()).isEqualTo("execution(void org.jesperancinha.sftd.flash18.aop.afterthrowing.service.impl.LyricsServiceImpl.enumerateLyric1())");
        final List<Exception> exceptionList = exceptionArgumentCaptor.getAllValues();
        assertThat(exceptionList.get(0)).isSameAs(runtimeException);
        assertThat(exceptionList.get(1)).isSameAs(runtimeException);
    }

    @Test
    void testAfterThrowingAdvice2_whenCallingEnumerateLyric2ThenRegisterAllAdvices() {
        final RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> lyricsService.enumerateLyric2());

        verify(joinPointService, times(1)).afterThrowingAdvice(joinPointArgumentCaptor.capture(), exceptionArgumentCaptor.capture());
        verify(joinPointService, times(1)).afterThrowingAdvice2(joinPointArgumentCaptor.capture(), exceptionArgumentCaptor.capture());
        final List<JoinPoint> allValues = joinPointArgumentCaptor.getAllValues();
        assertThat(allValues).hasSize(2);
        assertThat(allValues.get(0).toString()).isEqualTo("execution(void org.jesperancinha.sftd.flash18.aop.afterthrowing.service.impl.LyricsServiceImpl.enumerateLyric2())");
        assertThat(allValues.get(1).toString()).isEqualTo("execution(void org.jesperancinha.sftd.flash18.aop.afterthrowing.service.impl.LyricsServiceImpl.enumerateLyric2())");
        final List<Exception> exceptionList = exceptionArgumentCaptor.getAllValues();
        assertThat(exceptionList.get(0)).isSameAs(runtimeException);
        assertThat(exceptionList.get(1)).isSameAs(runtimeException);
    }

    @Test
    void testAfterThrowingAdvice3_whenCallingEnumerateLyric3ThenRegisterAllAdvices() {
        final RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> lyricsService.enumerateLyric3());

        verify(joinPointService, times(1)).afterThrowingAdvice(joinPointArgumentCaptor.capture(), exceptionArgumentCaptor.capture());
        verify(joinPointService, times(1)).afterThrowingAdvice3(joinPointArgumentCaptor.capture(), exceptionArgumentCaptor.capture());
        final List<JoinPoint> allValues = joinPointArgumentCaptor.getAllValues();
        assertThat(allValues).hasSize(2);
        assertThat(allValues.get(0).toString()).isEqualTo("execution(void org.jesperancinha.sftd.flash18.aop.afterthrowing.service.impl.LyricsServiceImpl.enumerateLyric3())");
        assertThat(allValues.get(1).toString()).isEqualTo("execution(void org.jesperancinha.sftd.flash18.aop.afterthrowing.service.impl.LyricsServiceImpl.enumerateLyric3())");
        final List<Exception> exceptionList = exceptionArgumentCaptor.getAllValues();
        assertThat(exceptionList.get(0)).isSameAs(runtimeException);
        assertThat(exceptionList.get(1)).isSameAs(runtimeException);
    }

    @Test
    void testAfterThrowingAdviceResultsWhenResultLyric1ThenRegisterAllAdvice() {
        final RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> lyricsService.resultLyric1());

        verify(joinPointService, times(1)).afterThrowingAdvice(joinPointArgumentCaptor.capture(), exceptionArgumentCaptor.capture());
        verify(joinPointService, times(0)).afterThrowingAdvice1(joinPointArgumentCaptor.capture(), exceptionArgumentCaptor.capture());
        verify(joinPointService, times(0)).afterThrowingAdvice2(joinPointArgumentCaptor.capture(), exceptionArgumentCaptor.capture());
        verify(joinPointService, times(0)).afterThrowingAdvice3(joinPointArgumentCaptor.capture(), exceptionArgumentCaptor.capture());
        verify(joinPointService, times(1)).afterThrowingAdviceResults(joinPointArgumentCaptor.capture(), exceptionArgumentCaptor.capture());
        final List<JoinPoint> allValues = joinPointArgumentCaptor.getAllValues();
        assertThat(allValues).hasSize(2);
        assertThat(allValues.get(0).toString()).isEqualTo("execution(void org.jesperancinha.sftd.flash18.aop.afterthrowing.service.impl.LyricsServiceImpl.resultLyric1())");
        assertThat(allValues.get(1).toString()).isEqualTo("execution(void org.jesperancinha.sftd.flash18.aop.afterthrowing.service.impl.LyricsServiceImpl.resultLyric1())");
        final List<Exception> exceptionList = exceptionArgumentCaptor.getAllValues();
        assertThat(exceptionList.get(0)).isSameAs(runtimeException);
        assertThat(exceptionList.get(1)).isSameAs(runtimeException);
    }

    @Test
    void testAfterThrowingAdviceResultsWhenResultLyric2ThenRegisterAllAdvice() {
        final RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> lyricsService.resultLyric2());

        verify(joinPointService, times(1)).afterThrowingAdvice(joinPointArgumentCaptor.capture(), exceptionArgumentCaptor.capture());
        verify(joinPointService, times(0)).afterThrowingAdvice1(joinPointArgumentCaptor.capture(), exceptionArgumentCaptor.capture());
        verify(joinPointService, times(0)).afterThrowingAdvice2(joinPointArgumentCaptor.capture(), exceptionArgumentCaptor.capture());
        verify(joinPointService, times(0)).afterThrowingAdvice3(joinPointArgumentCaptor.capture(), exceptionArgumentCaptor.capture());
        verify(joinPointService, times(1)).afterThrowingAdviceResults(joinPointArgumentCaptor.capture(), exceptionArgumentCaptor.capture());
        final List<JoinPoint> allValues = joinPointArgumentCaptor.getAllValues();
        assertThat(allValues).hasSize(2);
        assertThat(allValues.get(0).toString()).isEqualTo("execution(void org.jesperancinha.sftd.flash18.aop.afterthrowing.service.impl.LyricsServiceImpl.resultLyric2())");
        assertThat(allValues.get(1).toString()).isEqualTo("execution(void org.jesperancinha.sftd.flash18.aop.afterthrowing.service.impl.LyricsServiceImpl.resultLyric2())");
        final List<Exception> exceptionList = exceptionArgumentCaptor.getAllValues();
        assertThat(exceptionList.get(0)).isSameAs(runtimeException);
        assertThat(exceptionList.get(1)).isSameAs(runtimeException);
    }

    @Test
    void testAfterThrowingAdviceResultsWhenResultLyric3ThenRegisterAllAdvice() {
        final RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> lyricsService.resultLyric3());

        verify(joinPointService, times(1)).afterThrowingAdvice(joinPointArgumentCaptor.capture(), exceptionArgumentCaptor.capture());
        verify(joinPointService, times(0)).afterThrowingAdvice1(joinPointArgumentCaptor.capture(), exceptionArgumentCaptor.capture());
        verify(joinPointService, times(0)).afterThrowingAdvice2(joinPointArgumentCaptor.capture(), exceptionArgumentCaptor.capture());
        verify(joinPointService, times(0)).afterThrowingAdvice3(joinPointArgumentCaptor.capture(), exceptionArgumentCaptor.capture());
        verify(joinPointService, times(1)).afterThrowingAdviceResults(joinPointArgumentCaptor.capture(), exceptionArgumentCaptor.capture());
        final List<JoinPoint> allValues = joinPointArgumentCaptor.getAllValues();
        assertThat(allValues).hasSize(2);
        assertThat(allValues.get(0).toString()).isEqualTo("execution(void org.jesperancinha.sftd.flash18.aop.afterthrowing.service.impl.LyricsServiceImpl.resultLyric3())");
        assertThat(allValues.get(1).toString()).isEqualTo("execution(void org.jesperancinha.sftd.flash18.aop.afterthrowing.service.impl.LyricsServiceImpl.resultLyric3())");
        final List<Exception> exceptionList = exceptionArgumentCaptor.getAllValues();
        assertThat(exceptionList.get(0)).isSameAs(runtimeException);
        assertThat(exceptionList.get(1)).isSameAs(runtimeException);
    }

    @Test
    void testAfterThrowingAdviceResultsWhenResultLyric4ThenRegisterAllAdvice() {
        final RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> lyricsService.resultLyric4());

        verify(joinPointService, times(1)).afterThrowingAdvice(joinPointArgumentCaptor.capture(), exceptionArgumentCaptor.capture());
        verify(joinPointService, times(0)).afterThrowingAdvice1(joinPointArgumentCaptor.capture(), exceptionArgumentCaptor.capture());
        verify(joinPointService, times(0)).afterThrowingAdvice2(joinPointArgumentCaptor.capture(), exceptionArgumentCaptor.capture());
        verify(joinPointService, times(0)).afterThrowingAdvice3(joinPointArgumentCaptor.capture(), exceptionArgumentCaptor.capture());
        verify(joinPointService, times(1)).afterThrowingAdviceResults(joinPointArgumentCaptor.capture(), exceptionArgumentCaptor.capture());
        final List<JoinPoint> allValues = joinPointArgumentCaptor.getAllValues();
        assertThat(allValues).hasSize(2);
        assertThat(allValues.get(0).toString()).isEqualTo("execution(void org.jesperancinha.sftd.flash18.aop.afterthrowing.service.impl.LyricsServiceImpl.resultLyric4())");
        assertThat(allValues.get(1).toString()).isEqualTo("execution(void org.jesperancinha.sftd.flash18.aop.afterthrowing.service.impl.LyricsServiceImpl.resultLyric4())");
        final List<Exception> exceptionList = exceptionArgumentCaptor.getAllValues();
        assertThat(exceptionList.get(0)).isSameAs(runtimeException);
        assertThat(exceptionList.get(1)).isSameAs(runtimeException);
    }
}