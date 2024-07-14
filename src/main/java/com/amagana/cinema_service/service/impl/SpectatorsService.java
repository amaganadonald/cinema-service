package com.amagana.cinema_service.service.impl;

import java.util.List;

import com.amagana.cinema_service.entity.Spectators;

public interface SpectatorsService {

    List<Spectators> getAllSpectatorsByFirname(String name);
}
