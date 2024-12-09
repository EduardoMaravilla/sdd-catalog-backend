package org.eduardomaravill.sdd_catalogo.dtos.car_dtos;

import jakarta.validation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class StreetTypeDtoTest {

    private StreetTypeDto streetTypeDto;
    private Validator validator;

    @BeforeEach
    void setUp() {
        streetTypeDto = new StreetTypeDto(5L,"Asfalto");
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()){
            validator = factory.getValidator();
        }
    }

    @Test
    @DisplayName("Test get ID")
    void getId() {
        assertEquals(5L, streetTypeDto.getId());
    }

    @Test
    @DisplayName("Test get Street Type")
    void getStreetType() {
        assertEquals("Asfalto", streetTypeDto.getStreetTypeVal());
    }

    @Test
    @DisplayName("Test change of ID")
    void setId() {
        streetTypeDto.setId(15L);
        assertEquals(15L, streetTypeDto.getId());
    }

    @Test
    @DisplayName("Test change of StreetType")
    void setStreetType() {
        streetTypeDto.setStreetTypeVal("Agarre");
        assertEquals("Agarre", streetTypeDto.getStreetTypeVal());
    }

    @Test
    @DisplayName("Test not null, not empty and size max 20 of Street Type")
    void testValidStreetTypeInStreetType(){
        streetTypeDto.setStreetTypeVal("");
        assertThrows(ConstraintViolationException.class, () -> validateStreetTypeDto(streetTypeDto));
        streetTypeDto.setStreetTypeVal(null);
        assertThrows(ConstraintViolationException.class, () -> validateStreetTypeDto(streetTypeDto));
        streetTypeDto.setStreetTypeVal("A".repeat(21));
        assertThrows(ConstraintViolationException.class, () -> validateStreetTypeDto(streetTypeDto));
    }

    @Test
    @DisplayName("Test equals")
    void testEquals() {
        StreetTypeDto streetTypeDto2 = new StreetTypeDto(5L,"Asfalto");
        assertEquals(streetTypeDto, streetTypeDto2);
    }

    @Test
    @DisplayName("Test count fields and methods")
    void testCountFieldsAndMethods(){
        assertEquals(2, StreetTypeDto.class.getDeclaredFields().length);
        assertEquals(8, StreetTypeDto.class.getDeclaredMethods().length);
    }

    private void validateStreetTypeDto(StreetTypeDto streetTypeDto){
        Set<ConstraintViolation<StreetTypeDto>> violations = validator.validate(streetTypeDto);
        if (!violations.isEmpty()){
            throw new ConstraintViolationException(violations);
        }
    }
}