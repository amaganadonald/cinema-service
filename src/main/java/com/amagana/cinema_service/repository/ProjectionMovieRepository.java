package com.amagana.cinema_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amagana.cinema_service.entity.ProjectionMovie;

@Repository
public interface ProjectionMovieRepository extends JpaRepository<ProjectionMovie, Long> {

}
