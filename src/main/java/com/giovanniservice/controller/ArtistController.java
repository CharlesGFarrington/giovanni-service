package com.giovanniservice.controller;

import com.giovanniservice.dto.AlbumDto;
import com.giovanniservice.dto.ArtistDto;
import com.giovanniservice.dto.EditAlbumDto;
import com.giovanniservice.dto.EditArtistDto;
import com.giovanniservice.entity.Album;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

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
     * Find all artists.
     * @return all artists.
     */
    @GetMapping
    public List<ArtistDto> getArtists() {
        List<Artist> artists = artistService.getArtists();
        return artists.stream()
                .map(artist -> modelMapper.map(artist, ArtistDto.class))
                .collect(Collectors.toList());
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
     * Create an album.
     * @param albumDto album to create.
     * @return the created album.
     */
    @PostMapping("/{artistId}/albums")
    public AlbumDto createAlbum(@PathVariable Integer artistId, @Valid @RequestBody EditAlbumDto albumDto) {
        Album albumCreated = artistService.addAlbum(artistId, albumDto);
        return modelMapper.map(albumCreated, AlbumDto.class);
    }

    /**
     * Find all albums belonging to the given artist.
     * @return all albums.
     */
    @GetMapping("/{artistId}/albums")
    public List<AlbumDto> getAlbums(@PathVariable Integer artistId) {
        List<Album> albums = artistService.getAlbums(artistId);
        return albums.stream()
                .map(album -> modelMapper.map(album, AlbumDto.class))
                .collect(Collectors.toList());
    }

    /**
     * Delete the artist with specified artist Id.
     * @param artistId the artist Id.
     */
    @DeleteMapping("/{artistId}")
    public void deleteArtist(@PathVariable Integer artistId) {
        artistService.deleteArtist(artistId);
    }
}
