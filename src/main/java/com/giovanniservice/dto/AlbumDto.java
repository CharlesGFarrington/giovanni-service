package com.giovanniservice.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Model class for album information.
 */
@Getter
@Setter
public class AlbumDto extends EditAlbumDto {
    @NotNull
    private Integer id;
    @NotNull
    private String artistId;
    private List<TrackDto> tracks;

    private String artworkBlobKey;
}
