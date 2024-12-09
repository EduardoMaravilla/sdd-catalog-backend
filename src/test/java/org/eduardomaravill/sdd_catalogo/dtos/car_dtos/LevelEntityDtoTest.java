package org.eduardomaravill.sdd_catalogo.dtos.car_dtos;

import jakarta.validation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class LevelEntityDtoTest {
    private LevelDto levelDto;
    private Validator validator;

    @BeforeEach
    void setUp() {
        levelDto = new LevelDto(5L,"Promedio");
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()){
            validator = factory.getValidator();
        }
    }

    @Test
    @DisplayName("Test get ID")
    void getId() {
        assertEquals(5L, levelDto.getId());
    }

    @Test
    @DisplayName("Test get Level")
    void getLevel() {
        assertEquals("Promedio", levelDto.getLevel());
    }

    @Test
    @DisplayName("Test change of ID")
    void setId() {
        levelDto.setId(10L);
        assertEquals(10L, levelDto.getId());
    }

    @Test
    @DisplayName("Test change of Level")
    void setLevel() {
        levelDto.setLevel("Avanzado");
        assertEquals("Avanzado", levelDto.getLevel());
    }

    @Test
    @DisplayName("Test not null,not empty and size max 10 of Level")
    void testNumbersOfMethodsAndFields(){
        levelDto.setLevel("");
        assertThrows(ConstraintViolationException.class, () -> validateLevelDto(levelDto));
        levelDto.setLevel(null);
        assertThrows(ConstraintViolationException.class, () -> validateLevelDto(levelDto));
        levelDto.setLevel("D".repeat(11));
        assertThrows(ConstraintViolationException.class, () -> validateLevelDto(levelDto));
    }

    @Test
    @DisplayName("Test equals")
    void testEquals() {
        LevelDto levelDto2 = new LevelDto(5L, "Promedio");
        assertEquals(levelDto, levelDto2);
    }

    @Test
    @DisplayName("Test count fields and Methods")
    void testNumberOfMethodsAndFields() {
        assertEquals(2, LevelDto.class.getDeclaredFields().length);
        assertEquals(8,LevelDto.class.getDeclaredMethods().length);
    }

    private void validateLevelDto(LevelDto levelDto){
        Set<ConstraintViolation<LevelDto>> violations = validator.validate(levelDto);
        if (!violations.isEmpty()){
            throw new ConstraintViolationException(violations);
        }
    }
}