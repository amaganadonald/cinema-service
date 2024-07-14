package com.amagana.cinema_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amagana.cinema_service.entity.Film;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long>{

}
