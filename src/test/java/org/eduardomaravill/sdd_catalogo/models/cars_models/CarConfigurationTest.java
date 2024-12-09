package org.eduardomaravill.sdd_catalogo.models.cars_models;

import jakarta.validation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CarConfigurationTest {

    private CarConfiguration carConfiguration;
    private Validator validator;

    @BeforeEach
    void setUp() {
        carConfiguration = new CarConfiguration();
        carConfiguration.setId(30L);
        carConfiguration.setCar(new Car(20L, new Maker(4L, "Ford"), "Mustang", "1964"));
        carConfiguration.setEngine(new Engine(10L, 1000, 8.5, "V8 híbrido", new LevelEntity(5L, "Elite")));
        carConfiguration.setInductionLevelEntity(new LevelEntity(1L, "Básico"));
        carConfiguration.setEcuLevelEntity(new LevelEntity(2L, "Deportivo"));
        carConfiguration.setInjectionLevelEntity(new LevelEntity(3L, "Pro"));
        carConfiguration.setEscapeLevelEntity(new LevelEntity(4L, "Super"));
        carConfiguration.setTurbo(new Turbo(5L, new TurboType(2L, "DobleTurboCompresor"), new LevelEntity(1L, "Básico")));
        carConfiguration.setNitroLevelEntity(new LevelEntity(5L, "Elite"));
        carConfiguration.setSuspension(new Suspension(6L, new StreetType(2L, "TodoTerreno"), new LevelEntity(2L, "Deportivo")));
        carConfiguration.setBrakeLevelEntity(new LevelEntity(3L, "Pro"));
        carConfiguration.setTire(new Tire(7L, new StreetType(4L, "Derrape"), new LevelEntity(3L, "Pro")));
        carConfiguration.setEmbragueLevelEntity(new LevelEntity(1L, "Básico"));
        carConfiguration.setGear(new Gear(3L, 3, new LevelEntity(3L, "Deportivo")));
        carConfiguration.setDifferentialLevelEntity(new LevelEntity(2L, "Deportivo"));
        carConfiguration.setTopSpeed(350);
        carConfiguration.setOneHundred(3.5);
        carConfiguration.setFourHundred(10.5);
        carConfiguration.setPower(1500);
        carConfiguration.setPar(2000);
        carConfiguration.setDriver(new Driver(2L, 100, 5, 5, true, new InitSkid(4L, "POR DEFECTO")));
        carConfiguration.setAuxiliaryOne(new Auxiliary(2L, "AGARRE DE NITRO", new LevelEntity(2L,"Pro")));
        carConfiguration.setAuxiliaryTwo(new Auxiliary(2L, "AGARRE DE NITRO", new LevelEntity(2L,"Pro")));
        carConfiguration.setClasses(new Classes(4L,"S"));
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    @Test
    @DisplayName("Test get ID")
    void getId() {
        assertEquals(30L, carConfiguration.getId());
    }

    @Test
    @DisplayName("Test get Car")
    void getCar() {
        assertEquals(new Car(20L, new Maker(4L, "Ford"), "Mustang", "1964"), carConfiguration.getCar());
    }

    @Test
    @DisplayName("Test get Engine")
    void getEngine() {
        assertEquals(new Engine(10L, 1000, 8.5, "V8 híbrido", new LevelEntity(5L, "Elite")), carConfiguration.getEngine());
    }

    @Test
    @DisplayName("Test get Induction level")
    void getInductionLevel() {
        assertEquals(new LevelEntity(1L, "Básico"), carConfiguration.getInductionLevelEntity());
    }

    @Test
    @DisplayName("Test get Ecu Level")
    void getEcuLevel() {
        assertEquals(new LevelEntity(2L, "Deportivo"), carConfiguration.getEcuLevelEntity());
    }

    @Test
    @DisplayName("Test get Injection Level")
    void getInjectionLevel() {
        assertEquals(new LevelEntity(3L, "Pro"), carConfiguration.getInjectionLevelEntity());
    }

    @Test
    @DisplayName("Test get Exhaust Level")
    void getEscapeLevel() {
        assertEquals(new LevelEntity(4L, "Super"), carConfiguration.getEscapeLevelEntity());
    }

    @Test
    @DisplayName("Test get Turbo")
    void getTurbo() {
        assertEquals(new Turbo(5L, new TurboType(2L, "DobleTurboCompresor"), new LevelEntity(1L, "Básico")), carConfiguration.getTurbo());
    }

    @Test
    @DisplayName("Test get Nitro Level")
    void getNitroLevel() {
        assertEquals(new LevelEntity(5L, "Elite"), carConfiguration.getNitroLevelEntity());
    }

    @Test
    @DisplayName("Test get Suspension")
    void getSuspension() {
        assertEquals(new Suspension(6L, new StreetType(2L, "TodoTerreno"), new LevelEntity(2L, "Deportivo")), carConfiguration.getSuspension());
    }

    @Test
    @DisplayName("Test get Brake Level")
    void getBrakeLevel() {
        assertEquals(new LevelEntity(3L, "Pro"), carConfiguration.getBrakeLevelEntity());
    }

    @Test
    @DisplayName("Test get Tire")
    void getTire() {
        assertEquals(new Tire(7L, new StreetType(4L, "Derrape"), new LevelEntity(3L, "Pro")), carConfiguration.getTire());
    }

    @Test
    @DisplayName("Test get Embrague Level")
    void getEmbragueLevel() {
        assertEquals(new LevelEntity(1L, "Básico"), carConfiguration.getEmbragueLevelEntity());
    }

    @Test
    @DisplayName("Test get Gear")
    void getGear() {
        assertEquals(new Gear(3L, 3, new LevelEntity(3L, "Deportivo")), carConfiguration.getGear());
    }

    @Test
    @DisplayName("Test get Differential Level")
    void getDifferentialLevel() {
        assertEquals(new LevelEntity(2L, "Deportivo"), carConfiguration.getDifferentialLevelEntity());
    }

    @Test
    @DisplayName("Test get Top Speed")
    void getTopSpeed() {
        assertEquals(350, carConfiguration.getTopSpeed());
    }

    @Test
    @DisplayName("Test get Time of One Hundred")
    void getOneHundred() {
        assertEquals(3.5, carConfiguration.getOneHundred());
    }

    @Test
    @DisplayName("Test get Power")
    void getPower() {
        assertEquals(1500, carConfiguration.getPower());
    }

    @Test
    @DisplayName("Test get Par(N/m)")
    void getPar() {
        assertEquals(2000, carConfiguration.getPar());
    }

    @Test
    @DisplayName("Test get Time of Four Hundred")
    void getFourHundred() {
        assertEquals(10.5, carConfiguration.getFourHundred());
    }

    @Test
    @DisplayName("Test get Drive")
    void getDriver() {
        assertEquals(new Driver(2L, 100, 5, 5, true, new InitSkid(4L, "POR DEFECTO")), carConfiguration.getDriver());
    }

    @Test
    @DisplayName("Test change of ID")
    void setId() {
        carConfiguration.setId(50L);
        assertEquals(50L, carConfiguration.getId());
    }

    @Test
    @DisplayName("Test change of Car")
    void setCar() {
        carConfiguration.setCar(new Car(30L, new Maker(5L, "Chevrolet"), "Corvette", "1965"));
        assertEquals(new Car(30L, new Maker(5L, "Chevrolet"), "Corvette", "1965"), carConfiguration.getCar());
    }

    @Test
    @DisplayName("Test not null car")
    void testValidCarInCarConfiguration() {
        assertNotNull(carConfiguration.getCar());
        carConfiguration.setCar(null);
        assertThrows(ConstraintViolationException.class, () -> validateCarConfiguration(carConfiguration));
    }

    @Test
    @DisplayName("Test change of Engine")
    void setEngine() {
        carConfiguration.setEngine(new Engine(15L, 1500, 9.0, "V12", new LevelEntity(6L, "Premium")));
        assertEquals(new Engine(15L, 1500, 9.0, "V12", new LevelEntity(6L, "Premium")), carConfiguration.getEngine());
    }

    @Test
    @DisplayName("Test not null engine")
    void testValidEngineInCarConfiguration() {
        assertNotNull(carConfiguration.getEngine());
        carConfiguration.setEngine(null);
        assertThrows(ConstraintViolationException.class, () -> validateCarConfiguration(carConfiguration));
    }

    @Test
    @DisplayName("Test change of induction level")
    void setInductionLevel() {
        carConfiguration.setInductionLevelEntity(new LevelEntity(2L, "Deportivo"));
        assertEquals(new LevelEntity(2L, "Deportivo"), carConfiguration.getInductionLevelEntity());
    }

    @Test
    @DisplayName("Test not null induction level")
    void testValidInductionLevelInCarConfiguration() {
        assertNotNull(carConfiguration.getInductionLevelEntity());
        carConfiguration.setInductionLevelEntity(null);
        assertThrows(ConstraintViolationException.class, () -> validateCarConfiguration(carConfiguration));
    }

    @Test
    @DisplayName("Test change of Ecu level")
    void setEcuLevel() {
        carConfiguration.setEcuLevelEntity(new LevelEntity(3L, "Pro"));
        assertEquals(new LevelEntity(3L, "Pro"), carConfiguration.getEcuLevelEntity());
    }

    @Test
    @DisplayName("Test not null Ecu level")
    void testValidEcuLevelInCarConfiguration() {
        assertNotNull(carConfiguration.getEcuLevelEntity());
        carConfiguration.setEcuLevelEntity(null);
        assertThrows(ConstraintViolationException.class, () -> validateCarConfiguration(carConfiguration));
    }

    @Test
    @DisplayName("Test change of Injection level")
    void setInjectionLevel() {
        carConfiguration.setInjectionLevelEntity(new LevelEntity(3L, "Pro"));
        assertEquals(new LevelEntity(3L, "Pro"), carConfiguration.getInjectionLevelEntity());
    }

    @Test
    @DisplayName("Test not null Injection level")
    void testValidInjectionLevelInCarConfiguration() {
        assertNotNull(carConfiguration.getInjectionLevelEntity());
        carConfiguration.setInjectionLevelEntity(null);
        assertThrows(ConstraintViolationException.class, () -> validateCarConfiguration(carConfiguration));
    }

    @Test
    @DisplayName("Test change of Exhaust level")
    void setEscapeLevel() {
        carConfiguration.setEscapeLevelEntity(new LevelEntity(3L, "Pro"));
        assertEquals(new LevelEntity(3L, "Pro"), carConfiguration.getEscapeLevelEntity());
    }

    @Test
    @DisplayName("Test not null Exhaust level")
    void testValidEscapeLevelInCarConfiguration() {
        assertNotNull(carConfiguration.getEscapeLevelEntity());
        carConfiguration.setEscapeLevelEntity(null);
        assertThrows(ConstraintViolationException.class, () -> validateCarConfiguration(carConfiguration));
    }

    @Test
    @DisplayName("Test change of Turbo")
    void setTurbo() {
        carConfiguration.setTurbo(new Turbo(5L, new TurboType(5L, "SobreAlimentador"), new LevelEntity(1L, "Básico")));
        assertEquals(new Turbo(5L, new TurboType(5L, "SobreAlimentador"), new LevelEntity(1L, "Básico")), carConfiguration.getTurbo());
    }

    @Test
    @DisplayName("Test not null Turbo")
    void testValidTurboInCarConfiguration() {
        assertNotNull(carConfiguration.getTurbo());
        carConfiguration.setTurbo(null);
        assertThrows(ConstraintViolationException.class, () -> validateCarConfiguration(carConfiguration));
    }

    @Test
    @DisplayName("Test change of Nitro level")
    void setNitroLevel() {
        carConfiguration.setNitroLevelEntity(new LevelEntity(3L, "Pro"));
        assertEquals(new LevelEntity(3L, "Pro"), carConfiguration.getNitroLevelEntity());
    }

    @Test
    @DisplayName("Test not null Nitro level")
    void testValidNitroLevelInCarConfiguration() {
        assertNotNull(carConfiguration.getNitroLevelEntity());
        carConfiguration.setNitroLevelEntity(null);
        assertThrows(ConstraintViolationException.class, () -> validateCarConfiguration(carConfiguration));
    }

    @Test
    @DisplayName("Test change of Suspension")
    void setSuspension() {
        carConfiguration.setSuspension(new Suspension(5L, new StreetType(5L, "Asfalto"), new LevelEntity(3L, "Pro")));
        assertEquals(new Suspension(5L, new StreetType(5L, "Asfalto"), new LevelEntity(3L, "Pro")), carConfiguration.getSuspension());
    }

    @Test
    @DisplayName("Test not null Suspension")
    void testValidSuspensionInCarConfiguration() {
        assertNotNull(carConfiguration.getSuspension());
        carConfiguration.setSuspension(null);
        assertThrows(ConstraintViolationException.class, () -> validateCarConfiguration(carConfiguration));
    }

    @Test
    @DisplayName("Test change of Brake Level")
    void setBrakeLevel() {
        carConfiguration.setBrakeLevelEntity(new LevelEntity(3L, "Pro"));
        assertEquals(new LevelEntity(3L, "Pro"), carConfiguration.getBrakeLevelEntity());
    }

    @Test
    @DisplayName("Test not null Brake level")
    void testValidBrakeLevelInCarConfiguration() {
        assertNotNull(carConfiguration.getBrakeLevelEntity());
        carConfiguration.setBrakeLevelEntity(null);
        assertThrows(ConstraintViolationException.class, () -> validateCarConfiguration(carConfiguration));
    }

    @Test
    @DisplayName("Test change of Tire")
    void setTire() {
        carConfiguration.setTire(new Tire(5L, new StreetType(4L, "Agarre"), new LevelEntity(1L, "Básico")));
        assertEquals(new Tire(5L, new StreetType(4L, "Agarre"), new LevelEntity(1L, "Básico")), carConfiguration.getTire());
    }

    @Test
    @DisplayName("Test not null Tire")
    void testValidTireInCarConfiguration() {
        assertNotNull(carConfiguration.getTire());
        carConfiguration.setTire(null);
        assertThrows(ConstraintViolationException.class, () -> validateCarConfiguration(carConfiguration));
    }

    @Test
    @DisplayName("Test changue of Embrague level")
    void setEmbragueLevel() {
        carConfiguration.setEmbragueLevelEntity(new LevelEntity(3L, "Pro"));
        assertEquals(new LevelEntity(3L, "Pro"), carConfiguration.getEmbragueLevelEntity());
    }

    @Test
    @DisplayName("Test not null Embrague level")
    void testValidEmbragueLevelInCarConfiguration() {
        assertNotNull(carConfiguration.getEmbragueLevelEntity());
        carConfiguration.setEmbragueLevelEntity(null);
        assertThrows(ConstraintViolationException.class, () -> validateCarConfiguration(carConfiguration));
    }

    @Test
    @DisplayName("Test change of Gear")
    void setGear() {
        carConfiguration.setGear(new Gear(5L, 3, new LevelEntity(3L, "Pro")));
        assertEquals(new Gear(5L, 3, new LevelEntity(3L, "Pro")), carConfiguration.getGear());
    }

    @Test
    @DisplayName("Test not null Gear")
    void testValidGearInCarConfiguration() {
        assertNotNull(carConfiguration.getGear());
        carConfiguration.setGear(null);
        assertThrows(ConstraintViolationException.class, () -> validateCarConfiguration(carConfiguration));
    }

    @Test
    @DisplayName("Test change of Differential level")
    void setDifferentialLevel() {
        carConfiguration.setDifferentialLevelEntity(new LevelEntity(3L, "Pro"));
        assertEquals(new LevelEntity(3L, "Pro"), carConfiguration.getDifferentialLevelEntity());
    }

    @Test
    @DisplayName("Test not null Differential level")
    void testValidDifferentialLevelInCarConfiguration() {
        assertNotNull(carConfiguration.getDifferentialLevelEntity());
        carConfiguration.setDifferentialLevelEntity(null);
        assertThrows(ConstraintViolationException.class, () -> validateCarConfiguration(carConfiguration));
    }

    @Test
    @DisplayName("Test change of Top speed")
    void setTopSpeed() {
        carConfiguration.setTopSpeed(100);
        assertEquals(100, carConfiguration.getTopSpeed());
    }

    @Test
    @DisplayName("Test not null and Max of 500 of Top speed")
    void testValidTopSpeedInCarConfiguration() {
        carConfiguration.setTopSpeed(null);
        assertThrows(ConstraintViolationException.class, () -> validateCarConfiguration(carConfiguration));
        carConfiguration.setTopSpeed(501);
        assertThrows(ConstraintViolationException.class, () -> validateCarConfiguration(carConfiguration));
    }

    @Test
    @DisplayName("Test change of time of One Hundred meters")
    void setOneHundred() {
        carConfiguration.setOneHundred(9.8);
        assertEquals(9.8, carConfiguration.getOneHundred());
    }

    @Test
    @DisplayName("Test not null and Max of 100 seconds of Time of One Hundred meters")
    void testValidOneHundredInCarConfiguration() {
        carConfiguration.setOneHundred(null);
        assertThrows(ConstraintViolationException.class, () -> validateCarConfiguration(carConfiguration));
        carConfiguration.setOneHundred(100.1);
        assertThrows(ConstraintViolationException.class, () -> validateCarConfiguration(carConfiguration));
    }

    @Test
    @DisplayName("Test change of Power")
    void setPower() {
        carConfiguration.setPower(1500);
        assertEquals(1500, carConfiguration.getPower());
    }

    @Test
    @DisplayName("Test not null and Max of 5000 of Power")
    void testValidPowerInCarConfiguration() {
        carConfiguration.setPower(null);
        assertThrows(ConstraintViolationException.class, () -> validateCarConfiguration(carConfiguration));
        carConfiguration.setPower(5001);
        assertThrows(ConstraintViolationException.class, () -> validateCarConfiguration(carConfiguration));
    }

    @Test
    @DisplayName("Test change of Par(N/m)")
    void setPar() {
        carConfiguration.setPar(1000);
        assertEquals(1000, carConfiguration.getPar());
    }

    @Test
    @DisplayName("Test not null and Max of 5000 of Par(N/m)")
    void testValidParInCarConfiguration() {
        carConfiguration.setPar(null);
        assertThrows(ConstraintViolationException.class, () -> validateCarConfiguration(carConfiguration));
        carConfiguration.setPar(5001);
        assertThrows(ConstraintViolationException.class, () -> validateCarConfiguration(carConfiguration));
    }

    @Test
    @DisplayName("Test change of Four Hundred")
    void setFourHundred() {
        carConfiguration.setFourHundred(9.8);
        assertEquals(9.8, carConfiguration.getFourHundred());
    }

    @Test
    @DisplayName("Test not null and Max of 100 seconds of Four Hundred")
    void testValidFourHundredInCarConfiguration() {
        carConfiguration.setFourHundred(null);
        assertThrows(ConstraintViolationException.class, () -> validateCarConfiguration(carConfiguration));
        carConfiguration.setFourHundred(100.1);
        assertThrows(ConstraintViolationException.class, () -> validateCarConfiguration(carConfiguration));
    }

    @Test
    @DisplayName("Test change of Driver")
    void setDriver() {
        carConfiguration.setDriver(new Driver(1L, 250, 5, 5, true, new InitSkid(4L, "NO")));
        assertEquals(new Driver(1L, 250, 5, 5, true, new InitSkid(4L, "NO")), carConfiguration.getDriver());
    }

    @Test
    @DisplayName("Test not null Driver")
    void testValidDriverInCarConfiguration() {
        assertNotNull(carConfiguration.getDriver());
        carConfiguration.setDriver(null);
        assertThrows(ConstraintViolationException.class, () -> validateCarConfiguration(carConfiguration));
    }

    @Test
    @DisplayName("Test equals")
    void testEquals() {
        CarConfiguration carConfiguration2 = new CarConfiguration();
        carConfiguration2.setId(30L);
        carConfiguration2.setCar(new Car(20L, new Maker(4L, "Ford"), "Mustang", "1964"));
        carConfiguration2.setEngine(new Engine(10L, 1000, 8.5, "V8 híbrido", new LevelEntity(5L, "Elite")));
        carConfiguration2.setInductionLevelEntity(new LevelEntity(1L, "Básico"));
        carConfiguration2.setEcuLevelEntity(new LevelEntity(2L, "Deportivo"));
        carConfiguration2.setInjectionLevelEntity(new LevelEntity(3L, "Pro"));
        carConfiguration2.setEscapeLevelEntity(new LevelEntity(4L, "Super"));
        carConfiguration2.setTurbo(new Turbo(5L, new TurboType(2L, "DobleTurboCompresor"), new LevelEntity(1L, "Básico")));
        carConfiguration2.setNitroLevelEntity(new LevelEntity(5L, "Elite"));
        carConfiguration2.setSuspension(new Suspension(6L, new StreetType(2L, "TodoTerreno"), new LevelEntity(2L, "Deportivo")));
        carConfiguration2.setBrakeLevelEntity(new LevelEntity(3L, "Pro"));
        carConfiguration2.setTire(new Tire(7L, new StreetType(4L, "Derrape"), new LevelEntity(3L, "Pro")));
        carConfiguration2.setEmbragueLevelEntity(new LevelEntity(1L, "Básico"));
        carConfiguration2.setGear(new Gear(3L, 3, new LevelEntity(3L, "Deportivo")));
        carConfiguration2.setDifferentialLevelEntity(new LevelEntity(2L, "Deportivo"));
        carConfiguration2.setTopSpeed(350);
        carConfiguration2.setOneHundred(3.5);
        carConfiguration2.setFourHundred(10.5);
        carConfiguration2.setPower(1500);
        carConfiguration2.setPar(2000);
        carConfiguration2.setDriver(new Driver(2L, 100, 5, 5, true, new InitSkid(4L, "POR DEFECTO")));
        carConfiguration2.setAuxiliaryOne(new Auxiliary(2L, "AGARRE DE NITRO", new LevelEntity(2L,"Pro")));
        carConfiguration2.setAuxiliaryTwo(new Auxiliary(2L, "AGARRE DE NITRO", new LevelEntity(2L,"Pro")));
        carConfiguration2.setClasses(new Classes(4L,"S"));
        assertEquals(carConfiguration, carConfiguration2);
    }

    @Test
    @DisplayName("Test count fields and methods")
    void testNumberOfMethodsAndFields() {
        assertEquals(24, CarConfiguration.class.getDeclaredFields().length);
        assertEquals(52, CarConfiguration.class.getDeclaredMethods().length);
    }

    private void validateCarConfiguration(CarConfiguration carConfiguration) {
        Set<ConstraintViolation<CarConfiguration>> violations = validator.validate(carConfiguration);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}