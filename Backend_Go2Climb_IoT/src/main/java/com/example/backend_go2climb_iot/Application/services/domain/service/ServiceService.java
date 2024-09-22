package com.example.backend_go2climb_iot.Application.services.domain.service;


import com.example.backend_go2climb_iot.Application.services.domain.model.entity.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ServiceService {

    List<Service> getAll();

    Service getInfoServiceById(Long serviceId);

    List<Service> getAllByAgencyId(Long agencyId);

    List<Service> getAllByIsOffer(int isOffer);

    List<Service> getAllByIsPopular(int isPopular);

    List<Service> getAllByText(String text);

    Page<Service> getAllByAgencyId(Long agencyId, Pageable pageable);

    Service create(Long agencyId, Service service);

    Service update(Long agencyId, Long serviceId, Service service);

    ResponseEntity<?> delete(Long agencyId, Long serviceId);

}
