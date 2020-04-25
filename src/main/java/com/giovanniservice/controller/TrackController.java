package com.giovanniservice.controller;

import com.giovanniservice.dto.EditTrackDto;
import com.giovanniservice.dto.TrackDto;
import com.giovanniservice.entity.Track;
import com.giovanniservice.service.TrackService;
import org.modelmapper.ModelMapper;
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
 * Controller for tracks.
 */
@RestController
@RequestMapping("albums/{albumId}/tracks")
public class TrackController {
    private TrackService trackService;
    private ModelMapper modelMapper;

    public TrackController(ModelMapper modelMapper, TrackService trackService) {
        this.trackService = trackService;
        this.modelMapper = modelMapper;
    }

    /**
     * Return all tracks in the album.
     * @param albumId the id of the album.
     * @return tracks.
     */
    @GetMapping
    public List<TrackDto> getTracks(@PathVariable Integer albumId) {
        List<Track> tracks = trackService.getTracks(albumId);
        return tracks.stream()
                .map(track -> modelMapper.map(track, TrackDto.class))
                .collect(Collectors.toList());
    }

    /**
     * Add a new track.
     * @param trackDto track to add.
     * @return the saved track.
     */
    @PostMapping
    public TrackDto createTrack(@PathVariable Integer albumId, @Valid @RequestBody EditTrackDto trackDto) {
        Track trackCreated = trackService.addTrack(albumId, trackDto);
        return modelMapper.map(trackCreated, TrackDto.class);
    }

    /**
     * Update track with specified track Id.
     * @param trackId track Id.
     * @param trackDto new track details.
     * @return the edited track.
     */
    @PatchMapping("/{trackId}")
    public TrackDto updateTrack(@PathVariable Integer trackId, @Valid @RequestBody EditTrackDto trackDto) {
        Track track = trackService.updateTrack(trackId, trackDto);
        return modelMapper.map(track, TrackDto.class);
    }

    /**
     * Delete the track with specified track Id.
     * @param trackId the track Id.
     */
    @DeleteMapping("/{trackId}")
    public void deleteTrack(@PathVariable Integer trackId) {
        trackService.deleteTrack(trackId);
    }
}
