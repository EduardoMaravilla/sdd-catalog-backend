package org.eduardomaravill.sdd_catalogo.services.cars_services;

import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.TurboTypeDto;

import java.util.List;

public interface ITurboTypeService {
    TurboTypeDto getTurboType(Long id);

    void createTurboType(TurboTypeDto turboTypeDto);

    void updateTurboType(Long id, TurboTypeDto turboTypeDto);

    void deleteTurboType(Long id);

    List<TurboTypeDto> getAllTurboTypes();
}
