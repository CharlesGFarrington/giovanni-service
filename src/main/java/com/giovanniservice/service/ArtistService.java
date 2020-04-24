package com.giovanniservice.service;

import com.giovanniservice.dto.EditArtistDto;
import com.giovanniservice.entity.Artist;
import com.giovanniservice.repository.ArtistRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

/**
 * Service class for artists
 */
@Service
public class ArtistService {
    private static String ARTIST_NOT_FOUND = "Could not find the artist with id %d";
    private ArtistRepository artistRepository;
    private ModelMapper modelMapper;

    public ArtistService(ArtistRepository artistRepository, ModelMapper modelMapper) {
        this.artistRepository = artistRepository;
        this.modelMapper = modelMapper;
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
