package org.eduardomaravill.sdd_catalogo.services.cars_services;

import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.TurboTypeDto;
import org.eduardomaravill.sdd_catalogo.models.cars_models.TurboType;
import org.eduardomaravill.sdd_catalogo.repositories.cars_repositories.ITurboTypeRepository;
import org.eduardomaravill.sdd_catalogo.services.MapperService;
import org.eduardomaravill.sdd_catalogo.services.cars_services.implementation.TurboTypeServiceImpl;
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
class ITurboTypeServiceTest {

    @Mock
    private ITurboTypeRepository turboTypeRepository;

    @Mock
    private MapperService mapperService;

    @InjectMocks
    private TurboTypeServiceImpl turboTypeService;

    private TurboType turboType;
    private TurboTypeDto turboTypeDto;

    @BeforeEach
    void setUp() {
        turboType = new TurboType(3L,"TurboCompresor");
        turboTypeDto = new TurboTypeDto(3L,"TurboCompresor");
    }

    @Test
    @DisplayName("Test service get TurboType")
    void getTurboType() {
        when(turboTypeRepository.findById(anyLong())).thenReturn(Optional.of(turboType));
        when(mapperService.convertToDto(any(TurboType.class))).thenReturn(turboTypeDto);

        TurboTypeDto result = turboTypeService.getTurboType(3L);

        assertNotNull(result);
        verify(turboTypeRepository).findById(3L);
        verify(mapperService).convertToDto(turboType);
    }

    @Test
    @DisplayName("Test service create TurboType")
    void createTurboType() {
        when(turboTypeRepository.existsByName(turboType.getType())).thenReturn(false);
        when(mapperService.convertToEntity(any(TurboTypeDto.class))).thenReturn(turboType);

        turboTypeService.createTurboType(turboTypeDto);

        verify(turboTypeRepository).existsByName(turboTypeDto.getType());
        verify(turboTypeRepository).save(turboType);
        verify(mapperService).convertToEntity(turboTypeDto);
    }

    @Test
    @DisplayName("Test service update TurboType")
    void updateTurboType() {
        when(turboTypeRepository.existsById(anyLong())).thenReturn(true);
        when(mapperService.convertToEntity(any(TurboTypeDto.class))).thenReturn(turboType);

        turboTypeService.updateTurboType(3L, turboTypeDto);

        verify(turboTypeRepository).existsById(3L);
        verify(turboTypeRepository).save(turboType);
        verify(mapperService).convertToEntity(turboTypeDto);
    }

    @Test
    @DisplayName("Test service delete TurboType")
    void deleteTurboType() {
        when(turboTypeRepository.existsById(anyLong())).thenReturn(true);

        turboTypeService.deleteTurboType(3L);

        verify(turboTypeRepository).existsById(3L);
        verify(turboTypeRepository).deleteById(3L);
    }

    @Test
    @DisplayName("Test service getAllTurboTypes")
    void getAllTurboTypes() {
        when(turboTypeRepository.findAll()).thenReturn(List.of(turboType));
        when(mapperService.convertToDto(any(TurboType.class))).thenReturn(turboTypeDto);

        List<TurboTypeDto> result = turboTypeService.getAllTurboTypes();

        assertNotNull(result);
        verify(turboTypeRepository).findAll();
        verify(mapperService).convertToDto(turboType);
    }
}