package com.giovanniservice.controller;

import com.giovanniservice.dto.ArtistDto;
import com.giovanniservice.dto.EditArtistDto;
import com.giovanniservice.entity.Artist;
import com.giovanniservice.service.ArtistService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Controller class for artists.
 */
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("artists")
public class ArtistController {
    private ArtistService artistService;
    protected ModelMapper modelMapper;

    public ArtistController(ArtistService artistService, ModelMapper modelMapper) {
        this.artistService = artistService;
        this.modelMapper = modelMapper;
    }

    /**
     * Register an artist.
     * @param artistDto artist to register.
     * @return the registered artist.
     */
    @PostMapping
    public ArtistDto registerArtist(@Valid @RequestBody EditArtistDto artistDto) {
        Artist artistRegistered = artistService.registerArtist(artistDto);
        return modelMapper.map(artistRegistered, ArtistDto.class);
    }

    /**
     * Find artist with specified artist Id.
     * @param artistId the artistId.
     * @return the artist.
     */
    @GetMapping("/{artistId}")
    public ArtistDto getArtist(@PathVariable Integer artistId) {
        Artist artist = artistService.getArtist(artistId);
        return modelMapper.map(artist, ArtistDto.class);
    }

    /**
     * Update artist with specified artist Id.
     * @param artistId the artistId.
     * @param artistDto new artist details.
     * @return the edited artist.
     */
    @PatchMapping("/{artistId}")
    public ArtistDto updateArtist(@PathVariable Integer artistId, @Valid @RequestBody EditArtistDto artistDto) {
        Artist artist = artistService.updateArtist(artistId, artistDto);
        return modelMapper.map(artist, ArtistDto.class);
    }

    /**
     * Delete the artist with specified artist Id.
     * @param artistId the artist Id.
     */
    @DeleteMapping("/{artistId}")
    public void deleteAlbum(@PathVariable Integer artistId) {
        artistService.deleteArtist(artistId);
    }
}
