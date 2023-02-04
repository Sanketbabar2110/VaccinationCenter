package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.VaccinationCenter;

@Repository
public interface VaccinationCenterRepo extends JpaRepository<VaccinationCenter, Integer> {

}
