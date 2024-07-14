package com.amagana.cinema_service.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.amagana.cinema_service.dto.CinemaRequestDto;
import com.amagana.cinema_service.dto.CinemaResponseDto;
import com.amagana.cinema_service.entity.Cinema;
import com.amagana.cinema_service.entity.Salle;
import com.amagana.cinema_service.entity.Town;
import com.amagana.cinema_service.repository.CinemaRepository;
import com.amagana.cinema_service.service.CinemaService;
import com.amagana.cinema_service.service.SalleService;
import com.amagana.cinema_service.service.TownService;
import com.amagana.cinema_service.utils.CinemaMapper;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class CinemaServiceImpl implements CinemaService {

    private final CinemaRepository cinemaRepository;
    private final CinemaMapper cinemaMapper;
    private final TownService townService;
    private final SalleService salleService;

    @Override
    public List<CinemaResponseDto> getAllCinema() {
       return cinemaRepository.findAll()
                              .stream()
                              .map(cinemaMapper::cinemaToCinemaResponseDto)
                              .toList();
    }

    @Override
    public Cinema findCinemaById(Long id) {
        return cinemaRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Cinema not found with id: "+id));
    }

    @Override
    public CinemaResponseDto getCinemaById(Long id) {
        return cinemaMapper.cinemaToCinemaResponseDto(findCinemaById(id));
    }

    @Override
    public CinemaResponseDto createNewCinema(CinemaRequestDto cinemaRequestDto) {
        Cinema cinema = cinemaMapper.cinemaRepositoryToCinema(cinemaRequestDto);
        if (cinemaRequestDto.getTownId() != null) {
            Town town = townService.findTownById(cinemaRequestDto.getTownId());
            cinema.setTown(town);
        }
        if (!cinemaRequestDto.getSalleId().isEmpty()) {
            List<Salle> salles = salleService.getSalleByList(cinemaRequestDto.getSalleId());
            cinema.setSalle(salles);
            for(Salle salle: salles) {
                salle.setCinema(cinema);
            }
        }
        return cinemaMapper.cinemaToCinemaResponseDto(cinemaRepository.save(cinema));
    }

    @Override
    public CinemaResponseDto updateCinema(CinemaRequestDto cinemaRequestDto, Long id) {
        Cinema cinema = findCinemaById(id);
        if (cinemaRequestDto.getTownId() != null) {
            Town town = townService.findTownById(cinemaRequestDto.getTownId());
            cinema.setTown(town);
        }
        if (!cinemaRequestDto.getSalleId().isEmpty()) {
            List<Salle> salles = salleService.getSalleByList(cinemaRequestDto.getSalleId());
            for(Salle salle: salles) {
                salle.setCinema(cinema);
                cinema.getSalle().add(salle);
            }
        }
        cinema.setAltitude(cinemaRequestDto.getAltitude());
        cinema.setLatitude(cinemaRequestDto.getLatitude());
        cinema.setLongitude(cinemaRequestDto.getLongitude());
        cinema.setName(cinemaRequestDto.getName());
        cinema.setNumberRoom(cinemaRequestDto.getNumberRoom());
        return cinemaMapper.cinemaToCinemaResponseDto(cinemaRepository.save(cinema));
    }

    @Override
    public void deleteCinema(Long id) {
       cinemaRepository.delete(findCinemaById(id));
    }

}
