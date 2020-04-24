package com.giovanniservice.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Model class for detailed track information.
 */
@Getter
@Setter
public class TrackDto extends EditTrackDto {
    private String blobKey;
    private Integer albumId;
}
