package com.giovanniservice.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * Model class for artist information on registration.
 */
@Getter
@Setter
public class RegisterArtistDto {
    @NotNull
    private String name;
}
