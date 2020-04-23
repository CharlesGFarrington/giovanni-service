package com.giovanniservice.service;

import com.giovanniservice.dto.ArtistDto;
import com.giovanniservice.entity.Artist;
import com.giovanniservice.repository.ArtistRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

/**
 * Service class for artists
 */
@Service
public class ArtistService {
    private static String ARTIST_NOT_FOUND = "Could not find the artist with id %d";
    private ArtistRepository artistRepository;

    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    /**
     * Add an artist.
     * @param artist artist to add.
     * @return the created artist.
     */
    public Artist registerArtist(Artist artist) {
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
    public Artist updateArtist(Integer artistId, ArtistDto artist) {
        Artist artistToUpdate = artistRepository.getOne(artistId);
        artistToUpdate.setName(artist.getName());
        return artistRepository.save(artistToUpdate);
    }

    /**
     * Delete the artist with the specified id.
     * @param artistId artist id.
     */
    public void deleteArtist(Integer artistId) {
        artistRepository.deleteById(artistId);
    }
}
