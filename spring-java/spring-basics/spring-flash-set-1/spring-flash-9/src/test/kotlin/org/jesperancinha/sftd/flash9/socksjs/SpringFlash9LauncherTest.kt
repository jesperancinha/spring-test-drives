package org.jesperancinha.sftd.flash9.socksjs

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.jesperancinha.console.consolerizer.common.ConsolerizerColor
import org.jesperancinha.sftd.flash9.socksjs.domain.Present
import org.jesperancinha.sftd.flash9.socksjs.domain.Request
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.messaging.converter.MappingJackson2MessageConverter
import org.springframework.messaging.simp.stomp.StompFrameHandler
import org.springframework.messaging.simp.stomp.StompHeaders
import org.springframework.messaging.simp.stomp.StompSession
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter
import org.springframework.web.socket.client.standard.StandardWebSocketClient
import org.springframework.web.socket.messaging.WebSocketStompClient
import org.springframework.web.socket.sockjs.client.SockJsClient
import org.springframework.web.socket.sockjs.client.WebSocketTransport
import java.io.IOException
import java.lang.reflect.Type
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.ExecutionException
import java.util.concurrent.TimeUnit.SECONDS
import java.util.concurrent.TimeoutException

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class SpringFlash9LauncherTest @Autowired constructor(
) {
    private val webSocketStompClient: WebSocketStompClient by lazy {
        WebSocketStompClient(
            SockJsClient(listOf(WebSocketTransport(StandardWebSocketClient())))
        ).apply {
            messageConverter = MappingJackson2MessageConverter()
        }
    }

    private val objectMapper: ObjectMapper by lazy {
        val module = JavaTimeModule()
        val localDateTimeDeserializer =
            LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss[.SSSSSSSSS][.SSSSSSSS][.SSSSSSS][.SSSSSS][.SSSSS][.SSSS][.SSS][.SS][.S]"))
        module.addDeserializer(LocalDateTime::class.java, localDateTimeDeserializer)
        Jackson2ObjectMapperBuilder.json()
            .modules(module)
            .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .build()
    }

    @LocalServerPort
    private val port: Long? = null

    @Test
    @Throws(ExecutionException::class, InterruptedException::class, TimeoutException::class)
    fun testSendMessageWhenReceiveMessageThenSendResponseBack() {
        val blockingQueue: ArrayBlockingQueue<Present> = ArrayBlockingQueue<Present>(1)
        val session = webSocketStompClient
            .connect(String.format("ws://localhost:%d/ws", port), object : StompSessionHandlerAdapter() {})
            .get(1, SECONDS)
        subscribe(blockingQueue, session)
        val request = Request()
        request.message = "soup"
        session.send("/flash9/request", request)
        blockingQueue.poll(1, SECONDS)
            .shouldNotBeNull()
            .response
            .shouldNotBeNull() shouldBe "Baby instant soup doesn't really grab me, today I need something more sub-sub-sub-substantial"
    }

    private fun subscribe(blockingQueue: ArrayBlockingQueue<Present>, session: StompSession) {
        session.subscribe("/business/notification", object : StompFrameHandler {
            override fun getPayloadType(headers: StompHeaders): Type = Object::class.java

            override fun handleFrame(headers: StompHeaders, payload: Any?) {
                try {
                    blockingQueue.add(objectMapper.readValue(payload as ByteArray?, Present::class.java))
                } catch (e: JsonProcessingException) {
                    ConsolerizerColor.RED.printGenericLn("A JSON Parsing Exception has occured!")
                    ConsolerizerColor.RED.printThrowableAndExit(e)
                } catch (e: IOException) {
                    ConsolerizerColor.RED.printGenericLn("An IO Exception has occurred!")
                    ConsolerizerColor.RED.printThrowableAndExit(e)
                }
            }
        })
    }
}