package com.amagana.cinema_service.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.amagana.cinema_service.dto.SalleRequestDto;
import com.amagana.cinema_service.dto.SalleResponseDto;
import com.amagana.cinema_service.entity.Salle;
import com.amagana.cinema_service.repository.SalleRepository;
import com.amagana.cinema_service.service.SalleService;
import com.amagana.cinema_service.utils.SalleMapper;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class SalleServiceImpl implements SalleService {

    private final SalleRepository salleRepository;
    private final SalleMapper salleMapper;

    @Override
    public List<SalleResponseDto> getAllSalle() {
        return salleRepository.findAll()
                              .stream()
                              .map(salleMapper::salleToSalleResponseDto)
                              .toList();
    }

    @Override
    public List<Salle> getSalleByList(List<Integer> sallesId) {
        return salleRepository.getAllSalleByListId(sallesId);
    }

    @Override
    public Salle findSalleById(Long id) {
        return salleRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Salle not found with id: "+id));
    }

    @Override
    public SalleResponseDto getSalleById(Long id) {
        return salleMapper.salleToSalleResponseDto(findSalleById(id));
    }

    @Override
    public SalleResponseDto createNewSalle(SalleRequestDto salleRequestDto) {
        Salle salle = salleMapper.salleRequestDtoToSalle(salleRequestDto);
        return salleMapper.salleToSalleResponseDto(salleRepository.save(salle));
    }

    @Override
    public SalleResponseDto updateSalle(SalleRequestDto salleRequestDto, Long id) {
        Salle salle = findSalleById(id);
        salle.setName(salleRequestDto.getName());
        salle.setNumberSeat(salleRequestDto.getNumberSeat());
        return salleMapper.salleToSalleResponseDto(salleRepository.save(salle));
    }

    @Override
    public void deleteSalle(Long id) {
        salleRepository.delete(findSalleById(id));
    }

}
