package org.eduardomaravill.sdd_catalogo.dtos.car_dtos;

import jakarta.validation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CarDtoTest {
    private CarDto carDto;
    private Validator validator;

    @BeforeEach
    void setUp() {
        MakerDto makerDto = new MakerDto(23L, "Porsche");
        carDto = new CarDto(25L, makerDto, "911", "1956");
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    @Test
    @DisplayName("Test get ID")
    void getId() {
        assertEquals(25L, carDto.getId());
    }

    @Test
    @DisplayName("Test get MakerDto")
    void getMakerDto() {
        assertEquals("Porsche", carDto.getMakerDto().getName());
    }

    @Test
    @DisplayName("Test get Model")
    void getModel() {
        assertEquals("911", carDto.getModel());
    }

    @Test
    @DisplayName("Test get Year")
    void getYear() {
        assertEquals("1956", carDto.getYear());
    }

    @Test
    @DisplayName("Test change of ID")
    void setId() {
        carDto.setId(27L);
        assertEquals(27L, carDto.getId());
    }

    @Test
    @DisplayName("Test change of makerDto")
    void setMakerDto() {
        MakerDto makerDto = new MakerDto(24L, "BMW");
        carDto.setMakerDto(makerDto);
        assertEquals("BMW", carDto.getMakerDto().getName());
    }

    @Test
    @DisplayName("Test not null of makerDto")
    void testValidMakerDtoInCarDto() {
        carDto.setMakerDto(null);
        assertThrows(ConstraintViolationException.class, () -> validatedCarDto(carDto));
    }

    @Test
    @DisplayName("Test change of model")
    void setModel() {
        carDto.setModel("911 GT3");
        assertEquals("911 GT3", carDto.getModel());
    }

    @Test
    @DisplayName("Test not null, not empty and size max 50 of model")
    void testValidModelInCarDto() {
        carDto.setModel("");
        assertThrows(ConstraintViolationException.class, () -> validatedCarDto(carDto));
        carDto.setModel(null);
        assertThrows(ConstraintViolationException.class, () -> validatedCarDto(carDto));
        carDto.setModel("BMWBMWBMWBMWBMWBMWBMWBMWBMWBMWBMWBMWBMWBMWBMWBMWBMWBMWBMWBMWBMW");
        assertThrows(ConstraintViolationException.class, () -> validatedCarDto(carDto));
    }

    @Test
    @DisplayName("Test change of year")
    void setYear() {
        carDto.setYear("1957");
        assertEquals("1957", carDto.getYear());
    }

    @Test
    @DisplayName("Test not null, not empty and size max 4 of year")
    void testValidYearInCarDto() {
        carDto.setYear("");
        assertThrows(ConstraintViolationException.class, () -> validatedCarDto(carDto));
        carDto.setYear(null);
        assertThrows(ConstraintViolationException.class, () -> validatedCarDto(carDto));
        carDto.setYear("19561956");
        assertThrows(ConstraintViolationException.class, () -> validatedCarDto(carDto));
    }

    @Test
    @DisplayName("Test equals")
    void testEquals() {
        CarDto carDto2 = new CarDto(25L, new MakerDto(23L, "Porsche"), "911", "1956");
        assertEquals(carDto, carDto2);
    }

    @Test
    @DisplayName("Test count fields and methods")
    void testNumbersOfMethodsAndFields() {
        assertEquals(4, CarDto.class.getDeclaredFields().length);
        assertEquals(12, CarDto.class.getDeclaredMethods().length);
    }

    private void validatedCarDto(CarDto carDto) {
        Set<ConstraintViolation<CarDto>> violations = validator.validate(carDto);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}