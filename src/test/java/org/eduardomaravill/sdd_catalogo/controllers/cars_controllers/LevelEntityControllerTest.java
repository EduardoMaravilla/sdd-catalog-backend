package org.eduardomaravill.sdd_catalogo.controllers.cars_controllers;

import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.LevelDto;
import org.eduardomaravill.sdd_catalogo.repositories.users_respositories.IJwtTokenRepository;
import org.eduardomaravill.sdd_catalogo.services.auth.IJwtService;
import org.eduardomaravill.sdd_catalogo.services.cars_services.ILevelService;
import org.eduardomaravill.sdd_catalogo.services.cars_services.IStreetTypeService;
import org.eduardomaravill.sdd_catalogo.services.users_services.IUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LevelController.class)
@AutoConfigureMockMvc(addFilters = false)
class LevelEntityControllerTest {

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
    private ILevelService levelService;

    private LevelDto levelDto;

    @BeforeEach
    void setUp() {
        levelDto = new LevelDto(2L, "Deportivo");
    }

    @Test
    @DisplayName("Test controller get Level")
    void getLevel() throws Exception {
        when(levelService.getLevel(anyLong())).thenReturn(levelDto);
        mockMvc.perform(get("/levels/{id}", 2L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2L))
                .andExpect(jsonPath("$.level").value("Deportivo"));

        verify(levelService, times(1)).getLevel(anyLong());
    }

    @Test
    @DisplayName("Test controller getAllLevels")
    void getAllLevels() throws Exception {
        List<LevelDto> levelDtoList = List.of(levelDto, levelDto, levelDto);
        when(levelService.getAllLevels()).thenReturn(levelDtoList);

        mockMvc.perform(get("/levels"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(2L))
                .andExpect(jsonPath("$[2].level").value("Deportivo"))
                .andExpect(jsonPath("$.size()").value(levelDtoList.size()));

        verify(levelService, times(1)).getAllLevels();
    }

    @Test
    @DisplayName("Test controller create Level")
    void createLevel() throws Exception {
        doNothing().when(levelService).createLevel(any(LevelDto.class));

        mockMvc.perform(post("/levels")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"level\": \"Deportivo\"}"))
                .andExpect(status().isCreated());

        verify(levelService, times(1)).createLevel(any(LevelDto.class));
    }

    @Test
    @DisplayName("Test controller update Level")
    void updateLevel() throws Exception {
        doNothing().when(levelService).updateLevel(anyLong(), any(LevelDto.class));

        mockMvc.perform(put("/levels/{id}", 2L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"level\": \"Deportivo\"}"))
                .andExpect(status().isOk());

        verify(levelService, times(1)).updateLevel(anyLong(), any(LevelDto.class));
    }

    @Test
    @DisplayName("Test controller delete Level")
    void deleteLevel() throws Exception {
        doNothing().when(levelService).deleteLevel(anyLong());

        mockMvc.perform(delete("/levels/{id}", 2L))
               .andExpect(status().isOk());

        verify(levelService, times(1)).deleteLevel(anyLong());
    }

}