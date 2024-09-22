package com.example.backend_go2climb_iot.Application.Agency.Service;


import com.example.backend_go2climb_iot.Application.Agency.Domain.Model.Agency;
import com.example.backend_go2climb_iot.Application.Agency.Domain.Persistence.AgencyRepository;
import com.example.backend_go2climb_iot.Application.Agency.Domain.Service.AgencyService;
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
public class AgencyServiceIn implements AgencyService {
    private static final String ENTITY = "Agency";

    private final AgencyRepository agencyRepository;

    private final Validator validator;

    public AgencyServiceIn(AgencyRepository agencyRepository, Validator validator) {
        this.agencyRepository = agencyRepository;
        this.validator = validator;
    }

    @Override
    public List<Agency> getAll() {
        return agencyRepository.findAll();
    }

    @Override
    public Page<Agency> getAll(Pageable pageable) {
        return agencyRepository.findAll(pageable);
    }

    @Override
    public Agency create(Agency agency){
        Set<ConstraintViolation<Agency>> violations = validator.validate(agency);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, (Throwable) violations);

        Agency agencyWithEmail = agencyRepository.findByEmail(agency.getEmail());

        if (agencyWithEmail != null)
            throw new ResourceValidationException(ENTITY,
                    "An agency with the same email already exists.");

        Agency agencyWithPhoneNumber = agencyRepository.findByPhoneNumber(agency.getPhoneNumber());

        if (agencyWithPhoneNumber != null)
            throw new ResourceValidationException(ENTITY,
                    "An agency with the same phone number already exists.");

        return agencyRepository.save(agency);
    }
    @Override
    public Agency update(Long agencyId, Agency request){
        Set<ConstraintViolation<Agency>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, (Throwable) violations);

        return agencyRepository.findById(agencyId).map(agency ->
                        agencyRepository.save(agency.withName(request.getName())
                                .withEmail(request.getEmail())
                                .withPassword(request.getPassword())
                                .withPhoneNumber(request.getPhoneNumber())
                                .withDescription(request.getDescription())
                                .withLocation(request.getLocation())
                                .withRuc(request.getRuc())
                                .withPhoto(request.getPhoto())
                                .withScore(request.getScore())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, agencyId));
    }

    @Override
    public Agency getInfoAgencyById(Long agencyId){
        return agencyRepository.findById(agencyId).orElseThrow(() ->
                new ResourceNotFoundException(ENTITY, agencyId));
    }


    @Override
    public Agency getInfoAgencyByName(String agencyName){
        return agencyRepository.findByName(agencyName);
    }

    @Override
    public ResponseEntity<?> delete(Long agencyId) {
        return agencyRepository.findById(agencyId).map(
                agency -> {
                    agencyRepository.delete(agency);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, agencyId));
    }
}
