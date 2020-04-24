package com.giovanniservice.service;

import com.giovanniservice.dto.EditTrackDto;
import com.giovanniservice.entity.Album;
import com.giovanniservice.entity.Track;
import com.giovanniservice.repository.TrackRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

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
     * Create a track.
     * @param trackDto the track to create.
     * @return the created track.
     */
    public Track addTrack(Integer albumId, EditTrackDto trackDto) {
        Track track = modelMapper.map(trackDto, Track.class);
        String trackBlobKey = UUID.randomUUID().toString();
        track.setBlobKey(trackBlobKey);
        track.setAlbum(Album.builder().id(albumId).build());
        return trackRepository.save(track);
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
        trackToUpdate.setSize(trackDto.getSize());
        return trackRepository.save(trackToUpdate);
    }

    /**
     * Delete the track with the specified track id.
     * @param trackId track id.
     */
    public void deleteTrack(Integer trackId) {
        trackRepository.deleteById(trackId);
    }
}
