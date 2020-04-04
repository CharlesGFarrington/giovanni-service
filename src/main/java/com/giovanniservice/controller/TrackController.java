package com.giovanniservice.controller;

import com.giovanniservice.dto.DetailedTrackDto;
import com.giovanniservice.entity.Track;
import com.giovanniservice.service.TrackService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
     * Add a new track.
     * @param detailedTrackDto track to add.
     * @return the saved track.
     */
    @PostMapping
    public DetailedTrackDto createTrack(@Valid @RequestBody DetailedTrackDto detailedTrackDto) {
        Track track = modelMapper.map(detailedTrackDto, Track.class);
        // TODO make this better.
        track.setId(null);
        Track trackCreated = trackService.addTrack(track);
        return modelMapper.map(trackCreated, DetailedTrackDto.class);
    }

    /**
     * Delete the track with specified track Id.
     * @param trackId the track Id.
     */
    @DeleteMapping("/{trackId}")
    public void deleteAlbum(@PathVariable Integer trackId) {
        trackService.deleteTrack(trackId);
    }
}
