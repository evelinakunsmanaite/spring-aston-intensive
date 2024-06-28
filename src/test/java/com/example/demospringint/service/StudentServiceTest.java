package com.example.demospringint.service;

import com.example.demospringint.dtoModel.StudentDTO;
import com.example.demospringint.model.Student;
import com.example.demospringint.repository.StudentRepository;
import com.example.demospringint.service.impl.StudentService;
import com.example.demospringint.util.StudentMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    void testCreateStudent() {
        StudentDTO studentDTO = new StudentDTO();
        Student student = new Student();

        try (MockedStatic<StudentMapper> mockedStudentMapper = mockStatic(StudentMapper.class)) {
            when(studentRepository.save(any(Student.class))).thenReturn(student);
            mockedStudentMapper.when(() -> StudentMapper.studentDTOToStudent(studentDTO)).thenReturn(student);
            mockedStudentMapper.when(() -> StudentMapper.studentToStudentDTO(student)).thenReturn(studentDTO);

            StudentDTO result = studentService.create(studentDTO);

            assertNotNull(result);
            verify(studentRepository, times(1)).save(student);
        }
    }

    @Test
    void testReadStudents() {
        Student student = new Student();
        List<Student> students = Arrays.asList(student);

        try (MockedStatic<StudentMapper> mockedStudentMapper = mockStatic(StudentMapper.class)) {
            when(studentRepository.findAll()).thenReturn(students);
            mockedStudentMapper.when(() -> StudentMapper.studentToStudentDTO(student)).thenReturn(new StudentDTO());

            List<StudentDTO> result = studentService.read();

            assertEquals(1, result.size());
            verify(studentRepository, times(1)).findAll();
        }
    }

    @Test
    void testGetStudentById() {
        int id = 1;
        Student student = new Student();

        try (MockedStatic<StudentMapper> mockedStudentMapper = mockStatic(StudentMapper.class)) {
            when(studentRepository.findById(id)).thenReturn(Optional.of(student));
            mockedStudentMapper.when(() -> StudentMapper.studentToStudentDTO(student)).thenReturn(new StudentDTO());

            StudentDTO result = studentService.getById(id);

            assertNotNull(result);
            verify(studentRepository, times(1)).findById(id);
        }
    }

    @Test
    void testDeleteStudentById() {
        int id = 1;
        when(studentRepository.existsById(id)).thenReturn(true);

        studentService.deleteById(id);

        verify(studentRepository, times(1)).deleteById(id);
    }

    @Test
    void testUpdateStudent() {
        int id = 1;
        StudentDTO studentDTO = new StudentDTO();
        Student student = new Student();

        try (MockedStatic<StudentMapper> mockedStudentMapper = mockStatic(StudentMapper.class)) {
            when(studentRepository.findById(id)).thenReturn(Optional.of(student));
            mockedStudentMapper.when(() -> StudentMapper.studentToStudentDTO(student)).thenReturn(studentDTO);
            mockedStudentMapper.when(() -> StudentMapper.studentDTOToStudent(studentDTO)).thenReturn(student);

            studentService.update(id, studentDTO);

            verify(studentRepository, times(1)).save(student);
        }
    }
}