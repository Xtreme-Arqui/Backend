package com.example.backend_go2climb_iot.Application.services.resource;


import com.example.backend_go2climb_iot.Application.Agency.Resource.AgencyResource;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class ServiceResource {
    private Long id;
    private String name;
    private String description;
    private String location;
    private int score;
    private float price;
    private float newPrice;
    private String creationDate;
    private String photos;
    private int isOffer;
    private int isPopular;
    private AgencyResource agency;
}
