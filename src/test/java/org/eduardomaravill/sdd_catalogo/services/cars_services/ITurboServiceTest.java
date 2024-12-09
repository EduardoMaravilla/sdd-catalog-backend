package org.eduardomaravill.sdd_catalogo.services.cars_services;

import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.LevelDto;
import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.TurboDto;
import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.TurboTypeDto;
import org.eduardomaravill.sdd_catalogo.models.cars_models.LevelEntity;
import org.eduardomaravill.sdd_catalogo.models.cars_models.Turbo;
import org.eduardomaravill.sdd_catalogo.models.cars_models.TurboType;
import org.eduardomaravill.sdd_catalogo.repositories.cars_repositories.ITurboRepository;
import org.eduardomaravill.sdd_catalogo.services.MapperService;
import org.eduardomaravill.sdd_catalogo.services.cars_services.implementation.TurboServiceImpl;
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
class ITurboServiceTest {

    @Mock
    private ITurboRepository turboRepository;

    @Mock
    private ITurboTypeService turboTypeService;

    @Mock
    private ILevelService levelService;

    @Mock
    private MapperService mapperService;

    @InjectMocks
    private TurboServiceImpl turboService;

    private Turbo turbo;
    private TurboDto turboDto;

    @BeforeEach
    void setUp() {
        turbo = new Turbo(2L,new TurboType(2L,"SobreAlimentador"),new LevelEntity(2L,"Deportivo"));
        turboDto = new TurboDto(2L,new TurboTypeDto(2L,"SobreAlimentador"),new LevelDto(2L,"Deportivo"));
    }

    @Test
    @DisplayName("Test service get Turbo")
    void getTurbo() {
        when(turboRepository.findByIdSpecific(anyLong())).thenReturn(Optional.of(turbo));
        when(mapperService.convertToDto(any(Turbo.class))).thenReturn(turboDto);

        TurboDto result = turboService.getTurbo(2L);

        assertNotNull(result);
        verify(turboRepository).findByIdSpecific(2L);
        verify(mapperService).convertToDto(turbo);
    }

    @Test
    @DisplayName("Test service create Turbo")
    void createTurbo() {
        when(levelService.getLevel(anyLong())).thenReturn(new LevelDto(2L,"Deportivo"));
        when(turboTypeService.getTurboType(anyLong())).thenReturn(new TurboTypeDto(2L,"SobreAlimentador"));
        when(turboRepository.existsTurbo(anyLong(),anyLong())).thenReturn(false);
        when(mapperService.convertToEntity(any(TurboDto.class))).thenReturn(turbo);

        turboService.createTurbo(turboDto);

        verify(levelService).getLevel(anyLong());
        verify(turboTypeService).getTurboType(anyLong());
        verify(turboRepository).existsTurbo(2L,2L);
        verify(mapperService).convertToEntity(turboDto);
    }

    @Test
    @DisplayName("Test service update Turbo")
    void updateTurbo() {
        when(levelService.getLevel(anyLong())).thenReturn(new LevelDto(2L,"Deportivo"));
        when(turboTypeService.getTurboType(anyLong())).thenReturn(new TurboTypeDto(2L,"SobreAlimentador"));
        when(turboRepository.existsById(anyLong())).thenReturn(true);
        when(mapperService.convertToEntity(any(TurboDto.class))).thenReturn(turbo);

        turboService.updateTurbo(2L, turboDto);

        verify(levelService).getLevel(anyLong());
        verify(turboTypeService).getTurboType(anyLong());
        verify(turboRepository).existsById(2L);
        verify(mapperService).convertToEntity(turboDto);
    }

    @Test
    @DisplayName("Test service delete Turbo")
    void deleteTurbo() {
        when(turboRepository.existsById(anyLong())).thenReturn(true);

        turboService.deleteTurbo(2L);

        verify(turboRepository).existsById(2L);
        verify(turboRepository).deleteById(2L);
    }

    @Test
    @DisplayName("Test service getAllTurbos")
    void getAllTurbos() {
        when(turboRepository.findAll()).thenReturn(List.of(turbo));
        when(mapperService.convertToDto(any(Turbo.class))).thenReturn(turboDto);

        List<TurboDto> result = turboService.getAllTurbos();

        assertNotNull(result);
        verify(turboRepository).findAll();
        verify(mapperService).convertToDto(turbo);
    }
}