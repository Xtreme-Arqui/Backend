package com.example.backend_go2climb_iot.Application.tourists.mapping;

import com.example.backend_go2climb_iot.Application.tourists.domain.model.entity.Tourist;
import com.example.backend_go2climb_iot.Application.tourists.resource.CreateTouristResource;
import com.example.backend_go2climb_iot.Application.tourists.resource.TouristResource;
import com.example.backend_go2climb_iot.Application.tourists.resource.UpdateTouristResource;
import com.example.backend_go2climb_iot.Mapping.Mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
@Component
public class TouristMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;


    public TouristResource toResource(Tourist model) { return mapper.map(model, TouristResource.class); }

    public Tourist toModel(CreateTouristResource resource) { return mapper.map(resource, Tourist.class); }

    public Tourist toModel(UpdateTouristResource resource) { return mapper.map(resource, Tourist.class); }

    public Page<TouristResource> modelListPage(List<Tourist> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList, TouristResource.class), pageable, modelList.size());
    }
}
