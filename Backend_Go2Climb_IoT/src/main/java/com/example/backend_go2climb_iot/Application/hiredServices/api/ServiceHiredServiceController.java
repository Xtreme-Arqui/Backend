package com.example.backend_go2climb_iot.Application.hiredServices.api;

import com.example.backend_go2climb_iot.Application.hiredServices.domain.service.HiredServiceService;
import com.example.backend_go2climb_iot.Application.hiredServices.mapping.HiredServiceMapper;
import com.example.backend_go2climb_iot.Application.hiredServices.resource.CreateHiredServiceResource;
import com.example.backend_go2climb_iot.Application.hiredServices.resource.HiredServiceResource;
import com.example.backend_go2climb_iot.Application.hiredServices.resource.UpdateHiredServiceResource;
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
@Tag(name = "Service / Hired services", description = "Read, create, update and delete hired services by service Id")
@RestController
@RequestMapping("api/v1/services/{serviceId}/hired-services")
public class ServiceHiredServiceController {

    private final HiredServiceService hiredServiceService;

    private final HiredServiceMapper mapper;

    public ServiceHiredServiceController(HiredServiceService hiredServiceService, HiredServiceMapper mapper) {
        this.hiredServiceService = hiredServiceService;
        this.mapper = mapper;
    }

    @Operation(summary = "Get All agency reviews", description = "Get all agency reviews by agencyId stored in database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agency reviews found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = HiredServiceResource.class))})
    })
    @GetMapping
    public Page<HiredServiceResource> getAllByServiceId(@PathVariable Long serviceId, Pageable pageable) {
        return mapper.modelListPage(hiredServiceService.getAllByServiceId(serviceId), pageable);
    }

    @Operation(summary = "Create a hired service", description = "Create a hired service by serviceId and touristId in database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hired service created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = HiredServiceResource.class))})
    })
    @PostMapping("touristId={touristId}")
    public HiredServiceResource createHiredService(@PathVariable Long serviceId, @PathVariable Long touristId,
                                                     @RequestBody CreateHiredServiceResource resource) {
        return mapper.toResource(hiredServiceService.create(serviceId, touristId, mapper.toModel(resource)));
    }

    @Operation(summary = "Update a hired service", description = "Update a hired service in database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hired service updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = HiredServiceResource.class))})
    })
    @PutMapping("touristId={touristId}/{hiredServiceId}")
    public HiredServiceResource updateHiredService(@PathVariable Long hiredServiceId, @PathVariable Long touristId,
                                                     @PathVariable Long serviceId, @RequestBody UpdateHiredServiceResource resource) {
        return mapper.toResource(hiredServiceService.update(serviceId, touristId, hiredServiceId, mapper.toModel(resource)));
    }

    /*
    @PatchMapping("touristId={touristId}/{hiredServiceId}")
    public HiredServiceResource patchHiredService(@PathVariable Long hiredServiceId, @PathVariable Long touristId,
                                                  @PathVariable Long serviceId, @RequestBody PatchHiredServiceResource resource) {
        return mapper.toResource(hiredServiceService.patch(serviceId, touristId, hiredServiceId, mapper.toModel(resource)));
    }*/

    @Operation(summary = "Delete a hired service", description = "Delete a hired service from database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hired service deleted", content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("touristId={touristId}/{hiredServiceId}")
    public ResponseEntity<?> deleteHiredService(@PathVariable Long hiredServiceId, @PathVariable Long touristId,
                                                 @PathVariable Long serviceId) {
        return hiredServiceService.delete(serviceId, touristId, hiredServiceId);
    }
}
