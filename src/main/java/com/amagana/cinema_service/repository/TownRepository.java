package com.amagana.cinema_service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amagana.cinema_service.dto.TownResponse;
import com.amagana.cinema_service.entity.Town;

@Repository
public interface TownRepository extends JpaRepository<Town, Long> {

    Page<TownResponse> findAllBy(PageRequest pageRequest);
}
