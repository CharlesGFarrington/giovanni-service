package com.giovanniservice.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * Model class for detailed track information.
 */
@Getter
@Setter
public class TrackDto extends EditTrackDto {
    @NotNull
    private Integer id;
    private String blobKey;
    private Integer albumId;
    private Integer size;
}
