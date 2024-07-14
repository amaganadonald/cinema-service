package com.amagana.cinema_service.service;

import java.util.List;

import com.amagana.cinema_service.dto.SessionRequestDto;
import com.amagana.cinema_service.dto.SessionResponseDto;
import com.amagana.cinema_service.entity.Session;

public interface SessionService {

    List<SessionResponseDto> getAllSession();
    SessionResponseDto getSessionById(Long id);
    Session findSessionById(Long id);
    SessionResponseDto createNewSession(SessionRequestDto sessionRequestDto);
    SessionResponseDto updateSession(SessionRequestDto sessionRequestDto, Long id);
    void deleteSession(Long id);
}
