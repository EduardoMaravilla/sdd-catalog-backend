package org.eduardomaravill.sdd_catalogo.services.cars_services;

import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.StreetTypeDto;
import org.eduardomaravill.sdd_catalogo.models.cars_models.StreetType;
import org.eduardomaravill.sdd_catalogo.repositories.cars_repositories.IStreetTypeRepository;
import org.eduardomaravill.sdd_catalogo.services.MapperService;
import org.eduardomaravill.sdd_catalogo.services.cars_services.implementation.StreetTypeServiceImpl;
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
class IStreetTypeServiceTest {

    @Mock
    private IStreetTypeRepository streetTypeRepository;

    @Mock
    private MapperService mapperService;

    @InjectMocks
    private StreetTypeServiceImpl streetTypeService;

    private StreetType streetType;
    private StreetTypeDto streetTypeDto;

    @BeforeEach
    void setUp() {
        streetType = new StreetType(4L,"Derrape");
        streetTypeDto = new StreetTypeDto(4L,"Derrape");
    }

    @Test
    @DisplayName("Test service get Street Type")
    void getStreetType() {
        when(streetTypeRepository.findById(anyLong())).thenReturn(Optional.of(streetType));
        when(mapperService.convertToDto(any(StreetType.class))).thenReturn(streetTypeDto);

        StreetTypeDto result = streetTypeService.getStreetType(4L);

        assertNotNull(result);
        verify(streetTypeRepository).findById(4L);
        verify(mapperService).convertToDto(streetType);
    }

    @Test
    @DisplayName("Test service create Street Type")
    void createStreetType() {
        when(streetTypeRepository.existsByName(anyString())).thenReturn(false);
        when(streetTypeRepository.save(any(StreetType.class))).thenReturn(streetType);
        when(mapperService.convertToEntity(any(StreetTypeDto.class))).thenReturn(streetType);

        streetTypeService.createStreetType(streetTypeDto);

        verify(streetTypeRepository).existsByName(streetTypeDto.getStreetTypeVal());
        verify(streetTypeRepository).save(streetType);
        verify(mapperService).convertToEntity(streetTypeDto);
    }

    @Test
    @DisplayName("Test service update Street Type")
    void updateStreetType() {
        when(streetTypeRepository.existsById(anyLong())).thenReturn(true);
        when(streetTypeRepository.save(any(StreetType.class))).thenReturn(streetType);
        when(mapperService.convertToEntity(any(StreetTypeDto.class))).thenReturn(streetType);

        streetTypeService.updateStreetType(4L, streetTypeDto);

        verify(streetTypeRepository).existsById(4L);
        verify(streetTypeRepository).save(streetType);
        verify(mapperService).convertToEntity(streetTypeDto);
    }

    @Test
    @DisplayName("Test service delete Street Type")
    void deleteStreetType() {
        when(streetTypeRepository.existsById(anyLong())).thenReturn(true);
        streetTypeService.deleteStreetType(4L);

        verify(streetTypeRepository).existsById(4L);
        verify(streetTypeRepository).deleteById(4L);
    }

    @Test
    @DisplayName("Test service getAllStreetTypes")
    void getAllStreetTypes() {
        when(streetTypeRepository.findAll()).thenReturn(List.of(streetType));
        when(mapperService.convertToDto(any(StreetType.class))).thenReturn(streetTypeDto);

        List<StreetTypeDto> result = streetTypeService.getAllStreetTypes();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(streetTypeRepository).findAll();
        verify(mapperService).convertToDto(streetType);
    }
}