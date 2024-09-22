package com.example.backend_go2climb_iot.Application.Agency.Mapping;

import com.example.backend_go2climb_iot.Application.Agency.Domain.Model.Agency;
import com.example.backend_go2climb_iot.Application.Agency.Resource.AgencyResource;
import com.example.backend_go2climb_iot.Application.Agency.Resource.CreateAgencyResource;
import com.example.backend_go2climb_iot.Application.Agency.Resource.UpdateAgencyResource;
import com.example.backend_go2climb_iot.Mapping.Mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
@Component

public class AgencyMapper  implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public AgencyResource toResource(Agency model) { return mapper.map(model, AgencyResource.class); }

    public Agency toModel(CreateAgencyResource resource) { return mapper.map(resource, Agency.class); }

    public Agency toModel(UpdateAgencyResource resource) { return mapper.map(resource, Agency.class); }

    public Page<AgencyResource> modelListPage(List<Agency> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList, AgencyResource.class), pageable, modelList.size());
    }
}
