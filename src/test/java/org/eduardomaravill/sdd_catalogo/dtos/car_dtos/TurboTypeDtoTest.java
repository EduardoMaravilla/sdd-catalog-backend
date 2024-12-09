package org.eduardomaravill.sdd_catalogo.dtos.car_dtos;

import jakarta.validation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TurboTypeDtoTest {
    private TurboTypeDto turboTypeDto;
    private Validator validator;

    @BeforeEach
    void setUp() {
        turboTypeDto = new TurboTypeDto(5L,"Turbo");
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()){
            validator = factory.getValidator();
        }
    }

    @Test
    @DisplayName("Test get ID")
    void getId() {
        assertEquals(5L, turboTypeDto.getId());
    }

    @Test
    @DisplayName("Test get Type")
    void getType() {
        assertEquals("Turbo", turboTypeDto.getType());
    }

    @Test
    @DisplayName("Test change of ID")
    void setId() {
        turboTypeDto.setId(10L);
        assertEquals(10L, turboTypeDto.getId());
    }

    @Test
    @DisplayName("Test change of Type")
    void setType() {
        turboTypeDto.setType("DobleTurboCompresor");
        assertEquals("DobleTurboCompresor", turboTypeDto.getType());
    }

    @Test
    @DisplayName("Test not null, not empty and size max 30 of Type")
    void testValidTurboType(){
        turboTypeDto.setType("");
        assertThrows(ConstraintViolationException.class, () -> validateTurboTypeDto(turboTypeDto));
        turboTypeDto.setType(null);
        assertThrows(ConstraintViolationException.class, () -> validateTurboTypeDto(turboTypeDto));
        turboTypeDto.setType("A".repeat(31));
        assertThrows(ConstraintViolationException.class, () -> validateTurboTypeDto(turboTypeDto));
    }

    @Test
    @DisplayName("Test equals")
    void testEquals() {
        TurboTypeDto turboTypeDto1 = new TurboTypeDto(5L,"Turbo");
        assertEquals(turboTypeDto, turboTypeDto1);
    }

    @Test
    @DisplayName("Test count fields and Methods")
    void testNumberOfMethodsAndFields() {
        assertEquals(2, TurboTypeDto.class.getDeclaredFields().length);
        assertEquals(8, TurboTypeDto.class.getDeclaredMethods().length);
    }

    private void validateTurboTypeDto(TurboTypeDto turboTypeDto){
        Set<ConstraintViolation<TurboTypeDto>> violations = validator.validate(turboTypeDto);
        if (!violations.isEmpty()){
            throw new ConstraintViolationException(violations);
        }
    }
}