package org.eduardomaravill.sdd_catalogo.services.cars_services;

import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.TireDto;

import java.util.List;

public interface ITireService {
    TireDto getTire(Long id);

    void createTire(TireDto tireDto);

    void updateTire(Long id, TireDto tireDto);

    void deleteTire(Long id);

    List<TireDto> getAllTires();
}
