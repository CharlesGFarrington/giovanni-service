package com.giovanniservice.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * Model class for album information on create.
 */
@Getter
@Setter
public class EditAlbumDto {
    @NotNull
    @NotEmpty
    private String title;
    private Date releaseDate;
    @NotNull
    private Boolean availableToPublic;
    private String artworkBlobKey;
}
