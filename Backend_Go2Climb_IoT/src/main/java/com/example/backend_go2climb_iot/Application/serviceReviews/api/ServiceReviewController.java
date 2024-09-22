package com.example.backend_go2climb_iot.Application.serviceReviews.api;


import com.example.backend_go2climb_iot.Application.serviceReviews.domain.service.ServiceReviewService;
import com.example.backend_go2climb_iot.Application.serviceReviews.mapping.ServiceReviewMapper;
import com.example.backend_go2climb_iot.Application.serviceReviews.resource.ServiceReviewResource;
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
@Tag(name = "Service reviews", description = "Read service reviews")
@RestController
@RequestMapping("api/v1/service-reviews")
public class ServiceReviewController {

    private final ServiceReviewService serviceReviewService;

    private final ServiceReviewMapper mapper;

    public ServiceReviewController(ServiceReviewService serviceReviewService, ServiceReviewMapper mapper) {
        this.serviceReviewService = serviceReviewService;
        this.mapper = mapper;
    }

    @Operation(summary = "Get All service reviews", description = "Get all service reviews stored in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Service reviews found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ServiceReviewResource.class))})
    })
    @GetMapping
    public Page<ServiceReviewResource> getAllServiceReviews(Pageable pageable) {
        return mapper.modelListPage(serviceReviewService.getAll(), pageable);
    }

    @Operation(summary = "Get Service reviews by tourist Id", description = "Get all service reviews by tourist Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Service reviews found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ServiceReviewResource.class))})
    })
    @GetMapping("touristId={touristId}")
    public Page<ServiceReviewResource> getAllByTouristId(@PathVariable Long touristId, Pageable pageable) {
        return mapper.modelListPage(serviceReviewService.getAllByTouristId(touristId), pageable);
    }
}
