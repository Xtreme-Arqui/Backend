package com.example.backend_go2climb_iot.Application.tourists.domain.persistence;


import com.example.backend_go2climb_iot.Application.tourists.domain.model.entity.Tourist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TouristRepository  extends JpaRepository<Tourist, Long> {
    Tourist findByEmail(String email);
    Tourist findByPhoneNumber(int phoneNumber);
}
