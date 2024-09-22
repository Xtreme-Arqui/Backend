package com.example.backend_go2climb_iot.Application.hiredServices.resource;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class UpdateHiredServiceResource {
    private Long id;
    @NotNull
    private int amount;
    @NotNull
    private Double price;
    @NotNull
    @NotBlank
    private String scheduledDate;
    @NotBlank
    @NotNull
    private String status;
}
