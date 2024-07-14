package com.amagana.cinema_service.utils;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.amagana.cinema_service.dto.SessionRequestDto;
import com.amagana.cinema_service.dto.SessionResponseDto;
import com.amagana.cinema_service.entity.Session;

@Mapper
public interface SessionMapper {

    SessionMapper INSTANCE = Mappers.getMapper(SessionMapper.class);

    Session sessionRequestToSession(SessionRequestDto sessionRequestDto);

    SessionResponseDto sessionToSessionResponseDto(Session session);
}
