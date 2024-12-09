package org.eduardomaravill.sdd_catalogo.services.cars_services;

import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.EngineDto;

import java.util.List;

public interface IEngineService {
    EngineDto getEngine(Long id);

    void createEngine(EngineDto engineDto);

    void updateEngine(Long id, EngineDto engineDto);

    void deleteEngine(Long id);

    List<EngineDto> getAllEngines();
}
