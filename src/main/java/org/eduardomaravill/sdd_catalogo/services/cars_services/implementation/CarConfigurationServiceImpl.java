package org.eduardomaravill.sdd_catalogo.services.cars_services.implementation;

import jakarta.transaction.Transactional;
import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.*;
import org.eduardomaravill.sdd_catalogo.dtos.user_car_dtos.FilterCar;
import org.eduardomaravill.sdd_catalogo.exceptions.DataNotFoundException;
import org.eduardomaravill.sdd_catalogo.exceptions.ResourceNotFoundException;
import org.eduardomaravill.sdd_catalogo.models.cars_models.CarConfiguration;
import org.eduardomaravill.sdd_catalogo.repositories.cars_repositories.ICarConfigurationRepository;
import org.eduardomaravill.sdd_catalogo.services.MapperService;
import org.eduardomaravill.sdd_catalogo.services.cars_services.*;
import org.eduardomaravill.sdd_catalogo.utils.CarConfigurationSpecification;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.LongFunction;

@Service
@Transactional
public class CarConfigurationServiceImpl implements ICarConfigurationService {
    private static final String CAR_CONFIGURATION_MESSAGE = "Car Configuration";

    private final ICarConfigurationRepository carConfigurationRepository;

    private final ICarService carService;

    private final IEngineService engineService;

    private final ILevelService levelService;

    private final ITurboService turboService;

    private final ISuspensionService suspensionService;

    private final ITireService tireService;

    private final IGearService gearService;

    private final IDriverService driverService;

    private final IAuxiliarService auxiliarService;

    private final IClassesService classesService;

    private final MapperService mapperService;

    @Autowired
    public CarConfigurationServiceImpl(
            ICarConfigurationRepository carConfigurationRepository,
            ICarService carService, IEngineService engineService,
            ILevelService levelService, ITurboService turboService,
            ISuspensionService suspensionService,
            ITireService tireService, IGearService gearService,
            IDriverService driverService,
            IAuxiliarService auxiliarService,
            IClassesService classesService,
            MapperService mapperService) {
        this.carConfigurationRepository = carConfigurationRepository;
        this.carService = carService;
        this.engineService = engineService;
        this.levelService = levelService;
        this.turboService = turboService;
        this.suspensionService = suspensionService;
        this.tireService = tireService;
        this.gearService = gearService;
        this.driverService = driverService;
        this.auxiliarService = auxiliarService;
        this.classesService = classesService;
        this.mapperService = mapperService;
    }


    @Override
    public CarConfigurationDto getCarConfiguration(Long id) {
        CarConfiguration carConfiguration = carConfigurationRepository.findByIdSpecific(id)
                .orElseThrow(() -> new ResourceNotFoundException(CAR_CONFIGURATION_MESSAGE, id));
        return mapperService.convertToDto(carConfiguration);
    }

    @Override
    public CarConfigurationDto createCarConfiguration(CarConfigurationDto carConfigurationDto) {
        verifyDataExists(carConfigurationDto);
        CarConfiguration carConfiguration = mapperService.convertToEntity(carConfigurationDto);
        carConfiguration = carConfigurationRepository.save(carConfiguration);
        return mapperService.convertToDto(carConfiguration);
    }

    @Override
    public void updateCarConfiguration(Long id, CarConfigurationDto carConfigurationDto) {
        verifyDataExists(carConfigurationDto);
        verifyExists(id);
        carConfigurationDto.setId(id);
        CarConfiguration carConfiguration = mapperService.convertToEntity(carConfigurationDto);
        carConfigurationRepository.save(carConfiguration);
    }

    @Override
    public void deleteCarConfiguration(Long id) {
        verifyExists(id);
        carConfigurationRepository.deleteById(id);
    }

    @Override
    public List<CarConfigurationDto> getAllCarConfigurations() {
        return carConfigurationRepository.findAll().stream().map(mapperService::convertToDto).toList();
    }

    @Override
    public Page<CarConfigurationDto> searchCarConfigurations( FilterCar filterCar,Pageable pageable) {
        Specification<CarConfiguration> specification = CarConfigurationSpecification.filterByCriteria(filterCar);
        return carConfigurationRepository.findAll(specification,pageable).map(mapperService::convertToDto);
    }

    private void verifyDataExists(CarConfigurationDto carConfigurationDto) {
        Map<String, Long> ids = getStringLongMap(carConfigurationDto);

        Map<String, LongFunction<?>> services = new HashMap<>();
        services.put("Car", carService::getCar);
        services.put("Engine", engineService::getEngine);
        services.put("InductionLevel", levelService::getLevel);
        services.put("EcuLevel", levelService::getLevel);
        services.put("InjectionLevel", levelService::getLevel);
        services.put("EscapeLevel", levelService::getLevel);
        services.put("Turbo", turboService::getTurbo);
        services.put("NitroLevel", levelService::getLevel);
        services.put("Suspension", suspensionService::getSuspension);
        services.put("BrakeLevel", levelService::getLevel);
        services.put("Tire", tireService::getTire);
        services.put("EmbragueLevel", levelService::getLevel);
        services.put("Gear", gearService::getGear);
        services.put("DifferentialLevel", levelService::getLevel);
        services.put("Driver", driverService::getDriver);
        services.put("AuxiliarOne", auxiliarService::getAuxiliar);
        services.put("AuxiliarTwo", auxiliarService::getAuxiliar);
        services.put("Classes", classesService::getClasses);

        ids.forEach((key, id) -> verifyEntity(key, id, services.get(key)));

    }

    @NotNull
    private static Map<String, Long> getStringLongMap(CarConfigurationDto carConfigurationDto) {
        Map<String, Long> ids = new HashMap<>();
        ids.put("Car", carConfigurationDto.getCarDto().getId());
        ids.put("Engine", carConfigurationDto.getEngineDto().getId());
        ids.put("InductionLevel", carConfigurationDto.getInductionLevelDto().getId());
        ids.put("EcuLevel", carConfigurationDto.getEcuLevelDto().getId());
        ids.put("InjectionLevel", carConfigurationDto.getInjectionLevelDto().getId());
        ids.put("EscapeLevel", carConfigurationDto.getEscapeLevelDto().getId());
        ids.put("Turbo", carConfigurationDto.getTurboDto().getId());
        ids.put("NitroLevel", carConfigurationDto.getNitroLevelDto().getId());
        ids.put("Suspension", carConfigurationDto.getSuspensionDto().getId());
        ids.put("BrakeLevel", carConfigurationDto.getBrakeLevelDto().getId());
        ids.put("Tire", carConfigurationDto.getTireDto().getId());
        ids.put("EmbragueLevel", carConfigurationDto.getEmbragueLevelDto().getId());
        ids.put("Gear", carConfigurationDto.getGearDto().getId());
        ids.put("DifferentialLevel", carConfigurationDto.getDifferentialLevelDto().getId());
        ids.put("Driver", carConfigurationDto.getDriverDto().getId());
        ids.put("AuxiliarOne", carConfigurationDto.getAuxiliarOneDto().getId());
        ids.put("AuxiliarTwo", carConfigurationDto.getAuxiliarTwoDto().getId());
        ids.put("Classes", carConfigurationDto.getClassesDto().getId());
        return ids;
    }

    private <T> void verifyEntity(String entityName, Long id, LongFunction<T> serviceMethod) {
        T entity = serviceMethod.apply(id);
        if (entity == null) {
            throw new DataNotFoundException(entityName);
        }
    }


    private void verifyExists(Long id) {
        if (!carConfigurationRepository.existsById(id)) {
            throw new ResourceNotFoundException(CAR_CONFIGURATION_MESSAGE, id);
        }
    }
}
