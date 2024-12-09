package org.eduardomaravill.sdd_catalogo.services.cars_services;

import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.CarConfigurationDto;
import org.eduardomaravill.sdd_catalogo.dtos.user_car_dtos.FilterCar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICarConfigurationService {
    CarConfigurationDto getCarConfiguration(Long id);

    CarConfigurationDto createCarConfiguration(CarConfigurationDto carConfigurationDto);

    void updateCarConfiguration(Long id, CarConfigurationDto carConfigurationDto);

    void deleteCarConfiguration(Long id);

    List<CarConfigurationDto> getAllCarConfigurations();

    Page<CarConfigurationDto> searchCarConfigurations(FilterCar filterCar, Pageable pageable);
}
