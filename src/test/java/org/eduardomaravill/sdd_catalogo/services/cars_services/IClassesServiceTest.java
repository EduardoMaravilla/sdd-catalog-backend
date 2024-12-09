package org.eduardomaravill.sdd_catalogo.services.cars_services;

import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.ClassesDto;
import org.eduardomaravill.sdd_catalogo.models.cars_models.Classes;
import org.eduardomaravill.sdd_catalogo.repositories.cars_repositories.IClassesRepository;
import org.eduardomaravill.sdd_catalogo.services.MapperService;
import org.eduardomaravill.sdd_catalogo.services.cars_services.implementation.ClassesServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IClassesServiceTest {

    @Mock
    private IClassesRepository classesRepository;

    @Mock
    private MapperService mapperService;

    @InjectMocks
    private ClassesServiceImpl classesService;

    private Classes classes;
    private ClassesDto classesDto;

    @BeforeEach
    void setUp() {
        classes = new Classes(1L, "A");
        classesDto = new ClassesDto(1L, "A");
    }

    @Test
    @DisplayName("Test service get Classes")
    void testGetClasses() {
        when(classesRepository.findById(anyLong())).thenReturn(Optional.of(classes));
        when(mapperService.convertToDto(any(Classes.class))).thenReturn(classesDto);

        ClassesDto result = classesService.getClasses(1L);

        assertNotNull(result);
        verify(classesRepository).findById(1L);
        verify(mapperService).convertToDto(classes);
    }

    @Test
    @DisplayName("Test service create Classes")
    void createClass() {
        when(classesRepository.existsByName(classes.getName())).thenReturn(false);
        when(classesRepository.save(any(Classes.class))).thenReturn(classes);
        when(mapperService.convertToEntity(any(ClassesDto.class))).thenReturn(classes);
        classesService.createClass(classesDto);

        verify(classesRepository).existsByName(classesDto.getName());
        verify(classesRepository).save(any(Classes.class));
        verify(mapperService).convertToEntity(classesDto);
    }

    @Test
    @DisplayName("Test service update classes")
    void updateClass() {
        when(classesRepository.existsById(anyLong())).thenReturn(true);
        when(classesRepository.save(any(Classes.class))).thenReturn(classes);
        when(mapperService.convertToEntity(any(ClassesDto.class))).thenReturn(classes);

        classesService.updateClass(1L, classesDto);

        verify(classesRepository).existsById(1L);
        verify(classesRepository).save(any(Classes.class));
        verify(mapperService).convertToEntity(classesDto);
    }

    @Test
    @DisplayName("Test service delete Classes")
    void deleteClass() {
        when(classesRepository.existsById(anyLong())).thenReturn(true);
        classesService.deleteClass(1L);

        verify(classesRepository).existsById(1L);
        verify(classesRepository).deleteById(1L);
    }

    @Test
    @DisplayName("Test service getAllClasses")
    void getAllClasses() {
        when(classesRepository.findAll()).thenReturn(List.of(classes));
        when(mapperService.convertToDto(any(Classes.class))).thenReturn(classesDto);

        List<ClassesDto> result = classesService.getAllClasses();

        assertNotNull(result);
        verify(classesRepository).findAll();
        verify(mapperService).convertToDto(classes);
    }
}