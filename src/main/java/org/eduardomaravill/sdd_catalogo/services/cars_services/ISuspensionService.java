package org.eduardomaravill.sdd_catalogo.services.cars_services;

import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.SuspensionDto;

import java.util.List;

public interface ISuspensionService {
    SuspensionDto getSuspension(Long id);

    void createSuspension(SuspensionDto suspensionDto);

    void updateSuspension(Long id, SuspensionDto suspensionDto);

    void deleteSuspension(Long id);

    List<SuspensionDto> getAllSuspensions();
}
