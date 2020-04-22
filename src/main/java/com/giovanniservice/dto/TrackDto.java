package com.giovanniservice.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Model class for detailed track information.
 */
@Getter
@Setter
public class TrackDto {
    private String blobKey;
    private String name;
    private String songwriter;
    private Integer size;
    private Integer trackNumber;
    private Integer albumId;
}
