package com.amagana.cinema_service.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.amagana.cinema_service.dto.TownRequestDto;
import com.amagana.cinema_service.dto.TownResponseDto;
import com.amagana.cinema_service.entity.Town;
import com.amagana.cinema_service.repository.TownRepository;
import com.amagana.cinema_service.service.impl.TownServiceImpl;
import com.amagana.cinema_service.utils.TownMapper;

@ExtendWith(MockitoExtension.class)
class TownServiceTest {


    @Mock
    TownRepository townRepository;

    @InjectMocks
    TownServiceImpl townService;

    @Mock
    TownMapper townMapper;

    TownRequestDto townRequestDto;
    TownResponseDto townResponseDto;
    Town town;

    @BeforeEach
    void setUp() {
        townRequestDto=TownRequestDto.builder()
                                     .altitude(10)
                                     .latitude(30)
                                     .longitude(20)
                                     .name("Hamilus")
                                     .build();
        townResponseDto=TownResponseDto.builder()
                                     .altitude(10)
                                     .latitude(30)
                                     .longitude(20)
                                     .name("Hamilus")
                                     .id(1L)
                                     .build();
        town=Town.builder()
                 .altitude(10)
                 .latitude(30)
                 .longitude(20)
                 .name("Hamilus")
                 .id(1L)
                 .build();
    }

    @Test
    void getTownById_ExistingId_ReturnTown() {
        when(townRepository.findById(1L)).thenReturn(Optional.of(town));
        when(townMapper.townToTownResponseDto(town)).thenReturn(townResponseDto);
        TownResponseDto tDto = townService.getTownById(1L);
        assertEquals("Hamilus", tDto.getName());
        assertEquals(10, tDto.getAltitude());
    }

    @Test
    void createTown_validTownRequest_ReturnTown() {
        when(townMapper.townRequestDtoTown(townRequestDto)).thenReturn(town);
        when(townRepository.save(town)).thenReturn(town);
        when(townMapper.townToTownResponseDto(town)).thenReturn(townResponseDto);
        TownResponseDto towneDto = townService.createNewTown(townRequestDto);
        assertEquals("Hamilus", towneDto.getName());
        assertEquals(10, towneDto.getAltitude());
    }
}
