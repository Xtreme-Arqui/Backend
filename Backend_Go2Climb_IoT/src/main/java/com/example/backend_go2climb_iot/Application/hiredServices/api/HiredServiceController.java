package com.example.backend_go2climb_iot.Application.hiredServices.api;

import com.example.backend_go2climb_iot.Application.hiredServices.domain.service.HiredServiceService;
import com.example.backend_go2climb_iot.Application.hiredServices.mapping.HiredServiceMapper;
import com.example.backend_go2climb_iot.Application.hiredServices.resource.HiredServiceResource;
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
@Tag(name = "Hired services", description = "Read hired services")
@RestController
@RequestMapping("api/v1/hired-services")
public class HiredServiceController {
    private final HiredServiceService hiredServiceService;

    private final HiredServiceMapper mapper;


    public HiredServiceController(HiredServiceService hiredServiceService, HiredServiceMapper mapper) {
        this.hiredServiceService = hiredServiceService;
        this.mapper = mapper;
    }

    @Operation(summary = "Get All Hired services", description = "Get all hired services stored in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hired services found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = HiredServiceResource.class))})
    })
    @GetMapping
    public Page<HiredServiceResource> getAllHiredServices(Pageable pageable)
    {
        return mapper.modelListPage(hiredServiceService.getAll(), pageable);
    }

    @Operation(summary = "Get All Hired services by Id", description = "Get all hired services by Id stored in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hired services found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = HiredServiceResource.class))})
    })
    @GetMapping("{hiredServiceId}")
    public HiredServiceResource getHiredServiceById(@PathVariable Long hiredServiceId) {
        return mapper.toResource(hiredServiceService.getById(hiredServiceId));
    }

    @Operation(summary = "Get All Hired services by tourist Id", description = "Get all hired services by tourist Id stored in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hired services found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = HiredServiceResource.class))})
    })
    @GetMapping("touristId={touristId}")
    public Page<HiredServiceResource> getAllByTouristId(@PathVariable Long touristId, Pageable pageable) {
        return mapper.modelListPage(hiredServiceService.getAllByTouristId(touristId), pageable);
    }
}
