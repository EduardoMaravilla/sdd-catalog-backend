package org.eduardomaravill.sdd_catalogo.controllers.cars_controllers;

import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.ClassesDto;
import org.eduardomaravill.sdd_catalogo.repositories.users_respositories.IJwtTokenRepository;
import org.eduardomaravill.sdd_catalogo.services.auth.IJwtService;
import org.eduardomaravill.sdd_catalogo.services.cars_services.IClassesService;
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

@WebMvcTest(ClassesController.class)
@AutoConfigureMockMvc(addFilters = false)
class ClassesControllerTest {

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
    private IClassesService classesService;

    private ClassesDto classesDto;

    @BeforeEach
    void setUp() {
        classesDto = new ClassesDto(1L, "A+");
    }

    @Test
    @DisplayName("Test controller get Classes")
    void getClasses() throws Exception {
        when(classesService.getClasses(anyLong())).thenReturn(classesDto);

        mockMvc.perform(get("/classes/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(classesDto.getId()))
                .andExpect(jsonPath("$.name").value(classesDto.getName()));

        verify(classesService, times(1)).getClasses(anyLong());
    }

    @Test
    @DisplayName("Test controller getAllClasses")
    void getAllClasses() throws Exception {
        List<ClassesDto> classesDtoList = List.of(classesDto, classesDto, classesDto);
        when(classesService.getAllClasses()).thenReturn(classesDtoList);

        mockMvc.perform(get("/classes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(classesDtoList.getFirst().getId()))
                .andExpect(jsonPath("$[1].name").value(classesDtoList.get(1).getName()))
                .andExpect(jsonPath("$.size()").value(classesDtoList.size()));

        verify(classesService, times(1)).getAllClasses();

    }

    @Test
    @DisplayName("Test controller create Classes")
    void createClass() throws Exception{
        doNothing().when(classesService).createClass(any(ClassesDto.class));

        mockMvc.perform(post("/classes")
               .contentType("application/json")
               .content("{\"name\": \"A+\"}"))
               .andExpect(status().isCreated());

        verify(classesService, times(1)).createClass(any(ClassesDto.class));
    }

    @Test
    @DisplayName("Test controller update classes")
    void updateClass() throws Exception{
        doNothing().when(classesService).updateClass(anyLong(), any(ClassesDto.class));

        mockMvc.perform(put("/classes/{id}", 2L)
               .contentType("application/json")
               .content("{\"name\": \"A+\"}"))
               .andExpect(status().isOk());

        verify(classesService, times(1)).updateClass(anyLong(), any(ClassesDto.class));
    }

    @Test
    @DisplayName("Test controller delete Classes")
    void deleteClass() throws Exception{
        doNothing().when(classesService).deleteClass(anyLong());

        mockMvc.perform(delete("/classes/{id}", 2L))
               .andExpect(status().isOk());

        verify(classesService, times(1)).deleteClass(anyLong());
    }
}