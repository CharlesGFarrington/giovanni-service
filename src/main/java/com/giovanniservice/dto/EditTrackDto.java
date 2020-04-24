package com.giovanniservice.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * Model class for detailed track information.
 */
@Getter
@Setter
public class EditTrackDto {
    @NotNull
    private String title;
    private String songwriter;
    private Integer size;
    private Integer trackNumber;
}
