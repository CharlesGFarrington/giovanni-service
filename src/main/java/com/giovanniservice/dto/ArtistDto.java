package com.giovanniservice.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Model class for artist information.
 */
@Getter
@Setter
public class ArtistDto extends EditArtistDto {
    @NotNull
    private Integer id;
    private List<AlbumDto> albums;
}
