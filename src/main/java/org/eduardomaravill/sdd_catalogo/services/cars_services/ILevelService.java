package org.eduardomaravill.sdd_catalogo.services.cars_services;

import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.LevelDto;

import java.util.List;

public interface ILevelService {
    LevelDto getLevel(Long id);

    void createLevel(LevelDto level);

    void updateLevel(Long id, LevelDto level);

    void deleteLevel(Long id);

    List<LevelDto> getAllLevels();
}
