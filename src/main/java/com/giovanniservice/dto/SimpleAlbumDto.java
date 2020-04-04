package com.giovanniservice.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Model class for simple album information.
 */
@Getter
@Setter
public class SimpleAlbumDto {
    @NotNull
    private String title;
    private List<SimpleTrackDto> tracks;
}
