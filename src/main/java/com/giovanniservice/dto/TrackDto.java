package com.giovanniservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrackDto {
    private String name;
    private Integer size;
    private Integer trackNumber;
    private Integer albumId;
    private String blobKey;
}
