package org.eduardomaravill.sdd_catalogo.services.cars_services;

import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.InitSkidDto;

import java.util.List;

public interface IInitSkidService {
    InitSkidDto getInitSkid(Long id);

    void createInitSkid(InitSkidDto initSkidDto);

    void updateInitSkid(Long id, InitSkidDto initSkidDto);

    void deleteInitSkid(Long id);

    List<InitSkidDto> getAllInitSkids();
}
