package org.jesperancinha.sftd.flash19.transactional.configuration.services

import com.ninjasquad.springmockk.MockkBean
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldNotBeSameInstanceAs
import io.mockk.every
import io.mockk.slot
import io.mockk.verify
import org.jesperancinha.sftd.flash19.transactional.converters.AlbumConverter
import org.jesperancinha.sftd.flash19.transactional.domain.Album
import org.jesperancinha.sftd.flash19.transactional.dto.AlbumDto
import org.jesperancinha.sftd.flash19.transactional.repos.AlbumRepository
import org.jesperancinha.sftd.flash19.transactional.services.AlbumServiceImpl
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@MockkBean(AlbumRepository::class)
@ContextConfiguration(classes = [AlbumServiceImpl::class])
internal class AlbumServiceImplKotlinTest @Autowired constructor(
    @Autowired
    private val albumRepository: AlbumRepository,
    @Autowired
    private val albumService: AlbumServiceImpl
) {

    @BeforeEach
    fun setUp() {
        every { albumRepository.deleteById(any()) } returns Unit
    }

    @Test
    fun `should delete album by id`() {
        val deleted = albumService.deleteAlbumByIdI(1L)
        deleted.shouldBeTrue()
        verify { albumRepository.deleteById(1L) }
    }

    @Test
    fun `should update album`() {
        val inputAlbum = AlbumDto().apply {
            name = "The Abbey Road Sessions"
            artist = "Kylie Minogue"
            publisher = "EMI"
            year = 2012L
        }
        every { albumRepository.save(any()) } returns AlbumConverter.toData(inputAlbum)
        val albumSlot = slot<Album>()
        val album = albumService.updateAlbum(inputAlbum)
        verify { albumRepository.save(capture(albumSlot)) }
        albumSlot.captured
            .shouldNotBeNull()
            .apply {
                name shouldBe "The Abbey Road Sessions"
                artist shouldBe "Kylie Minogue"
                publisher shouldBe "EMI"
                year shouldBe 2012L
            }
        album
            .shouldNotBeNull()
            .apply {
                name shouldBe "The Abbey Road Sessions"
                artist shouldBe "Kylie Minogue"
                publisher shouldBe "EMI"
                year shouldBe 2012L
            }
    }

    @Test
    fun `should create an album`() {
        val inputAlbum = AlbumDto().apply {
            name = "A state of Trance 2006"
            artist = "Armin van Buuren"
            publisher = "Armada"
            year = 2006L
        }
        every { albumRepository.save(any()) } returns AlbumConverter.toData(inputAlbum)
        val album = albumService.createAlbum(
            "A state of Trance 2006", "Armin van Buuren", "Armada", 2006L
        )
        val albumSlot = slot<Album>()
        verify { albumRepository.save(capture(albumSlot)) }

        albumSlot.captured
            .shouldNotBeNull()
            .apply {
                name shouldBe "A state of Trance 2006"
                artist shouldBe "Armin van Buuren"
                publisher shouldBe "Armada"
                year shouldBe 2006L
            }
        album
            .shouldNotBeNull()
            .apply {
                name shouldBe "A state of Trance 2006"
                artist shouldBe "Armin van Buuren"
                publisher shouldBe "Armada"
                year shouldBe 2006L
            }
    }

    @Test
    fun `should rollback the creation of an album automatically`() {
        shouldThrow<RuntimeException> {
            albumService.createAlbumRollBack(
                "The Downward Spiral", "Nine Inch Nails", "Nothing Records", 1995L
            )
        }
        val albumSlot = slot<Album>()

        verify { albumRepository.save(capture(albumSlot)) }

        albumSlot.captured
            .shouldNotBeNull()
            .apply {
                id.shouldBeNull()
                name shouldBe "The Downward Spiral"
                artist shouldBe "Nine Inch Nails"
                publisher shouldBe "Nothing Records"
                year shouldBe 1995L
            }
    }

    @Test
    fun `should run find all with empty results`() {
        val albums: List<Album> = ArrayList()
        every { albumRepository.findAll() } returns albums
        albumService.allAlbums
            .shouldHaveSize(0) shouldNotBeSameInstanceAs  albums
    }
}