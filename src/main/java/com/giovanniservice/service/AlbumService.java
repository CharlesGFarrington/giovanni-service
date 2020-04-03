package com.giovanniservice.service;

import com.giovanniservice.entity.Album;
import com.giovanniservice.repository.AlbumRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * Service class for albums
 */
@Service
public class AlbumService {
    private static String ALBUM_NOT_FOUND = "Could not find the album with id %d";
    private AlbumRepository albumRepository;

    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    /**
     * Find all albums in the database.
     * @return albums.
     */
    public List<Album> getAlbums() {
        return albumRepository.findAll();
    }

    /**
     * Add an album.
     * @param album album to add.
     * @return the created album.
     */
    public Album addAlbum(Album album) {
        return albumRepository.save(album);
    }

    /**
     * Find album with the given id.
     * @return album.
     */
    public Album getAlbum(Integer albumId) {
        return albumRepository.findById(albumId)
                .orElseThrow(() -> new EntityNotFoundException(String.format(ALBUM_NOT_FOUND, albumId)));
    }

    /**
     * Delete the album with the specified album id.
     * @param albumId album id.
     */
    public void deleteAlbum(Integer albumId) {
        albumRepository.deleteById(albumId);
    }
}
