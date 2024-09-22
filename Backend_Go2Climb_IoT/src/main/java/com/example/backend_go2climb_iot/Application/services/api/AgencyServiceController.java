package com.example.backend_go2climb_iot.Application.services.api;


import com.example.backend_go2climb_iot.Application.services.domain.service.ServiceService;
import com.example.backend_go2climb_iot.Application.services.mapping.ServiceMapper;
import com.example.backend_go2climb_iot.Application.services.resource.CreateServiceResource;
import com.example.backend_go2climb_iot.Application.services.resource.ServiceResource;
import com.example.backend_go2climb_iot.Application.services.resource.UpdateServiceResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@Tag(name = "Agency / Services", description = "Read, create, update and delete services by agency Id")
@RestController
@RequestMapping("api/v1/agencies/{agencyId}/services")
public class AgencyServiceController {

    private final ServiceService serviceService;

    private final ServiceMapper mapper;

    public AgencyServiceController(ServiceService serviceService, ServiceMapper mapper) {
        this.serviceService = serviceService;
        this.mapper = mapper;
    }

    //funciona
    @Operation(summary = "Get All services", description = "Get all services by agencyId stored in database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Services found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ServiceResource.class))})
    })
    @GetMapping
    public Page<ServiceResource> getAllServicesByAgencyId(@PathVariable Long agencyId, Pageable pageable) {
        return mapper.modelListPage(serviceService.getAllByAgencyId(agencyId), pageable);
    }

    //funciona
    @Operation(summary = "Create a service", description = "Create a service by agencyId in database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Service created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ServiceResource.class))})
    })
    @PostMapping
    public ServiceResource createService(@PathVariable Long agencyId, @RequestBody CreateServiceResource resource) {
        return mapper.toResource(serviceService.create(agencyId, mapper.toModel(resource)));
    }

    //funciona
    @Operation(summary = "Update a service", description = "Update a service in database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Service updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ServiceResource.class))})
    })
    @PutMapping("{serviceId}")
    public ServiceResource updateService(@PathVariable Long serviceId,
                                         @PathVariable Long agencyId, @RequestBody UpdateServiceResource resource) {
        return mapper.toResource(serviceService.update(agencyId, serviceId, mapper.toModel(resource)));
    }

    //funciona
    @Operation(summary = "Delete a service", description = "Delete a service from database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Service deleted", content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("{serviceId}")
    public ResponseEntity<?> deleteService(@PathVariable Long agencyId, @PathVariable Long serviceId) {
        return serviceService.delete(agencyId, serviceId);
    }
}
