package org.jesperancinha.sftd.flash19.transactional.configuration.controllers

import com.ninjasquad.springmockk.MockkBean
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.nulls.shouldBeNull
import jakarta.servlet.ServletException
import org.jesperancinha.sftd.flash19.transactional.configuration.containers.AbstractTestContainersKotlinIT
import org.jesperancinha.sftd.flash19.transactional.controllers.AlbumController
import org.jesperancinha.sftd.flash19.transactional.dto.AlbumDto
import org.jesperancinha.sftd.flash19.transactional.repos.AlbumRepository
import org.jesperancinha.sftd.flash19.transactional.services.AlbumService
import org.jesperancinha.sftd.flash19.transactional.services.AlbumServiceImpl
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.transaction.annotation.EnableTransactionManagement

@WebMvcTest(AlbumController::class)
@EnableTransactionManagement
@ContextConfiguration(
    classes = [AlbumServiceImpl::class, AlbumController::class],
    initializers = [AbstractTestContainersKotlinIT.DockerPostgresDataInitializer::class]
)
internal class AlbumControllerKotlinTest @Autowired constructor(
    @Autowired
    private val mockMvc: MockMvc,
    @Autowired
    private val albumService: AlbumService
) {

    @MockkBean(relaxed = true)
    lateinit var albumRepository: AlbumRepository

    @Test
    fun testCreateAlbumRollBackWhenCallCreateAlbumThenCreateAndRollback() {
        val inputAlbum = AlbumDto()
            .apply {
                name = "The Abbey Road Sessions"
                artist = "Kylie Minogue"
                publisher = "EMI"
                year = 2012L
            }

        shouldThrow<ServletException> {
            mockMvc.perform(
                MockMvcRequestBuilders.post("/create/albumRollback")
                    .header("name", "The Abbey Road Sessions")
                    .header("artist", "Kylie Minogue")
                    .header("publisher", "EMI")
                    .header("year", 2012)
            )
        }
        albumService.lastTry.shouldBeNull()
    }
}