package org.eduardomaravill.sdd_catalogo.controllers.cars_controllers;

import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.LevelDto;
import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.StreetTypeDto;
import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.TireDto;
import org.eduardomaravill.sdd_catalogo.repositories.users_respositories.IJwtTokenRepository;
import org.eduardomaravill.sdd_catalogo.services.auth.IJwtService;
import org.eduardomaravill.sdd_catalogo.services.cars_services.IStreetTypeService;
import org.eduardomaravill.sdd_catalogo.services.cars_services.ITireService;
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

@WebMvcTest(TireController.class)
@AutoConfigureMockMvc(addFilters = false)
class TireControllerTest {

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
    private ITireService tireService;

    private TireDto tireDto;

    @BeforeEach
    void setUp() {
        tireDto = new TireDto(5L, new StreetTypeDto(4L, "Asfalto"), new LevelDto(3L, "Pro"));
    }

    @Test
    @DisplayName("Test controller get Tire")
    void getTire() throws Exception {
        when(tireService.getTire(anyLong())).thenReturn(tireDto);

        mockMvc.perform(get("/tires/{id}", 4L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(5))
                .andExpect(jsonPath("$.streetTypeDto.streetTypeVal").value("Asfalto"))
                .andExpect(jsonPath("$.levelDto.level").value("Pro"));

        verify(tireService, times(1)).getTire(anyLong());
    }

    @Test
    @DisplayName("Test controller getAllTires")
    void getAllTires() throws Exception {
        List<TireDto> tireDtoList = List.of(tireDto, tireDto, tireDto);
        when(tireService.getAllTires()).thenReturn(tireDtoList);

        mockMvc.perform(get("/tires"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(5L))
                .andExpect(jsonPath("$[1].streetTypeDto.streetTypeVal").value("Asfalto"))
                .andExpect(jsonPath("$[2].levelDto.level").value("Pro"))
                .andExpect(jsonPath("$.size()").value(tireDtoList.size()));

        verify(tireService, times(1)).getAllTires();
    }

    @Test
    @DisplayName("Test controller create Tire")
    void createTire() throws Exception {
        doNothing().when(tireService).createTire(any(TireDto.class));

        mockMvc.perform(post("/tires")
                        .contentType("application/json")
                        .content("{\"streetTypeDto\":{\"id\":4,\"streetTypeVal\":\"Asfalto\"},\"levelDto\":{\"id\":3,\"level\":\"Pro\"}}"))
                .andExpect(status().isCreated());

        verify(tireService, times(1)).createTire(any(TireDto.class));
    }

    @Test
    @DisplayName("Test controller update Tire")
    void updateTire() throws Exception {
        doNothing().when(tireService).updateTire(anyLong(), any(TireDto.class));

        mockMvc.perform(put("/tires/{id}", 4L)
                        .contentType("application/json")
                        .content("{\"streetTypeDto\":{\"id\":4,\"streetTypeVal\":\"Asfalto\"},\"levelDto\":{\"id\":3,\"level\":\"Pro\"}}"))
                .andExpect(status().isOk());

        verify(tireService, times(1)).updateTire(anyLong(), any(TireDto.class));
    }

    @Test
    @DisplayName("Test controller delete Tire")
    void deleteTire() throws Exception {
        doNothing().when(tireService).deleteTire(anyLong());

        mockMvc.perform(delete("/tires/{id}", 4L))
                .andExpect(status().isOk());

        verify(tireService, times(1)).deleteTire(anyLong());
    }
}