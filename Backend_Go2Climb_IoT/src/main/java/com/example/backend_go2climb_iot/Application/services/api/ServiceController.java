package com.example.backend_go2climb_iot.Application.services.api;

import com.example.backend_go2climb_iot.Application.services.domain.service.ServiceService;
import com.example.backend_go2climb_iot.Application.services.mapping.ServiceMapper;
import com.example.backend_go2climb_iot.Application.services.resource.ServiceResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@Tag(name = "Service", description = "Read services")
@RestController
@RequestMapping("api/v1/services")
public class ServiceController {
    private final ServiceService serviceService;
    private final ServiceMapper mapper;

    public ServiceController(ServiceService serviceService, ServiceMapper mapper) {
        this.serviceService = serviceService;
        this.mapper = mapper;
    }

    //funciona
    @Operation(summary = "Get All services", description = "Get all services stored in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Services found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ServiceResource.class))})
    })
    @GetMapping
    public Page<ServiceResource> getAllServices(Pageable pageable) {
        return mapper.modelListPage(serviceService.getAll(), pageable);
    }

    @Operation(summary = "Get All services filtered by offer", description = "Get All services filtered by offer stored in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Services found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ServiceResource.class))})
    })
    @GetMapping("isOffer={isOffer}")
    public Page<ServiceResource> getAllByIsOffer(@PathVariable int isOffer, Pageable pageable) {
        System.out.print("s");
        return mapper.modelListPage(serviceService.getAllByIsOffer(isOffer), pageable);
    }

    @Operation(summary = "Get All services filtered by popular", description = "Get All services filtered by popular stored in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Services found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ServiceResource.class))})
    })
    @GetMapping("isPopular={isPopular}")
    public Page<ServiceResource> getAllByIsPopular(@PathVariable int isPopular, Pageable pageable) {
        return mapper.modelListPage(serviceService.getAllByIsPopular(isPopular), pageable);
    }

    @Operation(summary = "Get All services filtered by text", description = "Get All services filtered by text stored in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Services found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ServiceResource.class))})
    })
    @GetMapping("name_like={text}")
    public Page<ServiceResource> getAllByText(@PathVariable String text, Pageable pageable) {
        return mapper.modelListPage(serviceService.getAllByText(text), pageable);
    }

    //funciona
    @Operation(summary = "Get All services by serviceId", description = "Get All services by serviceId stored in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Services found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ServiceResource.class))})
    })
    @GetMapping("{serviceId}")
    public ServiceResource getById (@PathVariable Long serviceId) {
        return mapper.toResource(serviceService.getInfoServiceById(serviceId));
    }
}
