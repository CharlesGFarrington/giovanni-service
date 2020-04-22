package com.giovanniservice.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Entity class for tracks.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Track {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String songwriter;

    private Integer size;

    private Integer trackNumber;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_ALBUM_ID"))
    private Album album;

    private String blobKey;
}
