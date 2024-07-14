package com.amagana.cinema_service.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.amagana.cinema_service.controllers.CinemaController;
import com.amagana.cinema_service.dto.CinemaRequestDto;
import com.amagana.cinema_service.dto.CinemaResponseDto;
import com.amagana.cinema_service.service.CinemaService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(CinemaController.class)
@AutoConfigureWebMvc
class CinemaControllerTest {


    @Autowired
    MockMvc mockMvc;

    @MockBean
    CinemaService cinemaService;

    CinemaRequestDto cinemaRequestDto;
    CinemaResponseDto cinemaResponseDto;
    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        cinemaRequestDto = CinemaRequestDto.builder()
                                         .altitude(20)
                                         .latitude(30)
                                         .longitude(10)
                                         .name("Eden")
                                         .numberRoom(15)
                                         .build();
        cinemaResponseDto = CinemaResponseDto.builder()
                                            .altitude(20)
                                            .latitude(30)
                                            .longitude(10)
                                            .name("Eden")
                                            .numberRoom(15)
                                             .build();
    }

    @Test
    void shouldReturnCinemaById() throws Exception {
        when(cinemaService.getCinemaById(1L)).thenReturn(cinemaResponseDto);
        mockMvc.perform(get("/api/v1/cinema/"+1).contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(cinemaRequestDto)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.status").value(200))
        .andExpect(jsonPath("$.results.altitude").value(20));
    }
}
