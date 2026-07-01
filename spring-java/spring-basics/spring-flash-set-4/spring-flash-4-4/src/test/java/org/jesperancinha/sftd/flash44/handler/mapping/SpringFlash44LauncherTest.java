package org.jesperancinha.sftd.flash44.handler.mapping;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SpringFlash44LauncherTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testContext() {
    }

    @Test
    void testHandlerRequestWhenCalledThenGetAllRunningBeans() throws Exception {
        final var mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string("[" +
                        "{\"name\":\"requestMappingHandlerMapping\",\"order\":0,\"type\":\"org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping\"}," +
                        "{\"name\":\"beanNameHandlerMapping\",\"order\":2,\"type\":\"org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping\"}," +
                        "{\"name\":\"routerFunctionMapping\",\"order\":-1,\"type\":\"org.springframework.web.servlet.function.support.RouterFunctionMapping\"}," +
                        "{\"name\":\"healthEndpointWebMvcHandlerMapping\",\"order\":-100,\"type\":\"org.springframework.boot.actuate.endpoint.web.servlet.AdditionalHealthEndpointPathsWebMvcHandlerMapping\"}," +
                        "{\"name\":\"webEndpointServletHandlerMapping\",\"order\":-100,\"type\":\"org.springframework.boot.actuate.endpoint.web.servlet.WebMvcEndpointHandlerMapping\"}," +
                        "{\"name\":\"controllerEndpointHandlerMapping\",\"order\":-100,\"type\":\"org.springframework.boot.actuate.endpoint.web.servlet.ControllerEndpointHandlerMapping\"}]"))
                .andReturn();

        ConsolerizerComposer.outSpace()
                .yellow()
                .jsonPrettyPrint(mvcResult.getResponse().getContentAsString()).reset();
    }
}