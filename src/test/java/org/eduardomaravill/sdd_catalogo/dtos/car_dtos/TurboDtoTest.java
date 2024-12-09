package org.eduardomaravill.sdd_catalogo.dtos.car_dtos;

import jakarta.validation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TurboDtoTest {
    private TurboDto turboDto;
    private Validator validator;

    @BeforeEach
    void setUp() {
        turboDto = new TurboDto(5L, new TurboTypeDto(5L, "TurboCompresor"), new LevelDto(5L, "Elite"));
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    @Test
    @DisplayName("Test get ID")
    void getId() {
        assertEquals(5L, turboDto.getId());
    }

    @Test
    @DisplayName("Test get Turbo Type")
    void getTurboType() {
        assertEquals("TurboCompresor", turboDto.getTurboTypeDto().getType());
    }

    @Test
    @DisplayName("Test get Level")
    void getLevel() {
        assertEquals("Elite", turboDto.getLevelDto().getLevel());
    }

    @Test
    @DisplayName("Test change of ID")
    void setId() {
        turboDto.setId(10L);
        assertEquals(10L, turboDto.getId());
    }

    @Test
    @DisplayName("Test change of Turbo Type")
    void setTurboType() {
        turboDto.setTurboTypeDto(new TurboTypeDto(10L, "TurboEco"));
        assertEquals("TurboEco", turboDto.getTurboTypeDto().getType());
    }

    @Test
    @DisplayName("Test not null of Turbo Type")
    void testValidTurboTypeInTurbo() {
        turboDto.setTurboTypeDto(null);
        assertThrows(ConstraintViolationException.class, () -> validateTurboDto(turboDto));
    }

    @Test
    @DisplayName("Test change of Level")
    void setLevel() {
        turboDto.setLevelDto(new LevelDto(10L, "Premium"));
        assertEquals(new LevelDto(10L, "Premium"), turboDto.getLevelDto());
    }

    @Test
    @DisplayName("Test not null of Level")
    void testValidLevelInTurbo() {
        turboDto.setLevelDto(null);
        assertThrows(ConstraintViolationException.class, () -> validateTurboDto(turboDto));
    }

    @Test
    @DisplayName("Test equals")
    void testEquals() {
        TurboDto turboDto1 = new TurboDto(5L, new TurboTypeDto(5L, "TurboCompresor"), new LevelDto(5L, "Elite"));
        assertEquals(turboDto, turboDto1);
    }

    @Test
    @DisplayName("Test count fields and methods")
    void testFieldsAndMethods() {
        assertEquals(3, TurboDto.class.getDeclaredFields().length);
        assertEquals(10, TurboDto.class.getDeclaredMethods().length);
    }

    private void validateTurboDto(TurboDto turboDto) {
        Set<ConstraintViolation<TurboDto>> violations = validator.validate(turboDto);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}