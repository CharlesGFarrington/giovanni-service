package com.giovanniservice.service;

import com.giovanniservice.entity.Track;
import com.giovanniservice.repository.TrackRepository;
import org.springframework.stereotype.Service;

/**
 * Service class for tracks.
 */
@Service
public class TrackService {
    private TrackRepository trackRepository;

    public TrackService(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    /**
     * Create a track.
     * @param track the track to create.
     * @return the created track.
     */
    public Track addTrack(Track track) {
        return trackRepository.save(track);
    }

    /**
     * Delete the track with the specified track id.
     * @param trackId track id.
     */
    public void deleteTrack(Integer trackId) {
        trackRepository.deleteById(trackId);
    }
}
