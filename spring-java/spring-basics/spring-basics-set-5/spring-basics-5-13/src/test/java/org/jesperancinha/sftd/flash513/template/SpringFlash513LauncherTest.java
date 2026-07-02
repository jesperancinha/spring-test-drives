package org.jesperancinha.sftd.flash513.template;

import org.jesperancinha.sftd.flash513.template.dto.Casket;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class SpringFlash513LauncherTest {

    @Test
    void testSendCasketWhenPostRequestThenGetResponse() throws JSONException {
        final var restTemplate = new RestTemplate();
        final var expectedCasket = "{\"designation\":\"Bananas\", \"units\":100,\"brand\":\"Chiquita\"}";
        final var casket = new Casket();
        casket.setBrand("Chiquita");
        casket.setDesignation("Bananas");
        casket.setUnits(100);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(expectedCasket, headers);

        ResponseEntity<String> response = restTemplate.exchange("http://localhost:8081/api/casket", HttpMethod.POST, entity, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        JSONAssert.assertEquals(expectedCasket, response.getBody(), false);
    }

}