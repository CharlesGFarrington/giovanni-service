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
public class ArtistDto {
    @NotNull
    private Integer id;
    @NotNull
    private String name;
    private List<AlbumDto> albums;
}
