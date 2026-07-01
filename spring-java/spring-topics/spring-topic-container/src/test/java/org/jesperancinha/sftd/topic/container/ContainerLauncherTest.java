package org.jesperancinha.sftd.topic.container;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

class ContainerLauncherTest {

    @Test
    void testContext() throws JsonProcessingException {
        ContainerLauncher.main(new String[0]);
    }
}