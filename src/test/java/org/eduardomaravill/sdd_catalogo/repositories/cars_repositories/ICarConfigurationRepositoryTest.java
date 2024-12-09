package org.eduardomaravill.sdd_catalogo.repositories.cars_repositories;

import org.eduardomaravill.sdd_catalogo.models.cars_models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(properties = {"spring.profiles.active=test"})
class ICarConfigurationRepositoryTest {

    @Autowired
    private ICarConfigurationRepository carConfigurationRepository;

    @Autowired
    private IAuxiliarRepository auxiliarRepository;

    @Autowired
    private ILevelRepository levelRepository;

    @Autowired
    private IMakersRepository makersRepository;

    @Autowired
    private ICarRepository carRepository;

    @Autowired
    private IEngineRepository engineRepository;

    @Autowired
    private IClassesRepository classesRepository;

    @Autowired
    private ITurboTypeRepository turboTypeRepository;

    @Autowired
    private ITurboRepository turboRepository;

    @Autowired
    private IStreetTypeRepository streetTypeRepository;

    @Autowired
    private ISuspensionRepository suspensionRepository;

    @Autowired
    private ITireRepository tireRepository;

    @Autowired
    private IGearRepository gearRepository;

    @Autowired
    private IInitSkidRepository initSkidRepository;

    @Autowired
    private IDriverRepository driverRepository;

    private CarConfiguration carConfiguration;

    @BeforeEach
    void setUp() {
        LevelEntity levelEntity = new LevelEntity(null, "Pro");
        levelEntity = levelRepository.save(levelEntity);
        Auxiliary auxiliary = new Auxiliary(null,"PROTECCIÓN DE IMPACTOS", levelEntity);
        auxiliary = auxiliarRepository.save(auxiliary);
        Maker maker = new Maker(null, "Ford");
        maker = makersRepository.save(maker);
        Car car = new Car(null, maker, "Mustang", "1964");
        car = carRepository.save(car);
        Engine engine = new Engine(null, 1000, 8.5, "V8 híbrido", levelEntity);
        engine = engineRepository.save(engine);
        Classes classes = new Classes(null, "B");
        classes = classesRepository.save(classes);
        TurboType turboType = new TurboType(null, "DobleTurboCompresor");
        turboType = turboTypeRepository.save(turboType);
        Turbo turbo = new Turbo(null, turboType, levelEntity);
        turbo = turboRepository.save(turbo);
        StreetType streetType = new StreetType(null, "Derrape");
        streetType = streetTypeRepository.save(streetType);
        Suspension suspension = new Suspension(null, streetType, levelEntity);
        suspension = suspensionRepository.save(suspension);
        Tire tire = new Tire(null,streetType, levelEntity);
        tire = tireRepository.save(tire);
        Gear gear = new Gear(null, 4, levelEntity);
        gear = gearRepository.save(gear);
        InitSkid initSkid = new InitSkid(null,"POR DEFECTO");
        initSkid = initSkidRepository.save(initSkid);
        Driver driver = new Driver(null, 100, 5, 5, true, initSkid);
        driver = driverRepository.save(driver);

        carConfiguration = new CarConfiguration();
        carConfiguration.setCar(car);
        carConfiguration.setEngine(engine);
        carConfiguration.setInductionLevelEntity(levelEntity);
        carConfiguration.setEcuLevelEntity(levelEntity);
        carConfiguration.setInjectionLevelEntity(levelEntity);
        carConfiguration.setEscapeLevelEntity(levelEntity);
        carConfiguration.setTurbo(turbo);
        carConfiguration.setNitroLevelEntity(levelEntity);
        carConfiguration.setSuspension(suspension);
        carConfiguration.setBrakeLevelEntity(levelEntity);
        carConfiguration.setTire(tire);
        carConfiguration.setEmbragueLevelEntity(levelEntity);
        carConfiguration.setGear(gear);
        carConfiguration.setDifferentialLevelEntity(levelEntity);
        carConfiguration.setTopSpeed(350);
        carConfiguration.setOneHundred(3.5);
        carConfiguration.setFourHundred(10.5);
        carConfiguration.setPower(1500);
        carConfiguration.setPar(2000);
        carConfiguration.setDriver(driver);
        carConfiguration.setAuxiliaryOne(auxiliary);
        carConfiguration.setAuxiliaryTwo(auxiliary);
        carConfiguration.setClasses(classes);
    }

    @Test
    @DisplayName("Test save CarConfiguration")
    void testSaveCarConfiguration() {
        carConfigurationRepository.save(carConfiguration);
        Optional<CarConfiguration> savedCarConfiguration = carConfigurationRepository.findById(carConfiguration.getId());
        assertTrue(savedCarConfiguration.isPresent());
        assertEquals(carConfiguration, savedCarConfiguration.get());
    }

    @Test
    @DisplayName("Test update CarConfiguration")
    void testUpdateCarConfiguration() {
        carConfigurationRepository.save(carConfiguration);
        CarConfiguration saveCarConfiguration = carConfigurationRepository.findById(carConfiguration.getId()).orElse(null);
        assertNotNull(saveCarConfiguration);
        saveCarConfiguration.setTopSpeed(360);
        carConfigurationRepository.save(saveCarConfiguration);
        Optional<CarConfiguration> updatedCarConfiguration = carConfigurationRepository.findById(saveCarConfiguration.getId());
        assertTrue(updatedCarConfiguration.isPresent());
        assertEquals(360, updatedCarConfiguration.get().getTopSpeed());
    }

    @Test
    @DisplayName("Test delete CarConfiguration")
    void testDeleteCarConfiguration() {
        carConfigurationRepository.save(carConfiguration);
        CarConfiguration saveCarConfiguration = carConfigurationRepository.findById(carConfiguration.getId()).orElse(null);
        assertNotNull(saveCarConfiguration);
        carConfigurationRepository.delete(saveCarConfiguration);
        Optional<CarConfiguration> deletedCarConfiguration = carConfigurationRepository.findById(saveCarConfiguration.getId());
        assertFalse(deletedCarConfiguration.isPresent());
    }
}