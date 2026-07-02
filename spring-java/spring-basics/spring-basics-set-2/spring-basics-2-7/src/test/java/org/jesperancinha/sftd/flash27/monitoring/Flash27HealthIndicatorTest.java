package org.jesperancinha.sftd.flash27.monitoring;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.jesperancinha.sftd.flash27.monitoring.model.Health;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class Flash27HealthIndicatorTest {

    @Autowired
    private MockMvc mockMvc;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void health() throws Exception {
        final var mvcResult = mockMvc.perform(get("/actuator/health"))
                .andReturn();

        final var response = mvcResult.getResponse();
        assertThat(response).isNotNull();
        final var contentAsString = response.getContentAsString();
        assertThat(contentAsString).isNotNull();
        ConsolerizerComposer.outSpace()
                .brightBlue("Service response is:")
                .jsonPrettyPrint(contentAsString)
                .reset();
        final var health = objectMapper.readValue(contentAsString, Health.class);
        assertThat(health).isNotNull();
        final var status = health.status();
        assertThat(status).isNotNull();
        assertThat(status).isIn("UP", "DOWN", "OUT_OF_SERVICE");
        final var components = health.components();
        assertThat(components).isNotNull();
        final var db = components.db();
        assertThat(db).isNotNull();
        assertThat(db.status()).isEqualTo("UP");
        final var dbDetails = db.details();
        assertThat(dbDetails).isNotNull();
        assertThat(dbDetails.database()).isEqualTo("H2");
        assertThat(dbDetails.result()).isIn(1, null);
        assertThat(dbDetails.validationQuery()).isIn("SELECT 1", "isValid()");
        assertThat(dbDetails.lyric()).isNull();
        assertThat(dbDetails.total()).isNull();
        assertThat(dbDetails.free()).isNull();
        assertThat(dbDetails.threshold()).isNull();
        final var diskSpace = components.diskSpace();
        assertThat(diskSpace).isNotNull();
        final var diskSpaceStatus = diskSpace.status();
        assertThat(diskSpaceStatus).isNotNull();
        assertThat(diskSpaceStatus).isEqualTo("UP");
        final var diskSpaceDetails = diskSpace.details();
        assertThat(diskSpaceDetails).isNotNull();
        assertThat(diskSpaceDetails.database()).isNull();
        assertThat(diskSpaceDetails.result()).isNull();
        assertThat(diskSpaceDetails.validationQuery()).isNull();
        assertThat(diskSpaceDetails.lyric()).isNull();
        assertThat(diskSpaceDetails.total()).isNotNull();
        assertThat(diskSpaceDetails.free()).isNotNull();
        assertThat(diskSpaceDetails.threshold()).isNotNull();
        final var flash27 = components.flash27();
        assertThat(flash27).isNotNull();
        final var flash27Status = flash27.status();
        assertThat(flash27Status).isNotNull();
        final var flash27Details = flash27.details();
        assertThat(flash27Details).isNotNull();
        assertThat(flash27Details.database()).isNull();
        assertThat(flash27Details.result()).isNull();
        assertThat(flash27Details.validationQuery()).isNull();
        assertThat(flash27Details.total()).isNull();
        assertThat(flash27Details.free()).isNull();
        assertThat(flash27Details.threshold()).isNull();
        assertThat(flash27Details.lyric()).isNotNull();
        assertThat(flash27Details.lyric()).isIn(
                "Oh, not to touch a hair on your head",
                "Leave you as you are",
                "If he felt he had to direct you",
                "Then direct you into my arms");
        final var ping = components.ping();
        assertThat(ping).isNotNull();
        final var pingStatus = ping.status();
        assertThat(pingStatus).isNotNull();
        assertThat(pingStatus).isEqualTo("UP");
    }
}