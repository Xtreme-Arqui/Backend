package com.example.backend_go2climb_iot.Application.tourists.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor

public class TouristResource {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private int phoneNumber;
    private String address;
    private String photo;
}
