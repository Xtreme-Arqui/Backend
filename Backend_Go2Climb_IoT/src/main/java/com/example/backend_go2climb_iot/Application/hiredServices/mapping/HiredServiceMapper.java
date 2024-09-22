package com.example.backend_go2climb_iot.Application.hiredServices.mapping;

import com.example.backend_go2climb_iot.Application.hiredServices.domain.model.entity.HiredService;
import com.example.backend_go2climb_iot.Application.hiredServices.resource.CreateHiredServiceResource;
import com.example.backend_go2climb_iot.Application.hiredServices.resource.HiredServiceResource;
import com.example.backend_go2climb_iot.Application.hiredServices.resource.PatchHiredServiceResource;
import com.example.backend_go2climb_iot.Application.hiredServices.resource.UpdateHiredServiceResource;
import com.example.backend_go2climb_iot.Mapping.Mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
@Component
public class HiredServiceMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public HiredServiceResource toResource(HiredService model) { return mapper.map(model, HiredServiceResource.class); }

    public HiredService toModel(CreateHiredServiceResource resource) { return mapper.map(resource, HiredService.class); }

    public HiredService toModel(UpdateHiredServiceResource resource) { return mapper.map(resource, HiredService.class); }

    public HiredService toModel(PatchHiredServiceResource resource) { return mapper.map(resource, HiredService.class); }

    public Page<HiredServiceResource> modelListPage (List<HiredService> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, HiredServiceResource.class), pageable, modelList.size());
    }

}
