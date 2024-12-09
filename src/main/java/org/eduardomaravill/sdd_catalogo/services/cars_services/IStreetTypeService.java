package org.eduardomaravill.sdd_catalogo.services.cars_services;

import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.StreetTypeDto;

import java.util.List;

public interface IStreetTypeService {
    StreetTypeDto getStreetType(Long id);

    void createStreetType(StreetTypeDto streetTypeDto);

    void updateStreetType(Long id, StreetTypeDto streetTypeDto);

    void deleteStreetType(Long id);

    List<StreetTypeDto> getAllStreetTypes();
}
