package com.example.backend_go2climb_iot.Application.serviceReviews.resource;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;



@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateServiceReviewResource {
    @NotNull
    @NotBlank
    private String comment;

    @NotNull
    @NotBlank
    private String date;

    @NotNull
    private float score;
}
