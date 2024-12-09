package org.eduardomaravill.sdd_catalogo.services.cars_services;

import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.*;
import org.eduardomaravill.sdd_catalogo.models.cars_models.*;
import org.eduardomaravill.sdd_catalogo.repositories.cars_repositories.ICarConfigurationRepository;
import org.eduardomaravill.sdd_catalogo.services.MapperService;
import org.eduardomaravill.sdd_catalogo.services.cars_services.implementation.CarConfigurationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ICarConfigurationServiceTest {

    @Mock
    private ICarConfigurationRepository carConfigurationRepository;

    @Mock
    private ICarService carService;

    @Mock
    private IEngineService engineService;

    @Mock
    private ILevelService levelService;

    @Mock
    private ITurboService turboService;

    @Mock
    private ISuspensionService suspensionService;

    @Mock
    private ITireService tireService;

    @Mock
    private IGearService gearService;

    @Mock
    private IDriverService driverService;

    @Mock
    private IAuxiliarService auxiliarService;

    @Mock
    private IClassesService classesService;

    @Mock
    private MapperService mapperService;

    @InjectMocks
    private CarConfigurationServiceImpl carConfigurationService;

    private CarConfigurationDto carConfigurationDto;
    private CarConfiguration carConfiguration;

    @BeforeEach
    public void setup() {
        carConfiguration = new CarConfiguration();
        carConfiguration.setId(30L);
        carConfiguration.setCar(new Car(20L, new Maker(4L, "Ford"), "Mustang", "1964"));
        carConfiguration.setEngine(new Engine(10L, 1000, 8.5, "V8 híbrido", new LevelEntity(5L, "Elite")));
        carConfiguration.setInductionLevelEntity(new LevelEntity(1L, "Básico"));
        carConfiguration.setEcuLevelEntity(new LevelEntity(1L, "Básico"));
        carConfiguration.setInjectionLevelEntity(new LevelEntity(1L, "Básico"));
        carConfiguration.setEscapeLevelEntity(new LevelEntity(1L, "Básico"));
        carConfiguration.setTurbo(new Turbo(5L, new TurboType(2L, "DobleTurboCompresor"), new LevelEntity(1L, "Básico")));
        carConfiguration.setNitroLevelEntity(new LevelEntity(1L, "Básico"));
        carConfiguration.setSuspension(new Suspension(6L, new StreetType(2L, "TodoTerreno"), new LevelEntity(2L, "Deportivo")));
        carConfiguration.setBrakeLevelEntity(new LevelEntity(1L, "Básico"));
        carConfiguration.setTire(new Tire(7L, new StreetType(4L, "Derrape"), new LevelEntity(3L, "Pro")));
        carConfiguration.setEmbragueLevelEntity(new LevelEntity(1L, "Básico"));
        carConfiguration.setGear(new Gear(3L, 3, new LevelEntity(3L, "Deportivo")));
        carConfiguration.setDifferentialLevelEntity(new LevelEntity(1L, "Básico"));
        carConfiguration.setTopSpeed(350);
        carConfiguration.setOneHundred(3.5);
        carConfiguration.setFourHundred(10.5);
        carConfiguration.setPower(1500);
        carConfiguration.setPar(2000);
        carConfiguration.setDriver(new Driver(2L, 100, 5, 5, true, new InitSkid(4L, "POR DEFECTO")));
        carConfiguration.setAuxiliaryOne(new Auxiliary(2L,"NITRO POR DERRAPE", new LevelEntity(4L,"SUPER")));
        carConfiguration.setAuxiliaryTwo(new Auxiliary(2L,"NITRO POR DERRAPE", new LevelEntity(4L,"SUPER")));
        carConfiguration.setClasses(new Classes(2L,"A"));

        carConfigurationDto = new CarConfigurationDto();
        carConfigurationDto.setId(30L);
        carConfigurationDto.setCarDto(new CarDto(20L, new MakerDto(4L, "Ford"), "Mustang", "1964"));
        carConfigurationDto.setEngineDto(new EngineDto(10L, 1000, 8.5, "V8 híbrido", new LevelDto(5L, "Elite")));
        carConfigurationDto.setInductionLevelDto(new LevelDto(1L, "Básico"));
        carConfigurationDto.setEcuLevelDto(new LevelDto(1L, "Básico"));
        carConfigurationDto.setInjectionLevelDto(new LevelDto(1L, "Básico"));
        carConfigurationDto.setEscapeLevelDto(new LevelDto(1L, "Básico"));
        carConfigurationDto.setTurboDto(new TurboDto(5L, new TurboTypeDto(2L, "DobleTurboCompresor"), new LevelDto(1L, "Básico")));
        carConfigurationDto.setNitroLevelDto(new LevelDto(1L, "Básico"));
        carConfigurationDto.setSuspensionDto(new SuspensionDto(6L, new StreetTypeDto(2L, "TodoTerreno"), new LevelDto(2L, "Deportivo")));
        carConfigurationDto.setBrakeLevelDto(new LevelDto(1L, "Básico"));
        carConfigurationDto.setTireDto(new TireDto(7L, new StreetTypeDto(4L, "Derrape"), new LevelDto(3L, "Pro")));
        carConfigurationDto.setEmbragueLevelDto(new LevelDto(1L, "Básico"));
        carConfigurationDto.setGearDto(new GearDto(3L, 3, new LevelDto(3L, "Deportivo")));
        carConfigurationDto.setDifferentialLevelDto(new LevelDto(1L, "Básico"));
        carConfigurationDto.setTopSpeed(350);
        carConfigurationDto.setOneHundred(3.5);
        carConfigurationDto.setFourHundred(10.5);
        carConfigurationDto.setPower(1500);
        carConfigurationDto.setPar(2000);
        carConfigurationDto.setDriverDto(new DriverDto(2L, 100, 5, 5, true, new InitSkidDto(4L, "POR DEFECTO")));
        carConfigurationDto.setAuxiliarOneDto(new AuxiliarDto(2L,"NITRO POR DERRAPE", new LevelDto(4L,"SUPER")));
        carConfigurationDto.setAuxiliarTwoDto(new AuxiliarDto(2L,"NITRO POR DERRAPE", new LevelDto(4L,"SUPER")));
        carConfigurationDto.setClassesDto(new ClassesDto(2L,"A"));
    }

    @Test
    @DisplayName("Test service get CarConfiguration")
    void getCarConfiguration() {
        when(carConfigurationRepository.findByIdSpecific(anyLong())).thenReturn(Optional.of(carConfiguration));
        when(mapperService.convertToDto(any(CarConfiguration.class))).thenReturn(carConfigurationDto);

        CarConfigurationDto result = carConfigurationService.getCarConfiguration(1L);

        assertNotNull(result);
        verify(carConfigurationRepository).findByIdSpecific(1L);
        verify(mapperService).convertToDto(carConfiguration);
    }

    @Test
    @DisplayName("Test service create CarConfiguration")
    void createCarConfiguration() {
        when(carService.getCar(anyLong())).thenReturn(new CarDto(20L, new MakerDto(4L, "Ford"), "Mustang", "1964"));
        when(engineService.getEngine(anyLong())).thenReturn(new EngineDto(10L, 1000, 8.5, "V8 híbrido", new LevelDto(5L, "Elite")));
        when(levelService.getLevel(anyLong())).thenReturn(new LevelDto(1L, "Básico"));
        when(turboService.getTurbo(anyLong())).thenReturn(new TurboDto(5L, new TurboTypeDto(2L, "DobleTurboCompresor"), new LevelDto(1L, "Básico")));
        when(suspensionService.getSuspension(anyLong())).thenReturn(new SuspensionDto(6L, new StreetTypeDto(2L, "TodoTerreno"), new LevelDto(2L, "Deportivo")));
        when(tireService.getTire(anyLong())).thenReturn(new TireDto(7L, new StreetTypeDto(4L, "Derrape"), new LevelDto(3L, "Pro")));
        when(gearService.getGear(anyLong())).thenReturn(new GearDto(3L, 3, new LevelDto(3L, "Deportivo")));
        when(driverService.getDriver(anyLong())).thenReturn(new DriverDto(2L, 100, 5, 5, true, new InitSkidDto(4L, "POR DEFECTO")));
        when(auxiliarService.getAuxiliar(anyLong())).thenReturn(new AuxiliarDto(2L,"NITRO POR DERRAPE", new LevelDto(4L,"SUPER")));
        when(classesService.getClasses(anyLong())).thenReturn(new ClassesDto(2L,"A"));
        when(mapperService.convertToEntity(any(CarConfigurationDto.class))).thenReturn(carConfiguration);
        when(carConfigurationRepository.save(any(CarConfiguration.class))).thenReturn(carConfiguration);

        carConfigurationService.createCarConfiguration(carConfigurationDto);

        verify(carService).getCar(anyLong());
        verify(engineService).getEngine(anyLong());
        verify(levelService,times(8)).getLevel(anyLong());
        verify(turboService).getTurbo(anyLong());
        verify(suspensionService).getSuspension(anyLong());
        verify(tireService).getTire(anyLong());
        verify(gearService).getGear(anyLong());
        verify(driverService).getDriver(anyLong());
        verify(auxiliarService, times(2)).getAuxiliar(anyLong());
        verify(classesService).getClasses(anyLong());
        verify(mapperService).convertToEntity(carConfigurationDto);
        verify(carConfigurationRepository).save(carConfiguration);
    }

    @Test
    @DisplayName("Test service update CarConfiguration")
    void updateCarConfiguration() {
        when(carService.getCar(anyLong())).thenReturn(new CarDto(20L, new MakerDto(4L, "Ford"), "Mustang", "1964"));
        when(engineService.getEngine(anyLong())).thenReturn(new EngineDto(10L, 1000, 8.5, "V8 híbrido", new LevelDto(5L, "Elite")));
        when(levelService.getLevel(anyLong())).thenReturn(new LevelDto(1L, "Básico"));
        when(turboService.getTurbo(anyLong())).thenReturn(new TurboDto(5L, new TurboTypeDto(2L, "DobleTurboCompresor"), new LevelDto(1L, "Básico")));
        when(suspensionService.getSuspension(anyLong())).thenReturn(new SuspensionDto(6L, new StreetTypeDto(2L, "TodoTerreno"), new LevelDto(2L, "Deportivo")));
        when(tireService.getTire(anyLong())).thenReturn(new TireDto(7L, new StreetTypeDto(4L, "Derrape"), new LevelDto(3L, "Pro")));
        when(gearService.getGear(anyLong())).thenReturn(new GearDto(3L, 3, new LevelDto(3L, "Deportivo")));
        when(driverService.getDriver(anyLong())).thenReturn(new DriverDto(2L, 100, 5, 5, true, new InitSkidDto(4L, "POR DEFECTO")));
        when(auxiliarService.getAuxiliar(anyLong())).thenReturn(new AuxiliarDto(2L,"NITRO POR DERRAPE", new LevelDto(4L,"SUPER")));
        when(classesService.getClasses(anyLong())).thenReturn(new ClassesDto(2L,"A"));
        when(mapperService.convertToEntity(any(CarConfigurationDto.class))).thenReturn(carConfiguration);
        when(carConfigurationRepository.save(any(CarConfiguration.class))).thenReturn(carConfiguration);
        when(carConfigurationRepository.existsById(anyLong())).thenReturn(true);

        carConfigurationService.updateCarConfiguration(1L, carConfigurationDto);


        verify(carService).getCar(anyLong());
        verify(engineService).getEngine(anyLong());
        verify(levelService,times(8)).getLevel(anyLong());
        verify(turboService).getTurbo(anyLong());
        verify(suspensionService).getSuspension(anyLong());
        verify(tireService).getTire(anyLong());
        verify(gearService).getGear(anyLong());
        verify(driverService).getDriver(anyLong());
        verify(auxiliarService, times(2)).getAuxiliar(anyLong());
        verify(classesService).getClasses(anyLong());
        verify(mapperService).convertToEntity(carConfigurationDto);
        verify(carConfigurationRepository).save(carConfiguration);
        verify(carConfigurationRepository).existsById(anyLong());
    }

    @Test
    @DisplayName("Test service delete CarConfigurations")
    void deleteCarConfiguration() {
        when(carConfigurationRepository.existsById(anyLong())).thenReturn(true);
        carConfigurationService.deleteCarConfiguration(1L);
        verify(carConfigurationRepository).existsById(anyLong());
        verify(carConfigurationRepository).deleteById(anyLong());
    }

    @Test
    @DisplayName("Test service getAllCarConfigurations")
    void testGetAllCarConfigurations() {
        when(carConfigurationRepository.findAll()).thenReturn(List.of(carConfiguration));
        when(mapperService.convertToDto(any(CarConfiguration.class))).thenReturn(carConfigurationDto);

        List<CarConfigurationDto> result = carConfigurationService.getAllCarConfigurations();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(carConfigurationRepository).findAll();
        verify(mapperService).convertToDto(carConfiguration);
    }
}