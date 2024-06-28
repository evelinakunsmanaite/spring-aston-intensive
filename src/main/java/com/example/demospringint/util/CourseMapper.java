package com.example.demospringint.util;

import com.example.demospringint.dtoModel.CourseDTO;
import com.example.demospringint.model.Course;
import org.mapstruct.Mapper;

import java.util.stream.Collectors;

@Mapper
public class  CourseMapper {

    public static CourseDTO courseToCourseDTO(Course course) {
        return CourseDTO.builder()
                .id(course.getId())
                .courseName(course.getCourseName())
                .teacher(TeacherMapper.teacherToTeacherDTO(course.getTeacher()))
                .studentList(course.getStudentList().stream()
                        .map(StudentMapper::studentToStudentDTO)
                        .collect(Collectors.toList()))
                .build();
    }

    public static Course courseDTOToCourse(CourseDTO courseDTO) {
        return Course.builder()
                .id(courseDTO.getId())
                .courseName(courseDTO.getCourseName())
                .teacher(TeacherMapper.teacherDTOToTeacher(courseDTO.getTeacher()))
                .studentList(courseDTO.getStudentList().stream()
                        .map(StudentMapper::studentDTOToStudent)
                        .collect(Collectors.toList()))
                .build();
    }
}
