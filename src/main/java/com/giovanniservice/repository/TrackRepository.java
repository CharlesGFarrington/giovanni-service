package com.giovanniservice.repository;

import com.giovanniservice.entity.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackRepository extends JpaRepository<Track, Integer> {

    List<Track> findAllByAlbumId(Integer albumId);
}
