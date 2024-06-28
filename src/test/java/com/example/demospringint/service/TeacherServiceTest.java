package com.example.demospringint.service;
import com.example.demospringint.dtoModel.TeacherDTO;
import com.example.demospringint.model.Teacher;
import com.example.demospringint.repository.TeacherRepository;
import com.example.demospringint.service.impl.TeacherService;
import com.example.demospringint.util.TeacherMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TeacherServiceTest {

    @Mock
    private TeacherRepository teacherRepository;

    @InjectMocks
    private TeacherService teacherService;

    @Test
    void testCreateTeacher() {
        TeacherDTO teacherDTO = new TeacherDTO();
        Teacher teacher = new Teacher();
        teacher.setCoursesList(new ArrayList<>());

        when(teacherRepository.save(any(Teacher.class))).thenReturn(teacher);
        try (MockedStatic<TeacherMapper> mockedTeacherMapper = mockStatic(TeacherMapper.class)) {
            mockedTeacherMapper.when(() -> TeacherMapper.teacherDTOToTeacher(teacherDTO)).thenReturn(teacher);
            mockedTeacherMapper.when(() -> TeacherMapper.teacherToTeacherDTO(teacher)).thenReturn(teacherDTO);

            TeacherDTO result = teacherService.create(teacherDTO);

            assertNotNull(result);
            verify(teacherRepository, times(1)).save(teacher);
        }
    }

    @Test
    void testReadTeachers() {
        Teacher teacher = new Teacher();
        teacher.setCoursesList(new ArrayList<>());
        List<Teacher> teachers = Arrays.asList(teacher);

        when(teacherRepository.findAll()).thenReturn(teachers);
        try (MockedStatic<TeacherMapper> mockedTeacherMapper = mockStatic(TeacherMapper.class)) {
            mockedTeacherMapper.when(() -> TeacherMapper.teacherToTeacherDTO(teacher)).thenReturn(new TeacherDTO());

            List<TeacherDTO> result = teacherService.read();

            assertEquals(1, result.size());
            verify(teacherRepository, times(1)).findAll();
        }
    }

    @Test
    void testGetTeacherById() {
        int id = 1;
        Teacher teacher = new Teacher();
        teacher.setCoursesList(new ArrayList<>());

        when(teacherRepository.findById(id)).thenReturn(Optional.of(teacher));
        try (MockedStatic<TeacherMapper> mockedTeacherMapper = mockStatic(TeacherMapper.class)) {
            mockedTeacherMapper.when(() -> TeacherMapper.teacherToTeacherDTO(teacher)).thenReturn(new TeacherDTO());

            TeacherDTO result = teacherService.getById(id);

            assertNotNull(result);
            verify(teacherRepository, times(1)).findById(id);
        }
    }

    @Test
    void testDeleteTeacherById() {
        int id = 1;
        when(teacherRepository.existsById(id)).thenReturn(true);

        teacherService.deleteById(id);

        verify(teacherRepository, times(1)).deleteById(id);
    }

    @Test
    void testUpdateTeacher() {
        int id = 1;
        TeacherDTO teacherDTO = new TeacherDTO();
        Teacher teacher = new Teacher();
        teacher.setCoursesList(new ArrayList<>());

        when(teacherRepository.findById(id)).thenReturn(Optional.of(teacher));
        try (MockedStatic<TeacherMapper> mockedTeacherMapper = mockStatic(TeacherMapper.class)) {
            mockedTeacherMapper.when(() -> TeacherMapper.teacherToTeacherDTO(teacher)).thenReturn(teacherDTO);
            mockedTeacherMapper.when(() -> TeacherMapper.teacherDTOToTeacher(teacherDTO)).thenReturn(teacher);

            teacherService.update(id, teacherDTO);

            verify(teacherRepository, times(1)).save(teacher);
        }
    }
}
