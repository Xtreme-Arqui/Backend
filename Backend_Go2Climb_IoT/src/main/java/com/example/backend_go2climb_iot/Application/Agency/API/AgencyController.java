package com.example.backend_go2climb_iot.Application.Agency.API;


import com.example.backend_go2climb_iot.Application.Agency.Domain.Service.AgencyService;
import com.example.backend_go2climb_iot.Application.Agency.Mapping.AgencyMapper;
import com.example.backend_go2climb_iot.Application.Agency.Resource.AgencyResource;
import com.example.backend_go2climb_iot.Application.Agency.Resource.CreateAgencyResource;
import com.example.backend_go2climb_iot.Application.Agency.Resource.UpdateAgencyResource;
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
@Tag(name = "Agencies", description = "Create, read, update and delete agencies")
@RestController
@RequestMapping(value = "api/v1/agencies")
public class AgencyController {
    private final AgencyService agencyService;
    private final AgencyMapper mapper;

    public AgencyController(AgencyService agencyService, AgencyMapper mapper) {
        this.agencyService = agencyService;
        this.mapper = mapper;
    }

    //funciona
    @Operation(summary = "Get All Agencies", description = "Get all agencies stored in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agencies found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AgencyResource.class))})
    })
    @GetMapping
    public Page<AgencyResource> getAllAgencies(Pageable pageable)
    {
        return mapper.modelListPage(agencyService.getAll(), pageable);
    }

    //funciona
    @Operation(summary = "Get Agency by Id", description = "Get an agency by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agency found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AgencyResource.class))})
    })
    @GetMapping("{agencyId}")
    public AgencyResource getInfoAgencyById(@PathVariable Long agencyId) {
        return mapper.toResource(agencyService.getInfoAgencyById(agencyId));
    }

    //funciona
    @Operation(summary = "Create Agency", description = "Create Agency in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agency created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AgencyResource.class)
                    ))
    })
    @PostMapping
    public AgencyResource createAgency(@RequestBody CreateAgencyResource resource){
        return mapper.toResource(agencyService.create(mapper.toModel(resource)));
    }

    //funciona
    @Operation(summary = "Update an Agency", description = "Update an Agency in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agency updated",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AgencyResource.class)
                    ))
    })
    @PutMapping("/{agencyId}")
    public AgencyResource updateAgency(@PathVariable Long agencyId, @RequestBody UpdateAgencyResource resource) {
        return mapper.toResource(agencyService.update(agencyId, mapper.toModel(resource)));
    }

    //funciona
    @Operation(summary = "Delete an Agency", description = "Delete an Agency from database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agency deleted", content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("{agencyId}")
    public ResponseEntity<?> deleteAgency(@PathVariable Long agencyId) {
        return agencyService.delete(agencyId);
    }
}
