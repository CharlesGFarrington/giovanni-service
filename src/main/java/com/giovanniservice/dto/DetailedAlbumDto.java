package com.giovanniservice.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Model class for detailed album information.
 */
@Getter
@Setter
public class DetailedAlbumDto {
    @NotNull
    private String title;
    private List<DetailedTrackDto> tracks;
}
