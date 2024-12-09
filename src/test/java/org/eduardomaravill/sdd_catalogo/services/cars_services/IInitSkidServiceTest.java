package org.eduardomaravill.sdd_catalogo.services.cars_services;

import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.InitSkidDto;
import org.eduardomaravill.sdd_catalogo.models.cars_models.InitSkid;
import org.eduardomaravill.sdd_catalogo.repositories.cars_repositories.IInitSkidRepository;
import org.eduardomaravill.sdd_catalogo.services.MapperService;
import org.eduardomaravill.sdd_catalogo.services.cars_services.implementation.InitSkidServiceImpl;
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
class IInitSkidServiceTest {

    @Mock
    private IInitSkidRepository initSkidRepository;

    @Mock
    private MapperService mapperService;

    @InjectMocks
    private InitSkidServiceImpl initSkidService;

    private InitSkid initSkid;
    private InitSkidDto initSkidDto;

    @BeforeEach
    void setUp() {
        initSkid = new InitSkid(1L, "NO");
        initSkidDto = new InitSkidDto(1L, "NO");
    }

    @Test
    @DisplayName("Test service get InitSkid")
    void getInitSkid() {
        when(initSkidRepository.findById(anyLong())).thenReturn(Optional.of(initSkid));
        when(mapperService.convertToDto(any(InitSkid.class))).thenReturn(initSkidDto);

        InitSkidDto result = initSkidService.getInitSkid(1L);

        assertNotNull(result);
        verify(initSkidRepository).findById(1L);
        verify(mapperService).convertToDto(initSkid);
    }

    @Test
    @DisplayName("Test service create InitSkid")
    void createInitSkid() {
        when(initSkidRepository.existsByName(anyString())).thenReturn(false);
        when(mapperService.convertToEntity(any(InitSkidDto.class))).thenReturn(initSkid);
        when(initSkidRepository.save(any(InitSkid.class))).thenReturn(initSkid);

        initSkidService.createInitSkid(initSkidDto);

        verify(initSkidRepository).existsByName(anyString());
        verify(mapperService).convertToEntity(initSkidDto);
        verify(initSkidRepository).save(initSkid);
    }

    @Test
    @DisplayName("Test service update InitSkid")
    void updateInitSkid() {
        when(initSkidRepository.existsById(anyLong())).thenReturn(true);
        when(mapperService.convertToEntity(any(InitSkidDto.class))).thenReturn(initSkid);
        when(initSkidRepository.save(any(InitSkid.class))).thenReturn(initSkid);

        initSkidService.updateInitSkid(1L, initSkidDto);

        verify(initSkidRepository).existsById(1L);
        verify(mapperService).convertToEntity(initSkidDto);
        verify(initSkidRepository).save(initSkid);
    }

    @Test
    @DisplayName("Test service delete InitSkid")
    void deleteInitSkid() {
        when(initSkidRepository.existsById(anyLong())).thenReturn(true);
        initSkidService.deleteInitSkid(1L);

        verify(initSkidRepository).existsById(1L);
        verify(initSkidRepository).deleteById(1L);
    }

    @Test
    @DisplayName("Test service getAllInitSkids")
    void getAllInitSkids() {
        when(initSkidRepository.findAll()).thenReturn(List.of(initSkid));
        when(mapperService.convertToDto(any(InitSkid.class))).thenReturn(initSkidDto);

        List<InitSkidDto> result = initSkidService.getAllInitSkids();

        assertNotNull(result);
        verify(initSkidRepository).findAll();
        verify(mapperService).convertToDto(any(InitSkid.class));
    }
}