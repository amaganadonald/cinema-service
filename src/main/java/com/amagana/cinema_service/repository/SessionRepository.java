package com.amagana.cinema_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amagana.cinema_service.entity.Session;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

}
