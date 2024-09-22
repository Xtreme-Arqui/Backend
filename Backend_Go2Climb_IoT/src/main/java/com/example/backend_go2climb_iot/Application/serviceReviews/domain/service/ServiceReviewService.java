package com.example.backend_go2climb_iot.Application.serviceReviews.domain.service;

import com.example.backend_go2climb_iot.Application.serviceReviews.domain.model.entity.ServiceReview;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ServiceReviewService {
    List<ServiceReview> getAll();
    ServiceReview getById(Long serviceReviewId);
    List<ServiceReview> getAllByServiceId(Long serviceId);
    List<ServiceReview> getAllByTouristId(Long touristId);

    ServiceReview create(Long serviceId, Long touristId, ServiceReview serviceReview);
    ServiceReview update(Long serviceId, Long touristId, Long serviceReviewId, ServiceReview serviceReview);
    ResponseEntity<?> delete(Long serviceId, Long touristId, Long serviceReviewId);
}
