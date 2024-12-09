package org.eduardomaravill.sdd_catalogo.dtos.car_dtos;

import jakarta.validation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ClassesDtoTest {
    private ClassesDto classesDto;
    private Validator validator;

    @BeforeEach
    void setup(){
        classesDto = new ClassesDto(15L, "A");
        try(ValidatorFactory factory = Validation.buildDefaultValidatorFactory()){
            validator = factory.getValidator();
        }
    }

    @Test
    @DisplayName("Test get ID")
    void getId() {
        assertEquals(15L, classesDto.getId());
    }

    @Test
    @DisplayName("Test get name")
    void getName() {
        assertEquals("A", classesDto.getName());
    }

    @Test
    @DisplayName("Test change of ID")
    void setId() {
        classesDto.setId(16L);
        assertEquals(16L, classesDto.getId());
    }

    @Test
    @DisplayName("Test change of name")
    void setName() {
        classesDto.setName("B");
        assertEquals("B", classesDto.getName());
    }

    @Test
    @DisplayName("Test not null, not empty and max size 2 of name")
    void testValidNameInClasses(){
        classesDto.setName("");
        assertThrows(ConstraintViolationException.class, () -> validateClassesDto(classesDto));
        classesDto.setName(null);
        assertThrows(ConstraintViolationException.class,() -> validateClassesDto(classesDto));
        classesDto.setName("AAA");
        assertThrows(ConstraintViolationException.class,() -> validateClassesDto(classesDto));
    }

    @Test
    @DisplayName("Test equals")
    void testEquals() {
        ClassesDto classesDto2 = new ClassesDto(15L, "A");
        assertEquals(classesDto, classesDto2);
        assertNotEquals(classesDto, new ClassesDto(18L,"B"));
    }

    @Test
    @DisplayName("Test count fields and methods")
    void testNumbersOfMethodsAndFields(){
        assertEquals(2, ClassesDto.class.getDeclaredFields().length);
        assertEquals(8, ClassesDto.class.getDeclaredMethods().length);
    }

    private void validateClassesDto(ClassesDto classesDto){
        Set<ConstraintViolation<ClassesDto>> violations = validator.validate(classesDto);
        if (!violations.isEmpty()){
            throw new ConstraintViolationException(violations);
        }
    }
}