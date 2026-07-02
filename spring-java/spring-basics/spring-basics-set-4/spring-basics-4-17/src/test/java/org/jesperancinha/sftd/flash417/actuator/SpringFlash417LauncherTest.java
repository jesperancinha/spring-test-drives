package org.jesperancinha.sftd.flash417.actuator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SpringFlash417LauncherTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testRootWhenCalling_getGhostTown() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string("Ghost Town"));
    }

    @Test
    void testActuatorWhenCalling_getAllActuatorEndpoints() throws Exception {
        mockMvc.perform(get("/actuator"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"_links\":{" +
                        "\"self\":{\"href\":\"http://localhost/actuator\",\"templated\":false}," +
                        "\"beans\":{\"href\":\"http://localhost/actuator/beans\",\"templated\":false}," +
                        "\"caches-cache\":{\"href\":\"http://localhost/actuator/caches/{cache}\",\"templated\":true}," +
                        "\"caches\":{\"href\":\"http://localhost/actuator/caches\",\"templated\":false}," +
                        "\"health\":{\"href\":\"http://localhost/actuator/health\",\"templated\":false}," +
                        "\"health-path\":{\"href\":\"http://localhost/actuator/health/{*path}\",\"templated\":true}," +
                        "\"info\":{\"href\":\"http://localhost/actuator/info\",\"templated\":false}," +
                        "\"conditions\":{\"href\":\"http://localhost/actuator/conditions\",\"templated\":false}," +
                        "\"shutdown\":{\"href\":\"http://localhost/actuator/shutdown\",\"templated\":false}," +
                        "\"configprops\":{\"href\":\"http://localhost/actuator/configprops\",\"templated\":false}," +
                        "\"env\":{\"href\":\"http://localhost/actuator/env\",\"templated\":false}," +
                        "\"env-toMatch\":{\"href\":\"http://localhost/actuator/env/{toMatch}\",\"templated\":true}," +
                        "\"loggers\":{\"href\":\"http://localhost/actuator/loggers\",\"templated\":false}," +
                        "\"loggers-name\":{\"href\":\"http://localhost/actuator/loggers/{name}\",\"templated\":true}," +
                        "\"heapdump\":{\"href\":\"http://localhost/actuator/heapdump\",\"templated\":false}," +
                        "\"threaddump\":{\"href\":\"http://localhost/actuator/threaddump\",\"templated\":false}," +
                        "\"metrics\":{\"href\":\"http://localhost/actuator/metrics\",\"templated\":false}," +
                        "\"metrics-requiredMetricName\":{\"href\":\"http://localhost/actuator/metrics/{requiredMetricName}\",\"templated\":true}," +
                        "\"scheduledtasks\":{\"href\":\"http://localhost/actuator/scheduledtasks\",\"templated\":false}," +
                        "\"mappings\":{\"href\":\"http://localhost/actuator/mappings\",\"templated\":false}}}"));
    }
}