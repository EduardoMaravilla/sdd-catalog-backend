package org.eduardomaravill.sdd_catalogo.dtos.car_dtos;

import jakarta.validation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class MakerDtoTest {
    private MakerDto makerDto;
    private Validator validator;

    @BeforeEach
    void setUp() {
        makerDto = new MakerDto(5L,"Chevrolet");
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()){
            validator = factory.getValidator();
        }
    }

    @Test
    @DisplayName("Test get ID")
    void getId() {
        assertEquals(5L, makerDto.getId());
    }

    @Test
    @DisplayName("Test get Maker")
    void getLevel() {
        assertEquals("Chevrolet", makerDto.getName());
    }

    @Test
    @DisplayName("Test change of ID")
    void setId() {
        makerDto.setId(10L);
        assertEquals(10L, makerDto.getId());
    }

    @Test
    @DisplayName("Test change of Maker")
    void setLevel() {
        makerDto.setName("SRT");
        assertEquals("SRT", makerDto.getName());
    }

    @Test
    @DisplayName("Test not null,not empty and size max 20 of Maker")
    void testNumbersOfMethodsAndFields(){
        makerDto.setName("");
        assertThrows(ConstraintViolationException.class, () -> validateMakerDto(makerDto));
        makerDto.setName(null);
        assertThrows(ConstraintViolationException.class, () -> validateMakerDto(makerDto));
        makerDto.setName("D".repeat(21));
        assertThrows(ConstraintViolationException.class, () -> validateMakerDto(makerDto));
    }

    @Test
    @DisplayName("Test equals")
    void testEquals() {
        MakerDto makerDto2 = new MakerDto(5L, "Chevrolet");
        assertEquals(makerDto, makerDto2);
    }

    @Test
    @DisplayName("Test count fields and Methods")
    void testNumberOfMethodsAndFields() {
        assertEquals(2, MakerDto.class.getDeclaredFields().length);
        assertEquals(8, MakerDto.class.getDeclaredMethods().length);
    }

    private void validateMakerDto(MakerDto makerDto){
        Set<ConstraintViolation<MakerDto>> violations = validator.validate(makerDto);
        if (!violations.isEmpty()){
            throw new ConstraintViolationException(violations);
        }
    }
}