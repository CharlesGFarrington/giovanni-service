package com.giovanniservice.service;

import com.giovanniservice.dto.EditTrackDto;
import com.giovanniservice.entity.Track;
import com.giovanniservice.repository.AlbumRepository;
import com.giovanniservice.repository.TrackRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Service class for tracks.
 */
@Service
public class TrackService {
    private TrackRepository trackRepository;
    private AlbumRepository albumRepository;
    private ModelMapper modelMapper;

    public TrackService(TrackRepository trackRepository, AlbumRepository albumRepository, ModelMapper modelMapper) {
        this.trackRepository = trackRepository;
        this.albumRepository = albumRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * Find all tracks in the album.
     * @param albumId the album.
     * @return all tracks in the album.
     */
    public List<Track> getTracks(Integer albumId) {
        return trackRepository.findAllByAlbumId(albumId);
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
        track.setAlbum(albumRepository.getOne(albumId));
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
