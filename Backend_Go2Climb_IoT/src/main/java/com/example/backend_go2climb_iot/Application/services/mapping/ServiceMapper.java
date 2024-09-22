package com.example.backend_go2climb_iot.Application.services.mapping;

import com.example.backend_go2climb_iot.Application.services.domain.model.entity.Service;
import com.example.backend_go2climb_iot.Application.services.resource.CreateServiceResource;
import com.example.backend_go2climb_iot.Application.services.resource.ServiceResource;
import com.example.backend_go2climb_iot.Application.services.resource.UpdateServiceResource;
import com.example.backend_go2climb_iot.Mapping.Mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
@Component
public class ServiceMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public ServiceResource toResource(Service model) { return mapper.map(model, ServiceResource.class); }

    public Service toModel(CreateServiceResource resource) { return mapper.map(resource, Service.class); }

    public Service toModel(UpdateServiceResource resource) { return mapper.map(resource, Service.class); }

    public Page<ServiceResource> modelListPage(List<Service> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList, ServiceResource.class), pageable, modelList.size());
    }

}
