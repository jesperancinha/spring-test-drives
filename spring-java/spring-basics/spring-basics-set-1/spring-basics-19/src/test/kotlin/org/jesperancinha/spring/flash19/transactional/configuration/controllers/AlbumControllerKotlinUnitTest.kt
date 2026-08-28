package org.jesperancinha.spring.flash19.transactional.configuration.controllers

import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.types.shouldNotBeSameInstanceAs
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.jesperancinha.spring.flash19.transactional.domain.Album
import org.jesperancinha.spring.flash19.transactional.repos.AlbumRepository
import org.jesperancinha.spring.flash19.transactional.services.AlbumServiceImpl
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode

@ExtendWith(MockKExtension::class)
@Execution(ExecutionMode.SAME_THREAD)
internal class AlbumControllerKotlinUnitTest {
    @MockK(relaxed = true)
    lateinit var albumRepository: AlbumRepository

    @InjectMockKs
    lateinit var albumService: AlbumServiceImpl

    @Test
    fun `should get all albums when called`(@MockK(relaxed = true) albums: List<Album>) {
        every { albums.isEmpty() } returns true
        every { albumRepository.findAll() } returns albums.shouldBeEmpty().toMutableList()
        albumService.allAlbums
            .shouldNotBeNull()
            .shouldBeEmpty() shouldNotBeSameInstanceAs albums
    }
}