package org.jesperancinha.sftd.flash13.gzip;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class SpringFlash13LauncherITTest {

    @LocalServerPort
    private Long port;

    private final RestTemplate restTemplate = new RestTemplate();

    @Test
    void testContext() {
    }

    @Test
    void testCallsToCssWhenNormalThenGetNormal() {
        final var headers = new HttpHeaders();
        final var request = new HttpEntity<>(headers);
        var response = restTemplate.exchange(
                String.format("http://localhost:%s/flash13.styles.css.gz", port), HttpMethod.GET,
                request, String.class);
        final String directGZipValueString = response.getBody();

        final var headers2 = new HttpHeaders();
        headers2.set("Accept-Encoding", "gzip, deflate");
        final var request2 = new HttpEntity<>(headers2);
        var response2 = restTemplate.exchange(
                String.format("http://localhost:%s/flash13.styles.css", port), HttpMethod.GET,
                request2, String.class);
        final String gzipValueString = response2.getBody();

        ConsolerizerComposer.outSpace()
                .green(directGZipValueString)
                .reset();
        ConsolerizerComposer.outSpace()
                .green(gzipValueString)
                .reset();

        assertThat(directGZipValueString).isEqualTo(gzipValueString);
    }

}