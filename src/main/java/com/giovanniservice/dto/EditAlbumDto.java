package com.giovanniservice.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * Model class for album information on create.
 */
@Getter
@Setter
public class EditAlbumDto {
    @NotNull
    private String title;
}
