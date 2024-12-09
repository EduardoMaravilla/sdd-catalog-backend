package org.eduardomaravill.sdd_catalogo.controllers.cars_controllers;

import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.InitSkidDto;
import org.eduardomaravill.sdd_catalogo.repositories.users_respositories.IJwtTokenRepository;
import org.eduardomaravill.sdd_catalogo.services.auth.IJwtService;
import org.eduardomaravill.sdd_catalogo.services.cars_services.IInitSkidService;
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

@WebMvcTest(InitSkidController.class)
@AutoConfigureMockMvc(addFilters = false)
class InitSkidControllerTest {

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
    private IInitSkidService initSkidService;

    private InitSkidDto initSkidDto;


    @BeforeEach
    void setUp() {
        initSkidDto = new InitSkidDto(4L,"POR DEFECTO");
    }

    @Test
    @DisplayName("Test controller get InitSkid")
    void getInitSkid() throws Exception{
        when(initSkidService.getInitSkid(anyLong())).thenReturn(initSkidDto);

        mockMvc.perform(get("/init-skids/{id}",4L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(initSkidDto.getId()))
                .andExpect(jsonPath("$.skidType").value(initSkidDto.getSkidType()));

        verify(initSkidService, times(1)).getInitSkid(anyLong());
    }

    @Test
    @DisplayName("Test controller getAllInitSkids")
    void getAllInitSkids() throws Exception {
        List<InitSkidDto> initSkidDtoList = List.of(initSkidDto, initSkidDto, initSkidDto);
        when(initSkidService.getAllInitSkids()).thenReturn(initSkidDtoList);

        mockMvc.perform(get("/init-skids"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$[0].id").value(initSkidDtoList.getFirst().getId()))
               .andExpect(jsonPath("$[2].skidType").value(initSkidDtoList.get(2).getSkidType()))
                .andExpect(jsonPath("$.size()").value(initSkidDtoList.size()));

        verify(initSkidService, times(1)).getAllInitSkids();
    }

    @Test
    @DisplayName("Test controller create InitSkid")
    void createInitSkid() throws Exception{
        doNothing().when(initSkidService).createInitSkid(any(InitSkidDto.class));

        mockMvc.perform(post("/init-skids")
               .contentType("application/json")
               .content("{\"skidType\":\"POR DEFECTO\"}"))
               .andExpect(status().isCreated());

        verify(initSkidService, times(1)).createInitSkid(any(InitSkidDto.class));
    }

    @Test
    @DisplayName("Test controller updateInitSkid")
    void updateInitSkid() throws Exception{
        doNothing().when(initSkidService).updateInitSkid(anyLong(), any(InitSkidDto.class));

        mockMvc.perform(put("/init-skids/{id}",4L)
               .contentType("application/json")
               .content("{\"skidType\":\"POR DEFECTO\"}"))
               .andExpect(status().isOk());

        verify(initSkidService, times(1)).updateInitSkid(anyLong(), any(InitSkidDto.class));
    }

    @Test
    @DisplayName("Test controller delete InitSkid")
    void deleteInitSkid() throws Exception{
        doNothing().when(initSkidService).deleteInitSkid(anyLong());

        mockMvc.perform(delete("/init-skids/{id}",4L))
               .andExpect(status().isOk());

        verify(initSkidService, times(1)).deleteInitSkid(anyLong());
    }
}