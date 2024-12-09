package org.eduardomaravill.sdd_catalogo.dtos.car_dtos;

import jakarta.validation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class EngineDtoTest {
    private EngineDto engineDto;
    private Validator validator;

    @BeforeEach
    void setUp() {
        engineDto = new EngineDto(5L, 450, 5.3, "V6", new LevelDto(1L, "Básico"));
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    @Test
    @DisplayName("Test get ID")
    void getId() {
        assertEquals(5L, engineDto.getId());
    }

    @Test
    @DisplayName("Test get Bhp")
    void getBhp() {
        assertEquals(450, engineDto.getBhp());
    }

    @Test
    @DisplayName("Test get Liters")
    void getLiters() {
        assertEquals(5.3, engineDto.getLiters(), 0.01);
    }

    @Test
    @DisplayName("Test get Model")
    void getModel() {
        assertEquals("V6", engineDto.getModel());
    }

    @Test
    @DisplayName("Test get Level")
    void getLevel() {
        assertEquals(new LevelDto(1L, "Básico"), engineDto.getLevelDto());
    }

    @Test
    @DisplayName("Test change of ID")
    void setId() {
        engineDto.setId(12L);
        assertEquals(12L, engineDto.getId());
    }

    @Test
    @DisplayName("Test change of Bhp")
    void setBhp() {
        engineDto.setBhp(300);
        assertEquals(300, engineDto.getBhp());
    }

    @Test
    @DisplayName("Test not null and Max 3000 of Bhp")
    void testValidBhpInEngine() {
        engineDto.setBhp(null);
        assertThrows(ConstraintViolationException.class, () -> validateEngineDto(engineDto));
        engineDto.setBhp(3001);
        assertThrows(ConstraintViolationException.class, () -> validateEngineDto(engineDto));
    }

    @Test
    @DisplayName("Test change of Liters")
    void setLiters() {
        engineDto.setLiters(5.6);
        assertEquals(5.6, engineDto.getLiters(), 0.01);
    }

    @Test
    @DisplayName("Test not null and Max 20 of Liters")
    void testValidLitersInEngine() {
        engineDto.setLiters(null);
        assertThrows(ConstraintViolationException.class, () -> validateEngineDto(engineDto));
        engineDto.setLiters(21.0);
        assertThrows(ConstraintViolationException.class, () -> validateEngineDto(engineDto));
    }

    @Test
    @DisplayName("Test change of Model")
    void setModel() {
        engineDto.setModel("V10");
        assertEquals("V10", engineDto.getModel());
    }

    @Test
    @DisplayName("Test not null, not empty and size 20 of Model")
    void testValidModelInEngine() {
        engineDto.setModel("");
        assertThrows(ConstraintViolationException.class, () -> validateEngineDto(engineDto));
        engineDto.setModel(null);
        assertThrows(ConstraintViolationException.class, () -> validateEngineDto(engineDto));
        engineDto.setModel("V101VVF564V6F5V4F56V4F56V4F564V56FV46F54VF65V45F6");
        assertThrows(ConstraintViolationException.class, () -> validateEngineDto(engineDto));
    }

    @Test
    @DisplayName("Test change of level")
    void setLevel() {
        engineDto.setLevelDto(new LevelDto(2L, "Deportivo"));
        assertEquals(new LevelDto(2L, "Deportivo"), engineDto.getLevelDto());
    }

    @Test
    @DisplayName("Test not null Level")
    void testValidLevelInEngine() {
        assertNotNull(engineDto.getLevelDto());
        engineDto.setLevelDto(null);
        assertThrows(ConstraintViolationException.class, () -> validateEngineDto(engineDto));
    }

    @Test
    @DisplayName("Test equals")
    void testEquals() {
        EngineDto engineDto2 = new EngineDto(5L, 450, 5.3, "V6", new LevelDto(1L, "Básico"));
        assertEquals(engineDto, engineDto2);
    }

    @Test
    @DisplayName("Test count fields and methods")
    void testCountFieldsAndMethods() {
        assertEquals(5, EngineDto.class.getDeclaredFields().length);
        assertEquals(14, EngineDto.class.getDeclaredMethods().length);
    }

    private void validateEngineDto(EngineDto engineDto) {
        Set<ConstraintViolation<EngineDto>> violations = validator.validate(engineDto);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}