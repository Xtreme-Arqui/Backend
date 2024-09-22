package com.example.backend_go2climb_iot.Application.serviceReviews.api;


import com.example.backend_go2climb_iot.Application.serviceReviews.domain.service.ServiceReviewService;
import com.example.backend_go2climb_iot.Application.serviceReviews.mapping.ServiceReviewMapper;
import com.example.backend_go2climb_iot.Application.serviceReviews.resource.CreateServiceReviewResource;
import com.example.backend_go2climb_iot.Application.serviceReviews.resource.ServiceReviewResource;
import com.example.backend_go2climb_iot.Application.serviceReviews.resource.UpdateServiceReviewResource;
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
@Tag(name = "Service / Service reviews", description = "Read, create, update and delete service reviews by service Id")
@RestController
@RequestMapping("api/v1/services/{serviceId}/service-reviews")
public class ServiceServiceReviewController {

    private final ServiceReviewService serviceReviewService;

    private final ServiceReviewMapper mapper;

    public ServiceServiceReviewController(ServiceReviewService serviceReviewService, ServiceReviewMapper mapper) {
        this.serviceReviewService = serviceReviewService;
        this.mapper = mapper;
    }

    @Operation(summary = "Get All service reviews", description = "Get all service reviews by serviceId stored in database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Service reviews found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ServiceReviewResource.class))})
    })
    @GetMapping
    public Page<ServiceReviewResource> getAllByServiceId(@PathVariable Long serviceId, Pageable pageable) {
        return mapper.modelListPage(serviceReviewService.getAllByServiceId(serviceId), pageable);
    }

    @Operation(summary = "Create a service review", description = "Create a service review by serviceId and touristId in database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Service review created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ServiceReviewResource.class))})
    })
    @PostMapping("touristId={touristId}")
    public ServiceReviewResource createServiceReview(@PathVariable Long serviceId, @PathVariable Long touristId,
                                                   @RequestBody CreateServiceReviewResource resource) {
        return mapper.toResource(serviceReviewService.create(serviceId, touristId, mapper.toModel(resource)));
    }

    @Operation(summary = "Update a service review", description = "Update a service review in database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Service review updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ServiceReviewResource.class))})
    })
    @PutMapping("touristId={touristId}/{serviceReviewId}")
    public ServiceReviewResource updateServiceReview(@PathVariable Long serviceReviewId, @PathVariable Long touristId,
                                                   @PathVariable Long serviceId, @RequestBody UpdateServiceReviewResource resource) {
        return mapper.toResource(serviceReviewService.update(serviceId, touristId, serviceReviewId, mapper.toModel(resource)));
    }

    @Operation(summary = "Delete a service review", description = "Delete a service review from database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Service review deleted", content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("touristId={touristId}/{serviceReviewId}")
    public ResponseEntity<?> deleteServiceReview(@PathVariable Long serviceReviewId, @PathVariable Long touristId,
                                                @PathVariable Long serviceId) {
        return serviceReviewService.delete(serviceId, touristId, serviceReviewId);
    }
}
