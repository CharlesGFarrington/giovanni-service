package com.giovanniservice.service;

import com.giovanniservice.dto.EditAlbumDto;
import com.giovanniservice.dto.EditTrackDto;
import com.giovanniservice.entity.Album;
import com.giovanniservice.entity.Track;
import com.giovanniservice.repository.AlbumRepository;
import com.giovanniservice.repository.TrackRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

/**
 * Service class for albums
 */
@Service
public class AlbumService {
    private static String ALBUM_NOT_FOUND = "Could not find the album with id %d";
    private AlbumRepository albumRepository;
    private TrackRepository trackRepository;
    private ModelMapper modelMapper;

    public AlbumService(AlbumRepository albumRepository, TrackRepository trackRepository, ModelMapper modelMapper) {
        this.albumRepository = albumRepository;
        this.trackRepository = trackRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * Find the album with the given id.
     * @param albumId album Id.
     * @return album.
     */
    public Album getAlbum(Integer albumId) {
        return albumRepository.findById(albumId)
                .orElseThrow(() -> new EntityNotFoundException(String.format(ALBUM_NOT_FOUND, albumId)));
    }

    /**
     * Update the album with the given id.
     * @param albumId album Id.
     * @param albumDto new album details.
     * @return album.
     */
    public Album updateAlbum(Integer albumId, EditAlbumDto albumDto) {
        Album albumToUpdate = albumRepository.getOne(albumId);
        albumToUpdate.setTitle(albumDto.getTitle());
        albumToUpdate.setReleaseDate(albumDto.getReleaseDate());
        albumToUpdate.setAvailableToPublic(albumDto.getAvailableToPublic());
        return albumRepository.save(albumToUpdate);
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
        Integer numberOfTracks = trackRepository.countByAlbumId(albumId);
        track.setTrackNumber(numberOfTracks + 1);
        return trackRepository.save(track);
    }

    /**
     * Delete the track with the specified track id.
     * Reduce track numbers on other, higher-numbered tracks in the album.
     * @param trackId track id.
     * @return the updated album.
     */
    public Album deleteTrack(Integer albumId, Integer trackId) {
        Album albumToUpdate = albumRepository.getOne(albumId);
        Track trackToDelete = trackRepository.getOne(trackId);
        List<Track> tracks = albumToUpdate.getTracks();
        tracks.forEach(track -> {
            Integer trackNumber = track.getTrackNumber();
            if (trackNumber > trackToDelete.getTrackNumber()) {
                track.setTrackNumber(trackNumber - 1);
            }
        });
        tracks.remove(trackToDelete);
        trackRepository.delete(trackToDelete);
        return albumRepository.save(albumToUpdate);
    }

    /**
     * Delete the album with the specified album id.
     * @param albumId album id.
     */
    public void deleteAlbum(Integer albumId) {
        albumRepository.deleteById(albumId);
    }
}
