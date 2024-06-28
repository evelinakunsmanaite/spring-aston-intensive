package com.example.demospringint.controller;

import com.example.demospringint.dtoModel.CourseDTO;
import com.example.demospringint.service.impl.CourseService;
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

@WebMvcTest(CourseController.class)
public class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseService courseService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllCourses() throws Exception {
        CourseDTO course = new CourseDTO();
        course.setId(1);
        course.setCourseName("name");

        Mockito.when(courseService.read()).thenReturn(Collections.singletonList(course));

        mockMvc.perform(get("/courses/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].courseName").value("name"));
    }

    @Test
    public void testAddCourse() throws Exception {
        CourseDTO course = new CourseDTO();
        course.setCourseName("name");

        CourseDTO savedCourse = new CourseDTO();
        savedCourse.setId(1);
        savedCourse.setCourseName("name");

        Mockito.when(courseService.create(Mockito.any(CourseDTO.class))).thenReturn(savedCourse);

        mockMvc.perform(post("/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(course)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.courseName").value("name"));
    }

    @Test
    public void testDeleteCourse() throws Exception {
        mockMvc.perform(delete("/courses/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testGetCourseById() throws Exception {
        CourseDTO course = new CourseDTO();
        course.setId(1);
        course.setCourseName("name");

        Mockito.when(courseService.getById(1)).thenReturn(course);

        mockMvc.perform(get("/courses/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.courseName").value("name"));
    }
}