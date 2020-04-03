package com.giovanniservice.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Model class for albums.
 */
@Getter
@Setter
public class AlbumDto {
    @NotNull
    private String title;
    private List<TrackDto> tracks;
}
