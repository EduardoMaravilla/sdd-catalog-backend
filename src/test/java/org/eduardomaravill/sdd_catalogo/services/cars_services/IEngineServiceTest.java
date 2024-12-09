package org.eduardomaravill.sdd_catalogo.services.cars_services;

import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.EngineDto;
import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.LevelDto;
import org.eduardomaravill.sdd_catalogo.models.cars_models.Engine;
import org.eduardomaravill.sdd_catalogo.models.cars_models.LevelEntity;
import org.eduardomaravill.sdd_catalogo.repositories.cars_repositories.IEngineRepository;
import org.eduardomaravill.sdd_catalogo.services.MapperService;
import org.eduardomaravill.sdd_catalogo.services.cars_services.implementation.EngineServiceImpl;
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
class IEngineServiceTest {

    @Mock
    private IEngineRepository engineRepository;

    @Mock
    private ILevelService levelService;

    @Mock
    private MapperService mapperService;

    @InjectMocks
    private EngineServiceImpl engineService;

    private Engine engine;
    private EngineDto engineDto;

    @BeforeEach
    void setUp() {
        engine = new Engine(6L,450,5.5,"V10",new LevelEntity(5L,"Deportivo"));
        engineDto = new EngineDto(6L,450,5.5,"V10",new LevelDto(5L,"Deportivo"));
    }

    @Test
    @DisplayName("Test service get Engine")
    void getEngine() {
        when(engineRepository.findByIdSpecific(anyLong())).thenReturn(Optional.of(engine));
        when(mapperService.convertToDto(any(Engine.class))).thenReturn(engineDto);

        EngineDto result = engineService.getEngine(6L);

        assertNotNull(result);

        verify(engineRepository).findByIdSpecific(6L);
        verify(mapperService).convertToDto(engine);
    }

    @Test
    @DisplayName("Test service create Engine")
    void createEngine() {
        when(levelService.getLevel(anyLong())).thenReturn(new LevelDto(5L,"Deportivo"));
        when(engineRepository.existsEngine(anyInt(),anyDouble(),anyString(),anyLong())).thenReturn(false);
        when(mapperService.convertToEntity(any(EngineDto.class))).thenReturn(engine);
        when(engineRepository.save(any(Engine.class))).thenReturn(engine);

        engineService.createEngine(engineDto);

        verify(levelService).getLevel(5L);
        verify(engineRepository).existsEngine(450,5.5,"V10",5L);
        verify(mapperService).convertToEntity(engineDto);
        verify(engineRepository).save(engine);
    }

    @Test
    @DisplayName("Test service update Engine")
    void updateEngine() {
        when(levelService.getLevel(anyLong())).thenReturn(new LevelDto(5L,"Deportivo"));
        when(engineRepository.existsById(anyLong())).thenReturn(true);
        when(mapperService.convertToEntity(any(EngineDto.class))).thenReturn(engine);
        when(engineRepository.save(any(Engine.class))).thenReturn(engine);

        engineService.updateEngine(6L, engineDto);

        verify(levelService).getLevel(5L);
        verify(engineRepository).existsById(6L);
        verify(mapperService).convertToEntity(engineDto);
        verify(engineRepository).save(engine);
    }

    @Test
    @DisplayName("Test service delete Engine")
    void deleteEngine() {
        when(engineRepository.existsById(anyLong())).thenReturn(true);

        engineService.deleteEngine(6L);

        verify(engineRepository).existsById(6L);
        verify(engineRepository).deleteById(6L);
    }

    @Test
    @DisplayName("Test service getAll Engines")
    void testGetAllEngines() {
        when(engineRepository.findAll()).thenReturn(List.of(engine,engine,engine));
        when(mapperService.convertToDto(any(Engine.class))).thenReturn(engineDto);

        List<EngineDto> result = engineService.getAllEngines();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(engineRepository).findAll();
        verify(mapperService, times(3)).convertToDto(engine);
    }
}