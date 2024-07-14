package com.amagana.cinema_service.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.amagana.cinema_service.dto.SessionRequestDto;
import com.amagana.cinema_service.dto.SessionResponseDto;
import com.amagana.cinema_service.entity.Session;
import com.amagana.cinema_service.repository.SessionRepository;
import com.amagana.cinema_service.service.SessionService;
import com.amagana.cinema_service.utils.SessionMapper;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class SessionServiceImpl implements SessionService {

    private final SessionRepository sessionRepository;
    private static final SessionMapper sessionMapper = SessionMapper.INSTANCE;

    @Override
    public List<SessionResponseDto> getAllSession() {
        return sessionRepository.findAll().stream()
                                .map(sessionMapper::sessionToSessionResponseDto)
                                .toList();
    }

    @Override
    public SessionResponseDto getSessionById(Long id) {
        return sessionMapper.sessionToSessionResponseDto(findSessionById(id));
    }

    @Override
    public Session findSessionById(Long id) {
        return sessionRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Session Not Found: "+id));
    }

    @Override
    public SessionResponseDto createNewSession(SessionRequestDto sessionRequestDto) {
        return sessionMapper.sessionToSessionResponseDto(sessionRepository.save(
            sessionMapper.sessionRequestToSession(sessionRequestDto)
        ));
    }

    @Override
    public SessionResponseDto updateSession(SessionRequestDto sessionRequestDto, Long id) {
        Session session = findSessionById(id);
        session.setStartHour(sessionRequestDto.startHour());
        return sessionMapper.sessionToSessionResponseDto(sessionRepository.save(session));
    }

    @Override
    public void deleteSession(Long id) {
        sessionRepository.delete(findSessionById(id));
    }

}
