package com.giovanniservice.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Model class for simple track information.
 */
@Getter
@Setter
public class SimpleTrackDto {
    private String name;
    private Integer size;
    private Integer trackNumber;
    private Integer albumId;
}
