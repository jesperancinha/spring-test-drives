package org.jesperancinha.sftd.flash19.transactional.controllers;

import org.jesperancinha.sftd.flash19.transactional.dto.AlbumDto;
import org.jesperancinha.sftd.flash19.transactional.services.AlbumService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.GREEN;

@RestController
public class AlbumController {

    private final AlbumService albumService;

    public AlbumController(final AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping("/list/all")
    public List<AlbumDto> getAllAlbums() {
        return this.albumService.getAllAlbums();
    }

    @PostMapping("/create/album")
    public void createAlbum(
            @RequestHeader
            final String name,
            @RequestHeader
            final String artist,
            @RequestHeader
            final String publisher,
            @RequestHeader
            final Long year) {
        final var album = this.albumService.createAlbum(name, artist, publisher, year);
        GREEN.printGenericLn("Created album -> %s", album);
    }

    @PostMapping("/create/albumRollback")
    public void createAlbumRollBack(
            @RequestHeader
            final String name,
            @RequestHeader
            final String artist,
            @RequestHeader
            final String publisher,
            @RequestHeader
            final Long year) {
        final var album = this.albumService.createAlbumRollBack(name, artist, publisher, year);
        GREEN.printGenericLn("Created album -> %s", album);
    }

    @PutMapping("/update/album")
    public void updateAlbum(
            @RequestBody
            final AlbumDto incomingAlbum) {
        final var album = this.albumService.updateAlbum(incomingAlbum);
        GREEN.printGenericLn("Created album -> %s", album);
    }

    @DeleteMapping("/delete/album/{id}")
    public void createAlbum(
            @PathVariable
            final Long id) {
        final var deleted = this.albumService.deleteAlbumByIdI(id);
        GREEN.printGenericLn("Deleted album with Id = %d -> %s", id, deleted);
    }


}
