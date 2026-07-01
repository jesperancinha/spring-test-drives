package org.jesperancinha.sftd.mastery1.french.music.controller;

import org.jesperancinha.sftd.mastery1.french.music.api.ArtistService;
import org.jesperancinha.sftd.mastery1.french.music.domain.Artist;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("artist")
public class ArtistController {

    private final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @RequestMapping
    public @ResponseBody
    List<Artist> getAllArtists() {
        return artistService.getAllArtists();
    }

    @RequestMapping("/search")
    public @ResponseBody
    List<Artist> getAllArtists(
            @RequestParam
            final String param) {
        return artistService.getArtistsLike(param);
    }

    @RequestMapping("/delete/{id}")
    public @ResponseBody
    ResponseEntity<Object> deleteArtistById(
            @PathVariable
            final Long id) {
        artistService.deleteArtistById(id);
        return ResponseEntity.ok().build();
    }
}
