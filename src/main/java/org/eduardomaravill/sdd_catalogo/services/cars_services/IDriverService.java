package org.eduardomaravill.sdd_catalogo.services.cars_services;

import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.DriverDto;

import java.util.List;

public interface IDriverService {
    DriverDto getDriver(Long id);

    void createDriver(DriverDto driverDto);

    void updateDriver(Long id, DriverDto driverDto);

    void deleteDriver(Long id);

    DriverDto createOrGet(DriverDto driverDto);

    List<DriverDto> getAllDrivers();
}
