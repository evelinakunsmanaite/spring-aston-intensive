package com.example.demospringint.service;

import com.example.demospringint.dtoModel.CourseDTO;
import com.example.demospringint.dtoModel.TeacherDTO;
import com.example.demospringint.model.Course;
import com.example.demospringint.model.Teacher;
import com.example.demospringint.repository.CourseRepository;
import com.example.demospringint.service.impl.CourseService;
import com.example.demospringint.util.CourseMapper;
import com.example.demospringint.util.TeacherMapper;
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
public class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CourseService courseService;

    @Test
    void testCreateCourse() {
        CourseDTO courseDTO = new CourseDTO();
        TeacherDTO teacherDTO = new TeacherDTO();
        courseDTO.setTeacher(teacherDTO);
        Course course = new Course();
        Teacher teacher = new Teacher();
        course.setTeacher(teacher);

        try (MockedStatic<CourseMapper> mockedCourseMapper = mockStatic(CourseMapper.class);
             MockedStatic<TeacherMapper> mockedTeacherMapper = mockStatic(TeacherMapper.class)) {

            when(courseRepository.save(any(Course.class))).thenReturn(course);
            mockedCourseMapper.when(() -> CourseMapper.courseDTOToCourse(courseDTO)).thenReturn(course);
            mockedCourseMapper.when(() -> CourseMapper.courseToCourseDTO(course)).thenReturn(courseDTO);
            mockedTeacherMapper.when(() -> TeacherMapper.teacherDTOToTeacher(teacherDTO)).thenReturn(teacher);
            mockedTeacherMapper.when(() -> TeacherMapper.teacherToTeacherDTO(teacher)).thenReturn(teacherDTO);

            CourseDTO result = courseService.create(courseDTO);

            assertNotNull(result);
            verify(courseRepository, times(1)).save(course);
        }
    }

    @Test
    void testReadCourses() {
        Course course = new Course();
        Teacher teacher = new Teacher();
        course.setTeacher(teacher);
        List<Course> courses = Arrays.asList(course);

        try (MockedStatic<CourseMapper> mockedCourseMapper = mockStatic(CourseMapper.class);
             MockedStatic<TeacherMapper> mockedTeacherMapper = mockStatic(TeacherMapper.class)) {

            when(courseRepository.findAll()).thenReturn(courses);
            mockedCourseMapper.when(() -> CourseMapper.courseToCourseDTO(course)).thenReturn(new CourseDTO());
            mockedTeacherMapper.when(() -> TeacherMapper.teacherToTeacherDTO(teacher)).thenReturn(new TeacherDTO());

            List<CourseDTO> result = courseService.read();

            assertEquals(1, result.size());
            verify(courseRepository, times(1)).findAll();
        }
    }

    @Test
    void testGetCourseById() {
        int id = 1;
        Course course = new Course();
        Teacher teacher = new Teacher();
        course.setTeacher(teacher);

        try (MockedStatic<CourseMapper> mockedCourseMapper = mockStatic(CourseMapper.class);
             MockedStatic<TeacherMapper> mockedTeacherMapper = mockStatic(TeacherMapper.class)) {

            when(courseRepository.findById(id)).thenReturn(Optional.of(course));
            mockedCourseMapper.when(() -> CourseMapper.courseToCourseDTO(course)).thenReturn(new CourseDTO());
            mockedTeacherMapper.when(() -> TeacherMapper.teacherToTeacherDTO(teacher)).thenReturn(new TeacherDTO());

            CourseDTO result = courseService.getById(id);

            assertNotNull(result);
            verify(courseRepository, times(1)).findById(id);
        }
    }

    @Test
    void testDeleteCourseById() {
        int id = 1;
        when(courseRepository.existsById(id)).thenReturn(true);

        courseService.deleteById(id);

        verify(courseRepository, times(1)).deleteById(id);
    }

    @Test
    void testUpdateCourse() {
        int id = 1;
        CourseDTO courseDTO = new CourseDTO();
        TeacherDTO teacherDTO = new TeacherDTO();
        courseDTO.setTeacher(teacherDTO);
        Course course = new Course();
        Teacher teacher = new Teacher();
        course.setTeacher(teacher);

        try (MockedStatic<CourseMapper> mockedCourseMapper = mockStatic(CourseMapper.class);
             MockedStatic<TeacherMapper> mockedTeacherMapper = mockStatic(TeacherMapper.class)) {

            when(courseRepository.findById(id)).thenReturn(Optional.of(course));
            mockedCourseMapper.when(() -> CourseMapper.courseToCourseDTO(course)).thenReturn(courseDTO);
            mockedCourseMapper.when(() -> CourseMapper.courseDTOToCourse(courseDTO)).thenReturn(course);
            mockedTeacherMapper.when(() -> TeacherMapper.teacherDTOToTeacher(teacherDTO)).thenReturn(teacher);
            mockedTeacherMapper.when(() -> TeacherMapper.teacherToTeacherDTO(teacher)).thenReturn(teacherDTO);

            courseService.update(id, courseDTO);

            verify(courseRepository, times(1)).save(course);
        }
    }
}