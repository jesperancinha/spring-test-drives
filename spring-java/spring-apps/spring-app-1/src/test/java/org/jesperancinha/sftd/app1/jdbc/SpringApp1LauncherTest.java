package org.jesperancinha.sftd.app1.jdbc;

import org.jesperancinha.sftd.app1.jdbc.containers.AbstractTestContainersIT;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(initializers = AbstractTestContainersIT.DockerPostgresDataInitializer.class)
public class SpringApp1LauncherTest {

    @Test
    public void testContext() {

    }

}