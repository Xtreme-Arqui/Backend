package com.example.backend_go2climb_iot.Application.Agency.Domain.Service;

import com.example.backend_go2climb_iot.Application.Agency.Domain.Model.Agency;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AgencyService {
    List<Agency> getAll();

    Page<Agency> getAll(Pageable pageable);

    // Create a new agency
    Agency create(Agency agency);

    // Update agency
    Agency update(Long agencyId, Agency agency);

    // Get info agency by id
    Agency getInfoAgencyById(Long agencyId);

    Agency getInfoAgencyByName(String agencyName);

    ResponseEntity<?> delete(Long agencyId);
}
