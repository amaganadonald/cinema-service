package com.amagana.cinema_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.amagana.cinema_service.entity.Salle;

@Repository
public interface SalleRepository extends JpaRepository<Salle, Long> {

    @Query(value = "SELECT s FROM Salle s WHERE s.id IN :salles")
    List<Salle> getAllSalleByListId(List<Integer> salles);
}
