package com.giovanniservice.service;

import com.giovanniservice.dto.EditAlbumDto;
import com.giovanniservice.dto.EditArtistDto;
import com.giovanniservice.entity.Album;
import com.giovanniservice.entity.Artist;
import com.giovanniservice.repository.AlbumRepository;
import com.giovanniservice.repository.ArtistRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * Service class for artists
 */
@Service
public class ArtistService {
    private static String ARTIST_NOT_FOUND = "Could not find the artist with id %d";
    private ArtistRepository artistRepository;
    private AlbumRepository albumRepository;
    private ModelMapper modelMapper;

    public ArtistService(ArtistRepository artistRepository, AlbumRepository albumRepository, ModelMapper modelMapper) {
        this.artistRepository = artistRepository;
        this.albumRepository = albumRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * Find all artists.
     * @return artists.
     */
    public List<Artist> getArtists() {
        return artistRepository.findAll();
    }


    /**
     * Add an artist.
     * @param artistDto artist to add.
     * @return the created artist.
     */
    public Artist registerArtist(EditArtistDto artistDto) {
        Artist artist = modelMapper.map(artistDto, Artist.class);
        return artistRepository.save(artist);
    }

    /**
     * Find the artist with the given id.
     * @param artistId artist Id.
     * @return artist.
     */
    public Artist getArtist(Integer artistId) {
        return artistRepository.findById(artistId)
                .orElseThrow(() -> new EntityNotFoundException(String.format(ARTIST_NOT_FOUND, artistId)));
    }

    /**
     * Update the artist with the given id.
     * @param artistId artist Id.
     * @param artist new artist details.
     * @return artist.
     */
    public Artist updateArtist(Integer artistId, EditArtistDto artist) {
        Artist artistToUpdate = artistRepository.getOne(artistId);
        artistToUpdate.setName(artist.getName());
        artistToUpdate.setDescription(artist.getDescription());
        return artistRepository.save(artistToUpdate);
    }

    /**
     * Add an album.
     * @param artistId artist Id.
     * @param albumDto album to add.
     * @return the created album.
     */
    public Album addAlbum(Integer artistId, EditAlbumDto albumDto) {
        Album album = modelMapper.map(albumDto, Album.class);
        album.setArtist(artistRepository.getOne(artistId));
        return albumRepository.save(album);
    }

    /**
     * Find all albums for the given artist.
     * @param artistId the artist Id.
     * @return albums.
     */
    public List<Album> getAlbums(Integer artistId) {
        return albumRepository.findAllByArtistId(artistId);
    }

    /**
     * Delete the artist with the specified id.
     * @param artistId artist id.
     */
    public void deleteArtist(Integer artistId) {
        artistRepository.deleteById(artistId);
    }
}
