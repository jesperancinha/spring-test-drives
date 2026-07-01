package org.jesperancinha.sftd.app1.jdbc

import org.jesperancinha.sftd.app1.jdbc.containers.AbstractTestContainersIT
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration

@SpringBootTest
@ContextConfiguration(initializers = [AbstractTestContainersIT.DockerPostgresDataInitializer::class])
class SpringApp1LauncherKotlinTest {
    @Test
    fun `should load context`() {
    }
}