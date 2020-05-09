package com.giovanniservice.service;

import com.giovanniservice.dto.EditTrackDto;
import com.giovanniservice.entity.Track;
import com.giovanniservice.repository.TrackRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 * Service class for tracks.
 */
@Service
public class TrackService {
    private TrackRepository trackRepository;
    private ModelMapper modelMapper;

    public TrackService(TrackRepository trackRepository, ModelMapper modelMapper) {
        this.trackRepository = trackRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * Update the track with the given id.
     * @return track.
     */
    public Track updateTrack(Integer trackId, EditTrackDto trackDto) {
        Track trackToUpdate = trackRepository.getOne(trackId);
        trackToUpdate.setTitle(trackDto.getTitle());
        trackToUpdate.setSongwriter(trackDto.getSongwriter());
        trackToUpdate.setTrackNumber(trackDto.getTrackNumber());
        trackToUpdate.setLyrics(trackDto.getLyrics());
        trackToUpdate.setAvailableToPublic(trackDto.getAvailableToPublic());
        return trackRepository.save(trackToUpdate);
    }
}
