package com.example.backend_go2climb_iot.Application.Agency.Domain.Persistence;

import com.example.backend_go2climb_iot.Application.Agency.Domain.Model.Agency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgencyRepository extends JpaRepository<Agency, Long> {
    Agency findByEmail(String email);
    Agency findByPhoneNumber(int phoneNumber);
    Agency findByName(String name);
}
