package com.example.backend_go2climb_iot.Application.tourists.service;


import com.example.backend_go2climb_iot.Application.tourists.domain.model.entity.Tourist;
import com.example.backend_go2climb_iot.Application.tourists.domain.persistence.TouristRepository;
import com.example.backend_go2climb_iot.Application.tourists.domain.service.TouristService;
import com.example.backend_go2climb_iot.Mapping.Exception.ResourceNotFoundException;
import com.example.backend_go2climb_iot.Mapping.Exception.ResourceValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service
public class TouristServiceIn implements TouristService {
    private static final String ENTITY = "Tourist";

    private final TouristRepository touristRepository;

    private final Validator validator;

    public TouristServiceIn(TouristRepository touristRepository, Validator validador) {
        this.touristRepository = touristRepository;
        this.validator = validador;
    }

    @Override
    public List<Tourist> getAll() {
        return touristRepository.findAll();
    }

    @Override
    public Page<Tourist> getAll(Pageable pageable) {
        return touristRepository.findAll(pageable);
    }

    @Override
    public Tourist create(Tourist tourist){
        Set<ConstraintViolation<Tourist>> violations = validator.validate(tourist);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, (Throwable) violations);

        Tourist touristWithEmail = touristRepository.findByEmail(tourist.getEmail());

        if (touristWithEmail != null)
            throw new ResourceValidationException(ENTITY,
                    "A tourist with the same email already exists.");

        Tourist touristWithPhoneNumber = touristRepository.findByPhoneNumber(tourist.getPhoneNumber());

        if (touristWithPhoneNumber != null)
            throw new ResourceValidationException(ENTITY,
                    "A tourist with the same phone number already exists.");

        return touristRepository.save(tourist);
    }
    @Override
    public Tourist update(Long touristId, Tourist request){
        Set<ConstraintViolation<Tourist>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, (Throwable) violations);

        return touristRepository.findById(touristId).map(tourist ->
                        touristRepository.save(tourist.withName(request.getName())
                                .withEmail(request.getEmail())
                                .withLastName(request.getLastName())
                                .withPassword(request.getPassword())
                                .withPhoneNumber(request.getPhoneNumber())
                                .withAddress(request.getAddress())
                                .withPhoto(request.getPhoto())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, touristId));
    }

    @Override
    public Tourist getInfoUserById(Long touristId){
        return touristRepository.findById(touristId).orElseThrow(() ->
                new ResourceNotFoundException(ENTITY, touristId));
    }

    @Override
    public ResponseEntity<?> delete(Long touristId) {
        return touristRepository.findById(touristId).map(
                tourist -> {
                    touristRepository.delete(tourist);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, touristId));
    }
}
