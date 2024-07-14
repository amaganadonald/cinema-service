package com.amagana.cinema_service.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.amagana.cinema_service.entity.Spectators;

public interface SpectatorsRepository extends JpaRepository<Spectators, Long>, JpaSpecificationExecutor<Spectators> {

    @Query("SELECT s FROM Spectators s where bornDate = ?1") 
    Spectators findByBornDate(LocalDate localDate);
    
    List<Spectators> findByAgeAndSalary(int age, double salary);
}
