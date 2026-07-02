package org.jesperancinha.sftd.flash14.health;

import org.apache.commons.io.IOUtils;
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;


@ActiveProfiles("test")
@SpringBootTest(
        webEnvironment = RANDOM_PORT,
        classes = Flash14HealthIndicator.class)
@EnableAutoConfiguration
class Flash14HealthIndicatorTest {

    @LocalServerPort
    private Long port;

    private final RestTemplate restTemplate = new RestTemplate();

    @Test
    void testHealthCheckWhenEndpointCalledThenReturnHealthChecks() {
        final String url = String.format("http://localhost:%s/actuator/health", port);
        final var headers = new HttpHeaders();
        final var request = new HttpEntity<>(headers);
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            @Override
            public void handleError(@NotNull URI url, @NotNull HttpMethod method, @NotNull ClientHttpResponse response) throws IOException {
                final String responseText = IOUtils.toString(response.getBody(), Charset.defaultCharset());
                assertThat(responseText).isNotNull();
                assertThat(responseText).contains("\"lyric\":\"A chance for calm, A hope for freedom\"");
                ConsolerizerComposer.outSpace()
                        .green("All assertions complete!")
                        .reset();
                super.handleError(url, method, response);
            }
        });

        assertThrows(HttpServerErrorException.class, () -> restTemplate.exchange(
                url, HttpMethod.GET,
                request, String.class));
    }
}