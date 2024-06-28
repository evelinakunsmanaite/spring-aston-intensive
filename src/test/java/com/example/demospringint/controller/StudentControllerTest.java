package com.example.demospringint.controller;

import com.example.demospringint.dtoModel.StudentDTO;
import com.example.demospringint.service.impl.StudentService;
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

@WebMvcTest(StudentController.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllStudents() throws Exception {
        StudentDTO student = new StudentDTO();
        student.setId(1);
        student.setName("name");

        Mockito.when(studentService.read()).thenReturn(Collections.singletonList(student));

        mockMvc.perform(get("/students/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("name"));
    }

    @Test
    public void testAddStudent() throws Exception {
        StudentDTO student = new StudentDTO();
        student.setName("name");

        StudentDTO savedStudent = new StudentDTO();
        savedStudent.setId(1);
        savedStudent.setName("name");

        Mockito.when(studentService.create(Mockito.any(StudentDTO.class))).thenReturn(savedStudent);

        mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("name"));
    }

    @Test
    public void testDeleteStudent() throws Exception {
        mockMvc.perform(delete("/students/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testGetStudentById() throws Exception {
        StudentDTO student = new StudentDTO();
        student.setId(1);
        student.setName("name");

        Mockito.when(studentService.getById(1)).thenReturn(student);

        mockMvc.perform(get("/students/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("name"));
    }
}
