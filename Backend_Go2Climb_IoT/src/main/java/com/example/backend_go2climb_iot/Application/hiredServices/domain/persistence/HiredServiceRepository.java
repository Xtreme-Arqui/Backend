package com.example.backend_go2climb_iot.Application.hiredServices.domain.persistence;

import com.example.backend_go2climb_iot.Application.hiredServices.domain.model.entity.HiredService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HiredServiceRepository extends JpaRepository<HiredService, Long> {

    List<HiredService> findByServiceId(Long serviceId);

    List<HiredService> findByTouristId(Long touristId);

    Optional<HiredService> findByIdAndServiceIdAndTouristId(Long id, Long serviceId, Long touristId);

    /*
    List<HiredService> findByCustomerId (Long customerId);
    Page<HiredService> findByCustomerId (Long customerId, Pageable pageable);

    List<HiredService> findByAgencyId (Long agencyId);
    Page<HiredService> findByAgencyId (Long agencyId, Pageable pageable);

    Optional<HiredService> findByIdAndCustomerId (Long id, Long customerId);
     */
}
