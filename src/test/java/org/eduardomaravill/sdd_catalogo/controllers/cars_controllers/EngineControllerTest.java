package org.eduardomaravill.sdd_catalogo.controllers.cars_controllers;

import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.EngineDto;
import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.LevelDto;
import org.eduardomaravill.sdd_catalogo.repositories.users_respositories.IJwtTokenRepository;
import org.eduardomaravill.sdd_catalogo.services.auth.IJwtService;
import org.eduardomaravill.sdd_catalogo.services.cars_services.IEngineService;
import org.eduardomaravill.sdd_catalogo.services.cars_services.IStreetTypeService;
import org.eduardomaravill.sdd_catalogo.services.users_services.IUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EngineController.class)
@AutoConfigureMockMvc(addFilters = false)
class EngineControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IJwtService jwtService;

    @MockBean
    private IStreetTypeService streetTypeService;

    @MockBean
    private IJwtTokenRepository jwtTokenRepository;

    @MockBean
    private IUserService userService;

    @MockBean
    private IEngineService engineService;

    private EngineDto engineDto;

    @BeforeEach
    void setUp() {
        engineDto = new EngineDto(2L, 450, 3.5, "V6", new LevelDto(5L, "Elite"));
    }

    @Test
    @DisplayName("Test controller get Engine")
    void getEngine() throws Exception {
        when(engineService.getEngine(anyLong())).thenReturn(engineDto);
        mockMvc.perform(get("/engines/{id}", 2L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(engineDto.getId()))
                .andExpect(jsonPath("$.bhp").value(engineDto.getBhp()));

        verify(engineService, times(1)).getEngine(anyLong());
    }

    @Test
    @DisplayName("Test controller getAllEngines")
    void getAllEngines() throws Exception {
        List<EngineDto> engineDtoList = List.of(engineDto, engineDto, engineDto);
        when(engineService.getAllEngines()).thenReturn(engineDtoList);

        mockMvc.perform(get("/engines"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(engineDtoList.getFirst().getId()))
                .andExpect(jsonPath("$[0].bhp").value(engineDtoList.getFirst().getBhp()))
                .andExpect(jsonPath("$.size()").value(engineDtoList.size()));

        verify(engineService, times(1)).getAllEngines();
    }

    @Test
    @DisplayName("Test controller create Engine")
    void createEngine() throws Exception {
        doNothing().when(engineService).createEngine(any(EngineDto.class));

        mockMvc.perform(post("/engines")
               .contentType("application/json")
               .content("{\"bhp\": 450,\"liters\":3.5 ,\"model\": \"V6\", \"levelDto\": {\"id\": 5, \"level\": \"Elite\"}}"))
               .andExpect(status().isCreated());

        verify(engineService, times(1)).createEngine(any(EngineDto.class));
    }

    @Test
    @DisplayName("Test controller update Engine")
    void updateEngine() throws Exception{
        doNothing().when(engineService).updateEngine(anyLong(), any(EngineDto.class));

        mockMvc.perform(put("/engines/{id}", 2L)
               .contentType("application/json")
               .content("{\"bhp\": 450,\"liters\":3.5 ,\"model\": \"V6\", \"levelDto\": {\"id\": 5, \"level\": \"Elite\"}}"))
               .andExpect(status().isOk());

        verify(engineService, times(1)).updateEngine(anyLong(), any(EngineDto.class));
    }

    @Test
    @DisplayName("Test controller delete engine")
    void deleteEngine() throws Exception{
        doNothing().when(engineService).deleteEngine(anyLong());

        mockMvc.perform(delete("/engines/{id}", 2L))
               .andExpect(status().isOk());

        verify(engineService, times(1)).deleteEngine(anyLong());
    }
}