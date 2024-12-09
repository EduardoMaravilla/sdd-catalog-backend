package org.eduardomaravill.sdd_catalogo.services.cars_services;

import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.GearDto;

import java.util.List;

public interface IGearService {
    GearDto getGear(Long id);

    void createGear(GearDto gearDto);

    void updateGear(Long id, GearDto gearDto);

    void deleteGear(Long id);

    List<GearDto> getAllGears();
}
