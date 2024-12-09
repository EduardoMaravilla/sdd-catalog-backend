package org.eduardomaravill.sdd_catalogo.dtos.car_dtos;

import jakarta.validation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class InitSkidDtoTest {
    private InitSkidDto initSkidDto;
    private Validator validator;

    @BeforeEach
    void setUp() {
        initSkidDto = new InitSkidDto(3L,"NO");
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()){
            validator = factory.getValidator();
        }
    }

    @Test
    @DisplayName("Test get ID")
    void getId() {
        assertEquals(3L, initSkidDto.getId());
    }

    @Test
    @DisplayName("Test get Skid Type")
    void getSkidType() {
        assertEquals("NO", initSkidDto.getSkidType());
    }

    @Test
    @DisplayName("Test change of ID")
    void setId() {
        initSkidDto.setId(4L);
        assertEquals(4L, initSkidDto.getId());
    }

    @Test
    @DisplayName("Test change of Skid Type")
    void setSkidType() {
        initSkidDto.setSkidType("POR DEFECTO");
        assertEquals("POR DEFECTO", initSkidDto.getSkidType());
    }

    @Test
    @DisplayName("Test not null, not empty and size max 20 of Skid Type")
    void testValidSkidTypeInInitSkid(){
        initSkidDto.setSkidType("");
        assertThrows(ConstraintViolationException.class,()-> validateInitSkidDto(initSkidDto));
        initSkidDto.setSkidType(null);
        assertThrows(ConstraintViolationException.class,()-> validateInitSkidDto(initSkidDto));
        initSkidDto.setSkidType("A".repeat(21));
        assertThrows(ConstraintViolationException.class,()-> validateInitSkidDto(initSkidDto));
    }

    @Test
    @DisplayName("Test equals")
    void testEquals() {
        InitSkidDto initSkidDto2 = new InitSkidDto(3L,"NO");
        assertEquals(initSkidDto, initSkidDto2);
    }

    @Test
    @DisplayName("Test count fields and Methods")
    void testCountFieldsAndMethods() {
        assertEquals(2, InitSkidDto.class.getDeclaredFields().length);
        assertEquals(8, InitSkidDto.class.getDeclaredMethods().length);
    }

    private void validateInitSkidDto(InitSkidDto initSkidDto){
        Set<ConstraintViolation<InitSkidDto>> violations = validator.validate(initSkidDto);
        if (!violations.isEmpty()){
            throw new ConstraintViolationException(violations);
        }
    }
}