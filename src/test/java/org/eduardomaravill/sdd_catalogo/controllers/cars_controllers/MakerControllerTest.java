package org.eduardomaravill.sdd_catalogo.controllers.cars_controllers;

import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.MakerDto;
import org.eduardomaravill.sdd_catalogo.repositories.users_respositories.IJwtTokenRepository;
import org.eduardomaravill.sdd_catalogo.services.auth.IJwtService;
import org.eduardomaravill.sdd_catalogo.services.cars_services.IMakerService;
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

@WebMvcTest(MakerController.class)
@AutoConfigureMockMvc(addFilters = false)
class MakerControllerTest {

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
    private IMakerService makerService;

    private MakerDto makerDto;

    @BeforeEach
    void setUp() {
        makerDto = new MakerDto(35L, "BMW");
    }

    @Test
    @DisplayName("Test controller get Maker")
    void getMaker() throws Exception {
        when(makerService.getMaker(anyLong())).thenReturn(makerDto);

        mockMvc.perform(get("/makers/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(35))
                .andExpect(jsonPath("$.name").value("BMW"));

        verify(makerService, times(1)).getMaker(anyLong());
    }

    @Test
    @DisplayName("Test controller getAllMakers")
    void getAllMakers() throws Exception {
        List<MakerDto> makerDtoList = List.of(makerDto, makerDto);
        when(makerService.getAllMakers()).thenReturn(makerDtoList);

        mockMvc.perform(get("/makers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(35))
                .andExpect(jsonPath("$[1].name").value("BMW"))
                .andExpect(jsonPath("$.size()").value(makerDtoList.size()));

        verify(makerService, times(1)).getAllMakers();
    }

    @Test
    @DisplayName("Test controller create Maker")
    void createMaker() throws Exception {
        doNothing().when(makerService).createMaker(any(MakerDto.class));

        mockMvc.perform(post("/makers")
               .contentType(MediaType.APPLICATION_JSON)
               .content("{\"name\": \"BMW\"}"))
               .andExpect(status().isCreated());

        verify(makerService, times(1)).createMaker(any(MakerDto.class));
    }

    @Test
    @DisplayName("Test controller update Maker")
    void updateMaker() throws Exception {
        doNothing().when(makerService).updateMaker(anyLong(), any(MakerDto.class));

        mockMvc.perform(put("/makers/{id}", 2L)
               .contentType(MediaType.APPLICATION_JSON)
               .content("{\"name\": \"Mercedes\"}"))
               .andExpect(status().isOk());

        verify(makerService, times(1)).updateMaker(anyLong(), any(MakerDto.class));
    }

    @Test
    @DisplayName("Test controller delete Maker")
    void deleteMaker() throws Exception {
        doNothing().when(makerService).deleteMaker(anyLong());

        mockMvc.perform(delete("/makers/{id}", 3L))
               .andExpect(status().isOk());

        verify(makerService, times(1)).deleteMaker(anyLong());
    }
}