package org.eduardomaravill.sdd_catalogo.services.cars_services;

import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.MakerDto;

import java.util.List;

public interface IMakerService {
    MakerDto getMaker(Long id);

    void createMaker(MakerDto makerDto);

    void updateMaker(Long id, MakerDto makerDto);

    void deleteMaker(Long id);

    List<MakerDto> getAllMakers();
}
