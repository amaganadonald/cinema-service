package com.amagana.cinema_service.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.amagana.cinema_service.dto.TownRequestDto;
import com.amagana.cinema_service.dto.TownResponse;
import com.amagana.cinema_service.dto.TownResponseDto;
import com.amagana.cinema_service.entity.Town;
import com.amagana.cinema_service.repository.TownRepository;
import com.amagana.cinema_service.service.TownService;
import com.amagana.cinema_service.utils.TownMapper;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class TownServiceImpl implements TownService {

    private final TownRepository townRepository;
    private final TownMapper townMapper;

    /**
     * @return List of all Town
     */
    @Override
    public List<TownResponseDto> getAllTown() {
        return townRepository.findAll().stream()
                             .map(townMapper::townToTownResponseDto)
                             .toList();
    }

    /**
     * create another method who return town for reusability for this class and another class
     * @return town 
     */
    @Override
    public Town findTownById(Long id) {
        return townRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Town with not found with id: "+ id));
    }
    @Override
    public TownResponseDto getTownById(Long id) {
        return townMapper.townToTownResponseDto(findTownById(id));
    }

    @Override
    public TownResponseDto createNewTown(TownRequestDto townRequestDto) {
        return townMapper.townToTownResponseDto(
            townRepository.save(townMapper.townRequestDtoTown(townRequestDto))
        );
    }

    @Override
    public TownResponseDto updateTown(TownRequestDto townRequestDto, Long id) {
        Town town = findTownById(id);
        town.setAltitude(townRequestDto.getAltitude());
        town.setLatitude(townRequestDto.getLatitude());
        town.setLongitude(townRequestDto.getLongitude());
        town.setName(townRequestDto.getName());
        return townMapper.townToTownResponseDto(townRepository.save(town));
    }

    @Override
    public void deleteTown(Long id) {
       townRepository.delete(findTownById(id));
    }

    @Override
    public Page<TownResponse> getTownByPage(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("name").ascending());
        return townRepository.findAllBy(pageRequest);
    }



}
