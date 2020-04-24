package com.giovanniservice.service;

import com.giovanniservice.dto.AlbumDto;
import com.giovanniservice.dto.EditAlbumDto;
import com.giovanniservice.entity.Album;
import com.giovanniservice.entity.Artist;
import com.giovanniservice.repository.AlbumRepository;
import org.modelmapper.ModelMapper;
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
    private ModelMapper modelMapper;

    public AlbumService(AlbumRepository albumRepository, ModelMapper modelMapper) {
        this.albumRepository = albumRepository;
        this.modelMapper = modelMapper;
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
     * @param artistId artist Id.
     * @param albumDto album to add.
     * @return the created album.
     */
    public Album addAlbum(Integer artistId, EditAlbumDto albumDto) {
        Album album = modelMapper.map(albumDto, Album.class);
        album.setArtist(Artist.builder().id(artistId).build());
        return albumRepository.save(album);
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
