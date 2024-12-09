package org.eduardomaravill.sdd_catalogo.services.user_car_services;

import org.eduardomaravill.sdd_catalogo.dtos.auth.ValidTokenResponse;
import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.CarConfigurationDto;

import java.util.List;

public interface IUserCarConfigurationService {
    ValidTokenResponse createRacerCarConfiguration(CarConfigurationDto carConfigurationDto);

    List<CarConfigurationDto> getAllRacerCarConfigurations();

    ValidTokenResponse deleteRacerCarConfiguration(Long idCarConfiguration);

    ValidTokenResponse updateRacerCarConfiguration(Long idCarConfiguration, CarConfigurationDto carConfigurationDto);
}
