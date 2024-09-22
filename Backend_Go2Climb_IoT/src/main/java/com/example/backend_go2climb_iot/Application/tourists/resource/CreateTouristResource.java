package com.example.backend_go2climb_iot.Application.tourists.resource;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;



@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateTouristResource {
    @NotBlank
    @NotNull
    private String name;
    @NotBlank
    @NotNull
    private String lastName;
    @NotBlank
    @NotNull
    private String email;
    @NotBlank
    @NotNull
    private String password;
    @NotNull
    private int phoneNumber;
    @NotBlank
    @NotNull
    private String address;
    @NotBlank
    @NotNull
    private String photo;
}
