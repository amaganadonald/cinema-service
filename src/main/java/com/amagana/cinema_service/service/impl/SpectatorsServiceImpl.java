package com.amagana.cinema_service.service.impl;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.amagana.cinema_service.entity.Spectators;
import com.amagana.cinema_service.repository.SpectatorsRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SpectatorsServiceImpl implements SpectatorsService {

    private final SpectatorsRepository spectatorsRepository;

    private Specification<Spectators> getSpecification(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("firstName"), name);
    
    }

    @Override
    public List<Spectators> getAllSpectatorsByFirname(String name) {
        Specification<Spectators> specification = getSpecification(name);
        return spectatorsRepository.findAll(specification);
    }

}
