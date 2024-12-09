package org.eduardomaravill.sdd_catalogo.services.cars_services;

import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.LevelDto;
import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.StreetTypeDto;
import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.SuspensionDto;
import org.eduardomaravill.sdd_catalogo.models.cars_models.LevelEntity;
import org.eduardomaravill.sdd_catalogo.models.cars_models.StreetType;
import org.eduardomaravill.sdd_catalogo.models.cars_models.Suspension;
import org.eduardomaravill.sdd_catalogo.repositories.cars_repositories.ISuspensionRepository;
import org.eduardomaravill.sdd_catalogo.services.MapperService;
import org.eduardomaravill.sdd_catalogo.services.cars_services.implementation.SuspensionServiceImpl;
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
class ISuspensionServiceTest {

    @Mock
    private ISuspensionRepository suspensionRepository;

    @Mock
    private IStreetTypeService streetTypeService;

    @Mock
    private ILevelService levelService;

    @Mock
    private MapperService mapperService;

    @InjectMocks
    private SuspensionServiceImpl suspensionService;

    private Suspension suspension;
    private SuspensionDto suspensionDto;

    @BeforeEach
    void setUp() {
        suspension = new Suspension(5L,new StreetType(2L,"Asfalto"),new LevelEntity(5L,"Elite"));
        suspensionDto =  new SuspensionDto(5L, new StreetTypeDto(2L,"Asfalto"),new LevelDto(5L,"Elite"));
    }

    @Test
    @DisplayName("Test service get Suspension")
    void getSuspension() {
        when(suspensionRepository.findByIdSpecific(anyLong())).thenReturn(Optional.of(suspension));
        when(mapperService.convertToDto(any(Suspension.class))).thenReturn(suspensionDto);

        SuspensionDto result = suspensionService.getSuspension(3L);
        assertNotNull(result);

        verify(suspensionRepository).findByIdSpecific(3L);
        verify(mapperService).convertToDto(suspension);
    }

    @Test
    @DisplayName("Test service create Suspension")
    void createSuspension() {
        when(levelService.getLevel(anyLong())).thenReturn(new LevelDto(5L,"Elite"));
        when(streetTypeService.getStreetType(anyLong())).thenReturn(new StreetTypeDto(2L,"Asfalto"));
        when(suspensionRepository.existsSuspension(anyLong(),anyLong())).thenReturn(false);
        when(mapperService.convertToEntity(any(SuspensionDto.class))).thenReturn(suspension);

        suspensionService.createSuspension(suspensionDto);

        verify(levelService).getLevel(5L);
        verify(streetTypeService).getStreetType(2L);
        verify(suspensionRepository).existsSuspension(2L,5L);
        verify(mapperService).convertToEntity(suspensionDto);
    }

    @Test
    @DisplayName("Test service update Suspension")
    void updateSuspension() {
        when(levelService.getLevel(anyLong())).thenReturn(new LevelDto(5L,"Elite"));
        when(streetTypeService.getStreetType(anyLong())).thenReturn(new StreetTypeDto(2L,"Asfalto"));
        when(suspensionRepository.existsById(anyLong())).thenReturn(true);
        when(mapperService.convertToEntity(any(SuspensionDto.class))).thenReturn(suspension);

        suspensionService.updateSuspension(3L, suspensionDto);

        verify(levelService).getLevel(5L);
        verify(streetTypeService).getStreetType(2L);
        verify(suspensionRepository).existsById(3L);
        verify(mapperService).convertToEntity(suspensionDto);
    }

    @Test
    @DisplayName("Test service delete Suspension")
    void deleteSuspension() {
        when(suspensionRepository.existsById(anyLong())).thenReturn(true);

        suspensionService.deleteSuspension(3L);

        verify(suspensionRepository).existsById(3L);
        verify(suspensionRepository).deleteById(3L);
    }

    @Test
    @DisplayName("Test service getAllSuspensions")
    void getAllSuspensions() {
        when(suspensionRepository.findAll()).thenReturn(List.of(suspension));
        when(mapperService.convertToDto(any(Suspension.class))).thenReturn(suspensionDto);

        List<SuspensionDto> result = suspensionService.getAllSuspensions();
        assertNotNull(result);

        verify(suspensionRepository).findAll();
        verify(mapperService).convertToDto(suspension);
    }
}