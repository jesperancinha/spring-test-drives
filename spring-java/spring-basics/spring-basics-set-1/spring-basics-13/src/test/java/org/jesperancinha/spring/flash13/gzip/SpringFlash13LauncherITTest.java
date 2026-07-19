package org.jesperancinha.spring.flash13.gzip;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPInputStream;

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
    void testCallsToCssWhenNormalThenGetNormal() throws IOException {
        final var headers = new HttpHeaders();
        final var request = new HttpEntity<>(headers);
        var response = restTemplate.exchange(
                String.format("http://localhost:%s/flash13.styles.css.gz", port), HttpMethod.GET,
                request, byte[].class);
        final String directGZipValueString = gunzip(response.getBody());

        final var headers2 = new HttpHeaders();
        headers2.set("Accept-Encoding", "gzip, deflate");
        final var request2 = new HttpEntity<>(headers2);
        var response2 = restTemplate.exchange(
                String.format("http://localhost:%s/flash13.styles.css", port), HttpMethod.GET,
                request2, byte[].class);
        final String gzipValueString = gunzip(response2.getBody());

        ConsolerizerComposer.outSpace()
                .green(directGZipValueString)
                .reset();
        ConsolerizerComposer.outSpace()
                .green(gzipValueString)
                .reset();

        assertThat(directGZipValueString).isEqualTo(gzipValueString);
    }

    private static String gunzip(final byte[] gzipped) throws IOException {
        try (var gzipInputStream = new GZIPInputStream(new java.io.ByteArrayInputStream(gzipped));
             var output = new ByteArrayOutputStream()) {
            gzipInputStream.transferTo(output);
            return output.toString(StandardCharsets.UTF_8);
        }
    }

}