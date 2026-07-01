package org.jesperancinha.sftd.flash19.transactional.configuration.controllers

import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.types.shouldNotBeSameInstanceAs
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.jesperancinha.sftd.flash19.transactional.domain.Album
import org.jesperancinha.sftd.flash19.transactional.repos.AlbumRepository
import org.jesperancinha.sftd.flash19.transactional.services.AlbumServiceImpl
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
internal class AlbumControllerKotlinUnitTest {
    @MockK(relaxed = true)
    lateinit var albumRepository: AlbumRepository

    @InjectMockKs
    lateinit var albumService: AlbumServiceImpl

    @Test
    fun testGetAllAlbumsWhenCallThenGetAllAlbums(@MockK(relaxed = true) albums: List<Album>) {
        every { albums.isEmpty() } returns true
        every { albumRepository.findAll() } returns albums.shouldBeEmpty().toMutableList()
        albumService.allAlbums
            .shouldNotBeNull()
            .shouldBeEmpty() shouldNotBeSameInstanceAs albums
    }
}