package org.eduardomaravill.sdd_catalogo.dtos.car_dtos;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CarConfigurationDtoTest {

    private CarConfigurationDto carConfigurationDto;
    private Validator validator;

    @BeforeEach
    void setUp() {
        carConfigurationDto = new CarConfigurationDto();
        carConfigurationDto.setId(30L);
        carConfigurationDto.setCarDto(new CarDto(20L, new MakerDto(4L, "Ford"), "Mustang", "1964"));
        carConfigurationDto.setEngineDto(new EngineDto(10L, 1000, 8.5, "V8 híbrido", new LevelDto(5L, "Elite")));
        carConfigurationDto.setInductionLevelDto(new LevelDto(1L, "Básico"));
        carConfigurationDto.setEcuLevelDto(new LevelDto(2L, "Deportivo"));
        carConfigurationDto.setInjectionLevelDto(new LevelDto(3L, "Pro"));
        carConfigurationDto.setEscapeLevelDto(new LevelDto(4L, "Super"));
        carConfigurationDto.setTurboDto(new TurboDto(5L, new TurboTypeDto(2L, "DobleTurboCompresor"), new LevelDto(1L, "Básico")));
        carConfigurationDto.setNitroLevelDto(new LevelDto(5L, "Elite"));
        carConfigurationDto.setSuspensionDto(new SuspensionDto(6L, new StreetTypeDto(2L, "TodoTerreno"), new LevelDto(2L, "Deportivo")));
        carConfigurationDto.setBrakeLevelDto(new LevelDto(3L, "Pro"));
        carConfigurationDto.setTireDto(new TireDto(7L, new StreetTypeDto(4L, "Derrape"), new LevelDto(3L, "Pro")));
        carConfigurationDto.setEmbragueLevelDto(new LevelDto(1L, "Básico"));
        carConfigurationDto.setGearDto(new GearDto(3L, 3, new LevelDto(3L, "Deportivo")));
        carConfigurationDto.setDifferentialLevelDto(new LevelDto(2L, "Deportivo"));
        carConfigurationDto.setTopSpeed(350);
        carConfigurationDto.setOneHundred(3.5);
        carConfigurationDto.setFourHundred(10.5);
        carConfigurationDto.setPower(1500);
        carConfigurationDto.setPar(2000);
        carConfigurationDto.setDriverDto(new DriverDto(2L, 100, 5, 5, true, new InitSkidDto(4L, "POR DEFECTO")));
        carConfigurationDto.setAuxiliarOneDto(new AuxiliarDto(2L,"NITRO POR DERRAPE", new LevelDto(2L,"Deportivo")));
        carConfigurationDto.setAuxiliarTwoDto(new AuxiliarDto(2L,"NITRO POR DERRAPE", new LevelDto(2L,"Deportivo")));
        carConfigurationDto.setClassesDto(new ClassesDto(3L,"A+"));
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    @Test
    @DisplayName("Test get ID")
    void getId() {
        assertEquals(30L, carConfigurationDto.getId());
    }

    @Test
    @DisplayName("Test get CarDto")
    void getCarDto() {
        assertEquals(new CarDto(20L, new MakerDto(4L, "Ford"), "Mustang", "1964"), carConfigurationDto.getCarDto());
    }

    @Test
    @DisplayName("Test get EngineDto")
    void getEngineDto() {
        assertEquals(new EngineDto(10L, 1000, 8.5, "V8 híbrido", new LevelDto(5L, "Elite")), carConfigurationDto.getEngineDto());
    }

    @Test
    @DisplayName("Test get Induction level")
    void getInductionLevelDto() {
        assertEquals(new LevelDto(1L, "Básico"), carConfigurationDto.getInductionLevelDto());
    }

    @Test
    @DisplayName("Test get Ecu Level")
    void getEcuLevelDto() {
        assertEquals(new LevelDto(2L, "Deportivo"), carConfigurationDto.getEcuLevelDto());
    }

    @Test
    @DisplayName("Test get Injection Level")
    void getInjectionLevelDto() {
        assertEquals(new LevelDto(3L, "Pro"), carConfigurationDto.getInjectionLevelDto());
    }

    @Test
    @DisplayName("Test get Exhaust Level")
    void getEscapeLevelDto() {
        assertEquals(new LevelDto(4L, "Super"), carConfigurationDto.getEscapeLevelDto());
    }

    @Test
    @DisplayName("Test get TurboDto")
    void getTurboDto() {
        assertEquals(new TurboDto(5L, new TurboTypeDto(2L, "DobleTurboCompresor"), new LevelDto(1L, "Básico")), carConfigurationDto.getTurboDto());
    }

    @Test
    @DisplayName("Test get Nitro Level")
    void getNitroLevelDto() {
        assertEquals(new LevelDto(5L, "Elite"), carConfigurationDto.getNitroLevelDto());
    }

    @Test
    @DisplayName("Test get Suspension")
    void getSuspensionDto() {
        assertEquals(new SuspensionDto(6L, new StreetTypeDto(2L, "TodoTerreno"), new LevelDto(2L, "Deportivo")), carConfigurationDto.getSuspensionDto());
    }

    @Test
    @DisplayName("Test get Brake Level")
    void getBrakeLevelDto() {
        assertEquals(new LevelDto(3L, "Pro"), carConfigurationDto.getBrakeLevelDto());
    }

    @Test
    @DisplayName("Test get TireDto")
    void getTireDto() {
        assertEquals(new TireDto(7L, new StreetTypeDto(4L, "Derrape"), new LevelDto(3L, "Pro")), carConfigurationDto.getTireDto());
    }

    @Test
    @DisplayName("Test get Embrague Level")
    void getEmbragueLevelDto() {
        assertEquals(new LevelDto(1L, "Básico"), carConfigurationDto.getEmbragueLevelDto());
    }

    @Test
    @DisplayName("Test get GearDto")
    void getGearDto() {
        assertEquals(new GearDto(3L, 3, new LevelDto(3L, "Deportivo")), carConfigurationDto.getGearDto());
    }

    @Test
    @DisplayName("Test get Differential Level")
    void getDifferentialLevelDto() {
        assertEquals(new LevelDto(2L, "Deportivo"), carConfigurationDto.getDifferentialLevelDto());
    }

    @Test
    @DisplayName("Test get Top Speed")
    void getTopSpeed() {
        assertEquals(350, carConfigurationDto.getTopSpeed());
    }

    @Test
    @DisplayName("Test get Time of One Hundred")
    void getOneHundred() {
        assertEquals(3.5, carConfigurationDto.getOneHundred());
    }

    @Test
    @DisplayName("Test get Power")
    void getPower() {
        assertEquals(1500, carConfigurationDto.getPower());
    }

    @Test
    @DisplayName("Test get Par(N/m)")
    void getPar() {
        assertEquals(2000, carConfigurationDto.getPar());
    }

    @Test
    @DisplayName("Test get Time of Four Hundred")
    void getFourHundred() {
        assertEquals(10.5, carConfigurationDto.getFourHundred());
    }

    @Test
    @DisplayName("Test get DriverDto")
    void getDriverDto() {
        assertEquals(new DriverDto(2L, 100, 5, 5, true, new InitSkidDto(4L, "POR DEFECTO")), carConfigurationDto.getDriverDto());
    }

    @Test
    @DisplayName("Test change of ID")
    void setId() {
        carConfigurationDto.setId(50L);
        assertEquals(50L, carConfigurationDto.getId());
    }

    @Test
    @DisplayName("Test change of CarDto")
    void setCarDto() {
        carConfigurationDto.setCarDto(new CarDto(30L, new MakerDto(5L, "Chevrolet"), "Corvette", "1965"));
        assertEquals(new CarDto(30L, new MakerDto(5L, "Chevrolet"), "Corvette", "1965"), carConfigurationDto.getCarDto());
    }

    @Test
    @DisplayName("Test not null CarDto")
    void testValidCarDtoInCarConfigurationDto() {
        assertNotNull(carConfigurationDto.getCarDto());
        carConfigurationDto.setCarDto(null);
        assertThrows(ConstraintViolationException.class, () -> validateCarConfigurationDto(carConfigurationDto));
    }

    @Test
    @DisplayName("Test change of EngineDto")
    void setEngineDto() {
        carConfigurationDto.setEngineDto(new EngineDto(15L, 1500, 9.0, "V12", new LevelDto(6L, "Legendario")));
        assertEquals(new EngineDto(15L, 1500, 9.0, "V12", new LevelDto(6L, "Legendario")), carConfigurationDto.getEngineDto());
    }

    @Test
    @DisplayName("Test not null EngineDto")
    void testValidEngineDtoInCarConfigurationDto() {
        assertNotNull(carConfigurationDto.getEngineDto());
        carConfigurationDto.setEngineDto(null);
        assertThrows(ConstraintViolationException.class, () -> validateCarConfigurationDto(carConfigurationDto));
    }

    @Test
    @DisplayName("Test change of Induction level")
    void setInductionLevelDto() {
        carConfigurationDto.setInductionLevelDto(new LevelDto(4L, "Máximo"));
        assertEquals(new LevelDto(4L, "Máximo"), carConfigurationDto.getInductionLevelDto());
    }

    @Test
    @DisplayName("Test not null Induction level")
    void testValidInductionLevelDtoInCarConfigurationDto() {
        assertNotNull(carConfigurationDto.getInductionLevelDto());
        carConfigurationDto.setInductionLevelDto(null);
        assertThrows(ConstraintViolationException.class, () -> validateCarConfigurationDto(carConfigurationDto));
    }

    @Test
    @DisplayName("Test change of Ecu Level")
    void setEcuLevelDto() {
        carConfigurationDto.setEcuLevelDto(new LevelDto(3L, "Ultimate"));
        assertEquals(new LevelDto(3L, "Ultimate"), carConfigurationDto.getEcuLevelDto());
    }

    @Test
    @DisplayName("Test not null Ecu Level")
    void testValidEcuLevelDtoInCarConfigurationDto() {
        assertNotNull(carConfigurationDto.getEcuLevelDto());
        carConfigurationDto.setEcuLevelDto(null);
        assertThrows(ConstraintViolationException.class, () -> validateCarConfigurationDto(carConfigurationDto));
    }

    @Test
    @DisplayName("Test change of Injection Level")
    void setInjectionLevelDto() {
        carConfigurationDto.setInjectionLevelDto(new LevelDto(4L, "Legendario"));
        assertEquals(new LevelDto(4L, "Legendario"), carConfigurationDto.getInjectionLevelDto());
    }

    @Test
    @DisplayName("Test not null Injection Level")
    void testValidInjectionLevelDtoInCarConfigurationDto() {
        assertNotNull(carConfigurationDto.getInjectionLevelDto());
        carConfigurationDto.setInjectionLevelDto(null);
        assertThrows(ConstraintViolationException.class, () -> validateCarConfigurationDto(carConfigurationDto));
    }

    @Test
    @DisplayName("Test change of Exhaust Level")
    void setEscapeLevelDto() {
        carConfigurationDto.setEscapeLevelDto(new LevelDto(3L, "Ultimate"));
        assertEquals(new LevelDto(3L, "Ultimate"), carConfigurationDto.getEscapeLevelDto());
    }

    @Test
    @DisplayName("Test not null Exhaust Level")
    void testValidEscapeLevelDtoInCarConfigurationDto() {
        assertNotNull(carConfigurationDto.getEscapeLevelDto());
        carConfigurationDto.setEscapeLevelDto(null);
        assertThrows(ConstraintViolationException.class, () -> validateCarConfigurationDto(carConfigurationDto));
    }

    @Test
    @DisplayName("Test change of TurboDto")
    void setTurboDto() {
        carConfigurationDto.setTurboDto(new TurboDto(6L, new TurboTypeDto(3L, "TurboPropulsor"), new LevelDto(2L, "Máximo")));
        assertEquals(new TurboDto(6L, new TurboTypeDto(3L, "TurboPropulsor"), new LevelDto(2L, "Máximo")), carConfigurationDto.getTurboDto());
    }

    @Test
    @DisplayName("Test not null TurboDto")
    void testValidTurboDtoInCarConfigurationDto() {
        assertNotNull(carConfigurationDto.getTurboDto());
        carConfigurationDto.setTurboDto(null);
        assertThrows(ConstraintViolationException.class, () -> validateCarConfigurationDto(carConfigurationDto));
    }

    @Test
    @DisplayName("Test change of Nitro Level")
    void setNitroLevelDto() {
        carConfigurationDto.setNitroLevelDto(new LevelDto(2L, "Pro"));
        assertEquals(new LevelDto(2L, "Pro"), carConfigurationDto.getNitroLevelDto());
    }

    @Test
    @DisplayName("Test not null Nitro Level")
    void testValidNitroLevelDtoInCarConfigurationDto() {
        assertNotNull(carConfigurationDto.getNitroLevelDto());
        carConfigurationDto.setNitroLevelDto(null);
        assertThrows(ConstraintViolationException.class, () -> validateCarConfigurationDto(carConfigurationDto));
    }

    @Test
    @DisplayName("Test change of Suspension")
    void setSuspensionDto() {
        carConfigurationDto.setSuspensionDto(new SuspensionDto(7L, new StreetTypeDto(3L, "Pista"), new LevelDto(3L, "Pro")));
        assertEquals(new SuspensionDto(7L, new StreetTypeDto(3L, "Pista"), new LevelDto(3L, "Pro")), carConfigurationDto.getSuspensionDto());
    }

    @Test
    @DisplayName("Test not null Suspension")
    void testValidSuspensionDtoInCarConfigurationDto() {
        assertNotNull(carConfigurationDto.getSuspensionDto());
        carConfigurationDto.setSuspensionDto(null);
        assertThrows(ConstraintViolationException.class, () -> validateCarConfigurationDto(carConfigurationDto));
    }

    @Test
    @DisplayName("Test change of Brake Level")
    void setBrakeLevelDto() {
        carConfigurationDto.setBrakeLevelDto(new LevelDto(2L, "Sport"));
        assertEquals(new LevelDto(2L, "Sport"), carConfigurationDto.getBrakeLevelDto());
    }

    @Test
    @DisplayName("Test not null Brake Level")
    void testValidBrakeLevelDtoInCarConfigurationDto() {
        assertNotNull(carConfigurationDto.getBrakeLevelDto());
        carConfigurationDto.setBrakeLevelDto(null);
        assertThrows(ConstraintViolationException.class, () -> validateCarConfigurationDto(carConfigurationDto));
    }

    @Test
    @DisplayName("Test change of TireDto")
    void setTireDto() {
        carConfigurationDto.setTireDto(new TireDto(8L, new StreetTypeDto(2L, "TodoTerreno"), new LevelDto(4L, "Elite")));
        assertEquals(new TireDto(8L, new StreetTypeDto(2L, "TodoTerreno"), new LevelDto(4L, "Elite")), carConfigurationDto.getTireDto());
    }

    @Test
    @DisplayName("Test not null TireDto")
    void testValidTireDtoInCarConfigurationDto() {
        assertNotNull(carConfigurationDto.getTireDto());
        carConfigurationDto.setTireDto(null);
        assertThrows(ConstraintViolationException.class, () -> validateCarConfigurationDto(carConfigurationDto));
    }

    @Test
    @DisplayName("Test change of Embrague Level")
    void setEmbragueLevelDto() {
        carConfigurationDto.setEmbragueLevelDto(new LevelDto(2L, "Sport"));
        assertEquals(new LevelDto(2L, "Sport"), carConfigurationDto.getEmbragueLevelDto());
    }

    @Test
    @DisplayName("Test not null Embrague Level")
    void testValidEmbragueLevelDtoInCarConfigurationDto() {
        assertNotNull(carConfigurationDto.getEmbragueLevelDto());
        carConfigurationDto.setEmbragueLevelDto(null);
        assertThrows(ConstraintViolationException.class, () -> validateCarConfigurationDto(carConfigurationDto));
    }

    @Test
    @DisplayName("Test change of GearDto")
    void setGearDto() {
        carConfigurationDto.setGearDto(new GearDto(4L, 4, new LevelDto(4L, "Elite")));
        assertEquals(new GearDto(4L, 4, new LevelDto(4L, "Elite")), carConfigurationDto.getGearDto());
    }

    @Test
    @DisplayName("Test not null GearDto")
    void testValidGearDtoInCarConfigurationDto() {
        assertNotNull(carConfigurationDto.getGearDto());
        carConfigurationDto.setGearDto(null);
        assertThrows(ConstraintViolationException.class, () -> validateCarConfigurationDto(carConfigurationDto));
    }

    @Test
    @DisplayName("Test change of Differential Level")
    void setDifferentialLevelDto() {
        carConfigurationDto.setDifferentialLevelDto(new LevelDto(3L, "Elite"));
        assertEquals(new LevelDto(3L, "Elite"), carConfigurationDto.getDifferentialLevelDto());
    }

    @Test
    @DisplayName("Test not null Differential Level")
    void testValidDifferentialLevelDtoInCarConfigurationDto() {
        assertNotNull(carConfigurationDto.getDifferentialLevelDto());
        carConfigurationDto.setDifferentialLevelDto(null);
        assertThrows(ConstraintViolationException.class, () -> validateCarConfigurationDto(carConfigurationDto));
    }

    @Test
    @DisplayName("Test change of Top speed")
    void setTopSpeed() {
        carConfigurationDto.setTopSpeed(100);
        assertEquals(100, carConfigurationDto.getTopSpeed());
    }

    @Test
    @DisplayName("Test not null and Max of 450 of Top speed")
    void testValidTopSpeedInCarConfiguration() {
        carConfigurationDto.setTopSpeed(null);
        assertThrows(ConstraintViolationException.class, () -> validateCarConfigurationDto(carConfigurationDto));
        carConfigurationDto.setTopSpeed(451);
        assertThrows(ConstraintViolationException.class, () -> validateCarConfigurationDto(carConfigurationDto));
    }

    @Test
    @DisplayName("Test change of time of One Hundred meters")
    void setOneHundred() {
        carConfigurationDto.setOneHundred(9.8);
        assertEquals(9.8, carConfigurationDto.getOneHundred());
    }

    @Test
    @DisplayName("Test not null and Max of 10 seconds of Time of One Hundred meters")
    void testValidOneHundredInCarConfiguration() {
        carConfigurationDto.setOneHundred(null);
        assertThrows(ConstraintViolationException.class, () -> validateCarConfigurationDto(carConfigurationDto));
        carConfigurationDto.setOneHundred(10.1);
        assertThrows(ConstraintViolationException.class, () -> validateCarConfigurationDto(carConfigurationDto));
    }

    @Test
    @DisplayName("Test change of Power")
    void setPower() {
        carConfigurationDto.setPower(1500);
        assertEquals(1500, carConfigurationDto.getPower());
    }

    @Test
    @DisplayName("Test not null and Max of 3000 of Power")
    void testValidPowerInCarConfiguration() {
        carConfigurationDto.setPower(null);
        assertThrows(ConstraintViolationException.class, () -> validateCarConfigurationDto(carConfigurationDto));
        carConfigurationDto.setPower(3001);
        assertThrows(ConstraintViolationException.class, () -> validateCarConfigurationDto(carConfigurationDto));
    }

    @Test
    @DisplayName("Test change of Par(N/m)")
    void setPar() {
        carConfigurationDto.setPar(1000);
        assertEquals(1000, carConfigurationDto.getPar());
    }

    @Test
    @DisplayName("Test not null and Max of 5000 of Par(N/m)")
    void testValidParInCarConfiguration() {
        carConfigurationDto.setPar(null);
        assertThrows(ConstraintViolationException.class, () -> validateCarConfigurationDto(carConfigurationDto));
        carConfigurationDto.setPar(5001);
        assertThrows(ConstraintViolationException.class, () -> validateCarConfigurationDto(carConfigurationDto));
    }

    @Test
    @DisplayName("Test change of Four Hundred")
    void setFourHundred() {
        carConfigurationDto.setFourHundred(9.8);
        assertEquals(9.8, carConfigurationDto.getFourHundred());
    }

    @Test
    @DisplayName("Test not null and Max of 30 seconds of Four Hundred")
    void testValidFourHundredInCarConfiguration() {
        carConfigurationDto.setFourHundred(null);
        assertThrows(ConstraintViolationException.class, () -> validateCarConfigurationDto(carConfigurationDto));
        carConfigurationDto.setFourHundred(30.1);
        assertThrows(ConstraintViolationException.class, () -> validateCarConfigurationDto(carConfigurationDto));
    }

    @Test
    @DisplayName("Test change of Driver")
    void setDriverDto() {
        carConfigurationDto.setDriverDto(new DriverDto(2L,85,3,7,true,new InitSkidDto(2L,"NO")));
        assertEquals(new DriverDto(2L,85,3,7,true,new InitSkidDto(2L,"NO")), carConfigurationDto.getDriverDto());
    }

    @Test
    @DisplayName("Test not null Driver")
    void testValidDriverDtoInCarConfigurationDto() {
        assertNotNull(carConfigurationDto.getDriverDto());
        carConfigurationDto.setDriverDto(null);
        assertThrows(ConstraintViolationException.class, () -> validateCarConfigurationDto(carConfigurationDto));
    }

    @Test
    @DisplayName("Test equals")
    void testEquals() {
        CarConfigurationDto carConfigurationDto2 = new CarConfigurationDto();
        carConfigurationDto2.setId(30L);
        carConfigurationDto2.setCarDto(new CarDto(20L, new MakerDto(4L, "Ford"), "Mustang", "1964"));
        carConfigurationDto2.setEngineDto(new EngineDto(10L, 1000, 8.5, "V8 híbrido", new LevelDto(5L, "Elite")));
        carConfigurationDto2.setInductionLevelDto(new LevelDto(1L, "Básico"));
        carConfigurationDto2.setEcuLevelDto(new LevelDto(2L, "Deportivo"));
        carConfigurationDto2.setInjectionLevelDto(new LevelDto(3L, "Pro"));
        carConfigurationDto2.setEscapeLevelDto(new LevelDto(4L, "Super"));
        carConfigurationDto2.setTurboDto(new TurboDto(5L, new TurboTypeDto(2L, "DobleTurboCompresor"), new LevelDto(1L, "Básico")));
        carConfigurationDto2.setNitroLevelDto(new LevelDto(5L, "Elite"));
        carConfigurationDto2.setSuspensionDto(new SuspensionDto(6L, new StreetTypeDto(2L, "TodoTerreno"), new LevelDto(2L, "Deportivo")));
        carConfigurationDto2.setBrakeLevelDto(new LevelDto(3L, "Pro"));
        carConfigurationDto2.setTireDto(new TireDto(7L, new StreetTypeDto(4L, "Derrape"), new LevelDto(3L, "Pro")));
        carConfigurationDto2.setEmbragueLevelDto(new LevelDto(1L, "Básico"));
        carConfigurationDto2.setGearDto(new GearDto(3L, 3, new LevelDto(3L, "Deportivo")));
        carConfigurationDto2.setDifferentialLevelDto(new LevelDto(2L, "Deportivo"));
        carConfigurationDto2.setTopSpeed(350);
        carConfigurationDto2.setOneHundred(3.5);
        carConfigurationDto2.setFourHundred(10.5);
        carConfigurationDto2.setPower(1500);
        carConfigurationDto2.setPar(2000);
        carConfigurationDto2.setDriverDto(new DriverDto(2L, 100, 5, 5, true, new InitSkidDto(4L, "POR DEFECTO")));
        carConfigurationDto2.setAuxiliarOneDto(new AuxiliarDto(2L,"NITRO POR DERRAPE",new LevelDto(2L,"Deportivo")));
        carConfigurationDto2.setAuxiliarTwoDto(new AuxiliarDto(2L,"NITRO POR DERRAPE",new LevelDto(2L,"Deportivo")));
        carConfigurationDto2.setClassesDto(new ClassesDto(3L,"A+"));
        assertEquals(carConfigurationDto, carConfigurationDto2);
    }

    @Test
    @DisplayName("Test count fields and methods")
    void testNumbersOfMethodsAndFields() {
        assertEquals(24, CarConfigurationDto.class.getDeclaredFields().length);
        assertEquals(52, CarConfigurationDto.class.getDeclaredMethods().length);
    }

    private void validateCarConfigurationDto(CarConfigurationDto carConfigurationDto) {
        Set<ConstraintViolation<CarConfigurationDto>> violations = validator.validate(carConfigurationDto);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}