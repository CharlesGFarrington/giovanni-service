package com.giovanniservice.repository;

import com.giovanniservice.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Integer> {

    List<Album> findAllByArtistId(Integer artistId);
}
