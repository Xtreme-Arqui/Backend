package com.example.backend_go2climb_iot.Application.services.service;


import com.example.backend_go2climb_iot.Application.Agency.Domain.Persistence.AgencyRepository;
import com.example.backend_go2climb_iot.Application.services.domain.model.entity.Service;
import com.example.backend_go2climb_iot.Application.services.domain.persistence.ServiceRepository;
import com.example.backend_go2climb_iot.Application.services.domain.service.ServiceService;
import com.example.backend_go2climb_iot.Mapping.Exception.ResourceNotFoundException;
import com.example.backend_go2climb_iot.Mapping.Exception.ResourceValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;


@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {

    private static final String ENTITY = "Service";

    private final ServiceRepository serviceRepository;

    private final AgencyRepository agencyRepository;

    private final Validator validator;

    public ServiceServiceImpl(ServiceRepository serviceRepository, AgencyRepository agencyRepository, Validator validator) {
        this.serviceRepository = serviceRepository;
        this.agencyRepository = agencyRepository;
        this.validator = validator;
    }


    @Override
    public List<Service> getAll() {
        return serviceRepository.findAll();
    }

    @Override
    public Service getInfoServiceById(Long serviceId) {
        return serviceRepository.findById(serviceId).orElseThrow(() ->
                new ResourceNotFoundException(ENTITY, serviceId));
    }

    @Override
    public List<Service> getAllByAgencyId(Long agencyId) {
        return serviceRepository.findByAgencyId(agencyId);
    }

    @Override
    public List<Service> getAllByIsOffer(int isOffer) {
        return serviceRepository.findByIsOffer(isOffer);
    }

    @Override
    public List<Service> getAllByIsPopular(int isPopular) {
        return serviceRepository.findByIsPopular(isPopular);
    }

    @Override
    public List<Service> getAllByText(String text) {
        return serviceRepository.findByText(text);
    }

    @Override
    public Page<Service> getAllByAgencyId(Long agencyId, Pageable pageable) {
        return serviceRepository.findByAgencyId(agencyId, pageable);
    }

    @Override
    public Service create(Long agencyId, Service service) {
        Set<ConstraintViolation<Service>> violations = validator.validate(service);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, (Throwable) violations);

        return agencyRepository.findById(agencyId).map(agency -> {
            service.setAgency(agency);
            return serviceRepository.save(service);
        }).orElseThrow(() -> new ResourceNotFoundException("Agency", agencyId));
    }

    @Override
    public Service update(Long agencyId, Long serviceId, Service service) {
        Set<ConstraintViolation<Service>> violations = validator.validate(service);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, (Throwable) violations);

        if(!agencyRepository.existsById(agencyId))
            throw new ResourceNotFoundException("Agency", agencyId);

        return serviceRepository.findById(serviceId).map(existingService ->
                serviceRepository.save(existingService.withName(service.getName())
                        .withDescription(service.getDescription())
                        .withLocation(service.getLocation())
                        .withScore(service.getScore())
                        .withPrice(service.getPrice())
                        .withNewPrice(service.getNewPrice())
                        .withPhotos(service.getPhotos())
                        .withIsOffer(service.getIsOffer())
                        .withIsPopular(service.getIsPopular())
                        .withCreationDate(service.getCreationDate())))
                .orElseThrow(() -> new ResourceNotFoundException("Service", serviceId));
    }

    @Override
    public ResponseEntity<?> delete(Long agencyId, Long serviceId) {
        return serviceRepository.findByIdAndAgencyId(serviceId, agencyId).map(service -> {
            serviceRepository.delete(service);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, serviceId));
    }
}
