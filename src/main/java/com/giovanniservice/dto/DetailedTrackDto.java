package com.giovanniservice.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Model class for detailed track information.
 */
@Getter
@Setter
public class DetailedTrackDto extends SimpleTrackDto {
    private String blobKey;
}
