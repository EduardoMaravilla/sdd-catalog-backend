package org.eduardomaravill.sdd_catalogo.controllers.cars_controllers;

import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.StreetTypeDto;
import org.eduardomaravill.sdd_catalogo.repositories.users_respositories.IJwtTokenRepository;
import org.eduardomaravill.sdd_catalogo.services.auth.IJwtService;
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

@WebMvcTest(StreetTypeController.class)
@AutoConfigureMockMvc(addFilters = false)
class StreetTypeControllerTest {

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

    private StreetTypeDto streetTypeDto;

    @BeforeEach
    void setUp() {
        streetTypeDto = new StreetTypeDto(6L, "Derrape Pro");
    }

    @Test
    @DisplayName("Test controller get StreetType")
    void getStreetType() throws Exception {
        when(streetTypeService.getStreetType(anyLong())).thenReturn(streetTypeDto);

        mockMvc.perform(get("/street-types/{id}", 6L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(6L))
                .andExpect(jsonPath("$.streetTypeVal").value("Derrape Pro"));

        verify(streetTypeService, times(1)).getStreetType(6L);
    }

    @Test
    @DisplayName("Test controller getAllStreetTypes")
    void getAllStreetTypes() throws Exception{
        List<StreetTypeDto> streetTypeDtoList = List.of(streetTypeDto,streetTypeDto,streetTypeDto);
        when(streetTypeService.getAllStreetTypes()).thenReturn(streetTypeDtoList);
        mockMvc.perform(get("/street-types"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(6L))
                .andExpect(jsonPath("$[2].streetTypeVal").value("Derrape Pro"))
                .andExpect(jsonPath("$.size()").value(streetTypeDtoList.size()));

        verify(streetTypeService, times(1)).getAllStreetTypes();
    }

    @Test
    @DisplayName("Test controller create StreetType")
    void createStreetType() throws Exception{
        doNothing().when(streetTypeService).createStreetType(any(StreetTypeDto.class));

        mockMvc.perform(post("/street-types")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"streetTypeVal\" : \"Derrape Pro\"}"))
                .andExpect(status().isCreated());

        verify(streetTypeService, times(1)).createStreetType(any(StreetTypeDto.class));
    }

    @Test
    @DisplayName("Test controller update StreetType")
    void updateStreetType() throws Exception{
        doNothing().when(streetTypeService).updateStreetType(anyLong(), any(StreetTypeDto.class));

        mockMvc.perform(put("/street-types/{id}",6L)
               .contentType(MediaType.APPLICATION_JSON)
               .content("{\"streetTypeVal\" : \"Derrape Pro\"}"))
               .andExpect(status().isOk());

        verify(streetTypeService, times(1)).updateStreetType(anyLong(), any(StreetTypeDto.class));
    }

    @Test
    @DisplayName("Test controller delete StreetType")
    void deleteStreetType() throws Exception{
        doNothing().when(streetTypeService).deleteStreetType(anyLong());

        mockMvc.perform(delete("/street-types/{id}",6L))
                .andExpect(status().isOk());

        verify(streetTypeService, times(1)).deleteStreetType(anyLong());
    }
}