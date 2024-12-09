package org.eduardomaravill.sdd_catalogo.services.cars_services;

import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.AuxiliarDto;

import java.util.List;

public interface IAuxiliarService {
    AuxiliarDto getAuxiliar(Long id);
    void createAuxiliar(AuxiliarDto auxiliarDto);
    void updateAuxiliar(Long id, AuxiliarDto auxiliarDto);
    void deleteAuxiliar(Long id);
    List<AuxiliarDto> getAllAuxiliaries();
}
