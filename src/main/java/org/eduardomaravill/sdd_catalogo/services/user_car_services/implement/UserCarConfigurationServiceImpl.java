package org.eduardomaravill.sdd_catalogo.services.user_car_services.implement;

import jakarta.transaction.Transactional;
import org.eduardomaravill.sdd_catalogo.dtos.auth.ValidTokenResponse;
import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.CarConfigurationDto;
import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.DriverDto;
import org.eduardomaravill.sdd_catalogo.exceptions.ObjectNotFoundException;
import org.eduardomaravill.sdd_catalogo.models.cars_models.CarConfiguration;
import org.eduardomaravill.sdd_catalogo.models.usercarconfig_models.UserCarConfiguration;
import org.eduardomaravill.sdd_catalogo.models.users_models.User;
import org.eduardomaravill.sdd_catalogo.repositories.users_cars_repositories.IUserCarConfigurationRepository;
import org.eduardomaravill.sdd_catalogo.services.MapperService;
import org.eduardomaravill.sdd_catalogo.services.cars_services.ICarConfigurationService;
import org.eduardomaravill.sdd_catalogo.services.cars_services.IDriverService;
import org.eduardomaravill.sdd_catalogo.services.user_car_services.IUserCarConfigurationService;
import org.eduardomaravill.sdd_catalogo.services.users_services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class UserCarConfigurationServiceImpl implements IUserCarConfigurationService {

    private final MapperService mapperService;
    private final IUserService userService;
    private final ICarConfigurationService carConfigurationService;
    private final IDriverService driverService;
    private final IUserCarConfigurationRepository userCarConfigurationRepository;

    @Autowired
    public UserCarConfigurationServiceImpl(IUserService userService, MapperService mapperService,
                                           ICarConfigurationService carConfigurationService, IDriverService driverService
            , IUserCarConfigurationRepository userCarConfigurationRepository) {
        this.userService = userService;
        this.mapperService = mapperService;
        this.carConfigurationService = carConfigurationService;
        this.driverService = driverService;
        this.userCarConfigurationRepository = userCarConfigurationRepository;
    }

    @Override
    public ValidTokenResponse createRacerCarConfiguration(CarConfigurationDto carConfigurationDto) {
        User user = getCurrentUser();
        try {
            carConfigurationDto.setDriverDto(getDriverDto(carConfigurationDto));
            CarConfigurationDto carConfigurationDto1 = carConfigurationService.createCarConfiguration(carConfigurationDto);
            CarConfiguration carConfiguration = mapperService.convertToEntity(carConfigurationDto1);
            UserCarConfiguration userCarConfiguration = new UserCarConfiguration();
            userCarConfiguration.setUser(user);
            userCarConfiguration.setCarConfiguration(carConfiguration);
            userCarConfigurationRepository.save(userCarConfiguration);
            return new ValidTokenResponse(true);
        } catch (Exception e) {
            return new ValidTokenResponse(false);
        }
    }

    private DriverDto getDriverDto(CarConfigurationDto carConfigurationDto) {
        DriverDto driverDto = carConfigurationDto.getDriverDto();
        driverDto = driverService.createOrGet(driverDto);
        return driverDto;
    }

    @Override
    public List<CarConfigurationDto> getAllRacerCarConfigurations() {
        User user = getCurrentUser();
        return user.getUserCarConfigurations().stream().map(UserCarConfiguration::getCarConfiguration).map(mapperService::convertToDto).toList();
    }

    @Override
    public ValidTokenResponse deleteRacerCarConfiguration(Long idCarConfiguration) {
        User user = getCurrentUser();
        UserCarConfiguration userCarConfiguration = user.getUserCarConfigurations()
                .stream().filter(uCC -> Objects.equals(uCC.getCarConfiguration().getId(), idCarConfiguration))
                .findFirst().orElseThrow( () -> new ObjectNotFoundException("CarConfiguration"));
        userCarConfigurationRepository.delete(userCarConfiguration);
        return new ValidTokenResponse(true);
    }

    @Override
    public ValidTokenResponse updateRacerCarConfiguration(Long idCarConfiguration, CarConfigurationDto carConfigurationDto) {
        User user = getCurrentUser();
        List<UserCarConfiguration> userCarConfiguration = user.getUserCarConfigurations();
        try {
            carConfigurationDto.setDriverDto(getDriverDto(carConfigurationDto));
            if (userCarConfiguration.stream().anyMatch(uCC -> Objects.equals(uCC.getCarConfiguration().getId(), idCarConfiguration))){
                carConfigurationService.updateCarConfiguration(idCarConfiguration,carConfigurationDto);
                return new ValidTokenResponse(true);
            }else {
                return new ValidTokenResponse(false);
            }
        }catch (Exception e){
            return new ValidTokenResponse(false);
        }
    }

    private User getCurrentUser() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.findOneByUsername(username).orElseThrow(() -> new ObjectNotFoundException("user not found. Username: " + username));
    }
}
