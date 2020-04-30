package com.giovanniservice.controller;

import com.giovanniservice.dto.AlbumDto;
import com.giovanniservice.dto.EditAlbumDto;
import com.giovanniservice.dto.EditTrackDto;
import com.giovanniservice.dto.TrackDto;
import com.giovanniservice.entity.Album;
import com.giovanniservice.entity.Track;
import com.giovanniservice.service.AlbumService;
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
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller class for albums.
 */
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("albums")
public class AlbumController {
    private AlbumService albumService;
    protected ModelMapper modelMapper;

    public AlbumController(AlbumService albumService, ModelMapper modelMapper) {
        this.albumService = albumService;
        this.modelMapper = modelMapper;
    }

    /**
     * Find album with specified album Id.
     * @param albumId the albumId.
     * @return the album.
     */
    @GetMapping("/{albumId}")
    public AlbumDto getAlbum(@PathVariable Integer albumId) {
        Album album = albumService.getAlbum(albumId);
        return modelMapper.map(album, AlbumDto.class);
    }

    /**
     * Update album with specified album Id.
     * @param albumId album Id.
     * @param albumDto new album details.
     * @return the edited album.
     */
    @PatchMapping("/{albumId}")
    public AlbumDto updateAlbum(@PathVariable Integer albumId, @Valid @RequestBody EditAlbumDto albumDto) {
        Album album = albumService.updateAlbum(albumId, albumDto);
        return modelMapper.map(album, AlbumDto.class);
    }

    /**
     * Return all tracks in the album.
     * @param albumId the id of the album.
     * @return tracks.
     */
    @GetMapping("/{albumId}/tracks")
    public List<TrackDto> getTracks(@PathVariable Integer albumId) {
        List<Track> tracks = albumService.getTracks(albumId);
        return tracks.stream()
                .map(track -> modelMapper.map(track, TrackDto.class))
                .collect(Collectors.toList());
    }

    /**
     * Add a new track to the album.
     * @param trackDto track to add.
     * @return the saved track.
     */
    @PostMapping("/{albumId}/tracks")
    public TrackDto createTrack(@PathVariable Integer albumId, @Valid @RequestBody EditTrackDto trackDto) {
        Track trackCreated = albumService.addTrack(albumId, trackDto);
        return modelMapper.map(trackCreated, TrackDto.class);
    }

    /**
     * Delete the album with specified album Id.
     * @param albumId the albumId.
     */
    @DeleteMapping("/{albumId}")
    public void deleteAlbum(@PathVariable Integer albumId) {
        albumService.deleteAlbum(albumId);
    }
}
