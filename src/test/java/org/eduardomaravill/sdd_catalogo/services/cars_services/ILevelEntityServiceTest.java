package org.eduardomaravill.sdd_catalogo.services.cars_services;

import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.LevelDto;
import org.eduardomaravill.sdd_catalogo.models.cars_models.LevelEntity;
import org.eduardomaravill.sdd_catalogo.repositories.cars_repositories.ILevelRepository;
import org.eduardomaravill.sdd_catalogo.services.MapperService;
import org.eduardomaravill.sdd_catalogo.services.cars_services.implementation.LevelServiceImpl;
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
class ILevelEntityServiceTest {

    @Mock
    private ILevelRepository levelRepository;

    @Mock
    private MapperService mapperService;

    @InjectMocks
    private LevelServiceImpl levelService;

    private LevelEntity levelEntity;
    private LevelDto levelDto;

    @BeforeEach
    void setUp() {
        levelEntity = new LevelEntity(1L, "Deportivo");
        levelDto = new LevelDto(1L, "Deportivo");
    }

    @Test
    @DisplayName("Test service get Level")
    void getLevel() {
        when(levelRepository.findById(anyLong())).thenReturn(Optional.of(levelEntity));
        when(mapperService.convertToDto(any(LevelEntity.class))).thenReturn(levelDto);

        LevelDto result = levelService.getLevel(1L);

        assertNotNull(result);
        verify(levelRepository).findById(1L);
        verify(mapperService).convertToDto(levelEntity);
    }

    @Test
    @DisplayName("Test service create Level")
    void createLevel() {
        when(levelRepository.existsByName(anyString())).thenReturn(false);
        when(mapperService.convertToEntity(any(LevelDto.class))).thenReturn(levelEntity);
        when(levelRepository.save(any(LevelEntity.class))).thenReturn(levelEntity);

        levelService.createLevel(levelDto);

        verify(levelRepository).existsByName("Deportivo");
        verify(levelRepository).save(levelEntity);
        verify(mapperService).convertToEntity(levelDto);
    }

    @Test
    @DisplayName("Test service update Level")
    void updateLevel() {
        when(levelRepository.existsById(anyLong())).thenReturn(true);
        when(levelRepository.save(any(LevelEntity.class))).thenReturn(levelEntity);
        when(mapperService.convertToEntity(any(LevelDto.class))).thenReturn(levelEntity);

        levelService.updateLevel(1L, levelDto);

        verify(levelRepository).existsById(1L);
        verify(levelRepository).save(levelEntity);
        verify(mapperService).convertToEntity(levelDto);
    }

    @Test
    @DisplayName("Test service delete Level")
    void deleteLevel() {
        when(levelRepository.existsById(anyLong())).thenReturn(true);

        levelService.deleteLevel(1L);

        verify(levelRepository).existsById(1L);
        verify(levelRepository).deleteById(1L);
    }

    @Test
    @DisplayName("Test service getAllLevels")
    void getAllLevels() {
        when(levelRepository.findAll()).thenReturn(List.of(levelEntity));
        when(mapperService.convertToDto(any(LevelEntity.class))).thenReturn(levelDto);

        List<LevelDto> result = levelService.getAllLevels();

        assertNotNull(result);
        verify(levelRepository).findAll();
        verify(mapperService).convertToDto(levelEntity);
    }
}