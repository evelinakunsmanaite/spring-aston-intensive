package com.example.demospringint.util;

import com.example.demospringint.dtoModel.StudentDTO;
import com.example.demospringint.model.Student;
import org.mapstruct.Mapper;

import java.util.stream.Collectors;

@Mapper
public class StudentMapper {

    public static StudentDTO studentToStudentDTO(Student student) {
        return StudentDTO.builder()
                .id(student.getId())
                .name(student.getName())
                .age(student.getAge())
                .coursesList(student.getCoursesList().stream()
                        .map(CourseMapper::courseToCourseDTO)
                        .collect(Collectors.toList()))
                .build();
    }

    public static Student studentDTOToStudent(StudentDTO studentDTO) {
        return Student.builder()
                .id(studentDTO.getId())
                .name(studentDTO.getName())
                .age(studentDTO.getAge())
                .coursesList(studentDTO.getCoursesList().stream()
                        .map(CourseMapper::courseDTOToCourse)
                        .collect(Collectors.toList()))
                .build();
    }
}
