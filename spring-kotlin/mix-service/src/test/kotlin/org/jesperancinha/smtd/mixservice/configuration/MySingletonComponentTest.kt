package org.jesperancinha.smtd.mixservice.configuration

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component
import kotlin.test.Test

@Component
@Primary
object MySingletonComponent {
    fun sayHello(): Boolean {
        println("AHA!")
        return true
    }
}
@SpringBootTest
class MySingletonComponentTest {


    @Test
    fun `should be able to mock singleton object bean`() {
    }
}