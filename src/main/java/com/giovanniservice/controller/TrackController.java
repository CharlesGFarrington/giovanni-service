package com.giovanniservice.controller;

import com.giovanniservice.dto.EditTrackDto;
import com.giovanniservice.dto.TrackDto;
import com.giovanniservice.entity.Track;
import com.giovanniservice.service.TrackService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Controller for tracks.
 */
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("tracks")
public class TrackController {
    private TrackService trackService;
    private ModelMapper modelMapper;

    public TrackController(ModelMapper modelMapper, TrackService trackService) {
        this.trackService = trackService;
        this.modelMapper = modelMapper;
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
}
