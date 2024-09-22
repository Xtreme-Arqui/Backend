package com.example.backend_go2climb_iot.Application.serviceReviews.mapping;


import com.example.backend_go2climb_iot.Application.serviceReviews.domain.model.entity.ServiceReview;
import com.example.backend_go2climb_iot.Application.serviceReviews.resource.CreateServiceReviewResource;
import com.example.backend_go2climb_iot.Application.serviceReviews.resource.ServiceReviewResource;
import com.example.backend_go2climb_iot.Application.serviceReviews.resource.UpdateServiceReviewResource;
import com.example.backend_go2climb_iot.Mapping.Mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
@Component
public class ServiceReviewMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public ServiceReviewResource toResource(ServiceReview model) { return mapper.map(model, ServiceReviewResource.class); }

    public ServiceReview toModel(CreateServiceReviewResource resource) { return mapper.map(resource, ServiceReview.class); }

    public ServiceReview toModel(UpdateServiceReviewResource resource) { return mapper.map(resource, ServiceReview.class); }

    public Page<ServiceReviewResource> modelListPage(List<ServiceReview> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList, ServiceReviewResource.class), pageable, modelList.size());
    }
}
