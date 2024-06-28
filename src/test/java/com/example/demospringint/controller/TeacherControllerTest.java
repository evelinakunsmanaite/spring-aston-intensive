package com.example.demospringint.controller;

import com.example.demospringint.dtoModel.TeacherDTO;
import com.example.demospringint.service.impl.TeacherService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TeacherController.class)
public class TeacherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TeacherService teacherService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllTeachers() throws Exception {
        TeacherDTO teacher = new TeacherDTO();
        teacher.setId(1);
        teacher.setName("name");

        Mockito.when(teacherService.read()).thenReturn(Collections.singletonList(teacher));

        mockMvc.perform(get("/teachers/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("name"));
    }

    @Test
    public void testAddTeacher() throws Exception {
        TeacherDTO teacher = new TeacherDTO();
        teacher.setName("name");

        TeacherDTO savedTeacher = new TeacherDTO();
        savedTeacher.setId(1);
        savedTeacher.setName("name");

        Mockito.when(teacherService.create(Mockito.any(TeacherDTO.class))).thenReturn(savedTeacher);

        mockMvc.perform(post("/teachers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(teacher)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("name"));
    }

    @Test
    public void testDeleteTeacher() throws Exception {
        mockMvc.perform(delete("/teachers/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testGetTeacherById() throws Exception {
        TeacherDTO teacher = new TeacherDTO();
        teacher.setId(1);
        teacher.setName("name");

        Mockito.when(teacherService.getById(1)).thenReturn(teacher);

        mockMvc.perform(get("/teachers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("name"));
    }
}
