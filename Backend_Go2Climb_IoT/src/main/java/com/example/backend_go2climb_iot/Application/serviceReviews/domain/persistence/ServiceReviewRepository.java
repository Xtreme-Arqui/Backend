package com.example.backend_go2climb_iot.Application.serviceReviews.domain.persistence;

import com.example.backend_go2climb_iot.Application.serviceReviews.domain.model.entity.ServiceReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceReviewRepository extends JpaRepository<ServiceReview, Long> {
    List<ServiceReview> findByServiceId(Long serviceId);

    Optional<ServiceReview> findByIdAndServiceId(Long id, Long serviceId);
    Page<ServiceReview> findByServiceId(Long serviceId, Pageable pageable);

    List<ServiceReview> findByTouristId(Long touristId);
    Optional<ServiceReview> findByIdAndTouristId(Long id, Long touristId);

    Optional<ServiceReview> findByIdAndServiceIdAndTouristId(Long id, Long serviceId, Long touristId);
}
