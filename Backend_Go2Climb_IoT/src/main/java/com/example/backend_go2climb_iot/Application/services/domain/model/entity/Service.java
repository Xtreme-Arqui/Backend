package com.example.backend_go2climb_iot.Application.services.domain.model.entity;


import com.example.backend_go2climb_iot.Application.Agency.Domain.Model.Agency;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;



@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "services")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String description;

    @NotNull
    @NotBlank
    private String location;

    @NotNull
    private int score;

    @NotNull
    private float price;

    @NotNull
    private float newPrice;

    @NotNull
    @NotBlank
    private String creationDate;

    @NotNull
    @NotBlank
    private String photos;

    @NotNull
    private int isOffer;

    @NotNull
    private int isPopular;

    //Relationships
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "agency_id", nullable = false)
    private Agency agency;

}
