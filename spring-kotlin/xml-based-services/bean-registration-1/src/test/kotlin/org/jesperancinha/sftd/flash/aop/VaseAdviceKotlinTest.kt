package org.jesperancinha.sftd.flash.aop

import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.aopalliance.intercept.MethodInvocation
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
internal class VaseAdviceKotlinTest {
    @MockK
    lateinit var methodInvocation: MethodInvocation

    @Test
    @Throws(Throwable::class)
    fun `should invoke and call return object`() {
        every { methodInvocation.proceed() } returns "Proceed"
        val vaseAdvice = VaseAdvice()
        val invoke = vaseAdvice.invoke(methodInvocation)
        invoke.shouldBeInstanceOf<String>()
        invoke shouldBe "Proceed"
        verify(exactly = 1) { methodInvocation.proceed() }
    }
}