package org.jesperancinha.spring.flash9.socksjs;

import org.jesperancinha.console.consolerizer.common.ConsolerizerColor;
import org.jesperancinha.spring.flash9.socksjs.domain.Present;
import org.jesperancinha.spring.flash9.socksjs.domain.Request;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.lang.NonNull;
import org.springframework.messaging.converter.JacksonJsonMessageConverter;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.ext.javatime.deser.LocalDateTimeDeserializer;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.databind.module.SimpleModule;
import tools.jackson.core.JacksonException;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringFlash9LauncherKotlinTest {

    private WebSocketStompClient webSocketStompClient;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        this.webSocketStompClient = new WebSocketStompClient(new SockJsClient(
                List.of(new WebSocketTransport(new StandardWebSocketClient()))));
        webSocketStompClient.setMessageConverter(new JacksonJsonMessageConverter());

        final var module = new SimpleModule();
        final var localDateTimeDeserializer = new
                LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss[.SSSSSSSSS][.SSSSSSSS][.SSSSSSS][.SSSSSS][.SSSSS][.SSSS][.SSS][.SS][.S]"));
        module.addDeserializer(LocalDateTime.class, localDateTimeDeserializer);
        objectMapper = JsonMapper.builder()
                .addModule(module)
                .build();
    }

    @LocalServerPort
    private Long port;

    @Test
    void main() {
    }

    @Test
    void testSendMessageWhenReceiveMessageThenSendResponseBack() throws ExecutionException, InterruptedException, TimeoutException {
        final var blockingQueue = new ArrayBlockingQueue<Present>(1);

        final var session = webSocketStompClient
                .connectAsync(String.format("ws://localhost:%d/ws", port), new StompSessionHandlerAdapter() {
                })
                .get(1, SECONDS);
        subscribe(blockingQueue, session);
        final var request = new Request();
        request.setMessage("soup");

        session.send("/flash9/request", request);

        final Present result = blockingQueue.poll(1, SECONDS);
        assertThat(result).isNotNull();
        assertThat(result.getResponse()).isEqualTo("Baby instant soup doesn't really grab me, today I need something more sub-sub-sub-substantial");
    }

    private void subscribe(ArrayBlockingQueue<Present> blockingQueue, StompSession session) {
        session.subscribe("/business/notification", new StompFrameHandler() {
            @Override
            @NonNull
            public Type getPayloadType(
                    @NonNull
                            StompHeaders headers) {
                return Object.class;
            }

            @Override
            public void handleFrame(
                    @NonNull
                            StompHeaders headers, Object payload) {
                try {
                    blockingQueue.add(objectMapper.readValue((byte[]) (payload), Present.class));
                } catch (JacksonException e) {
                    ConsolerizerColor.RED.printGenericLn("A JSON Parsing Exception has occured!");
                    ConsolerizerColor.RED.printThrowableAndExit(e);
                }
            }
        });
    }
}