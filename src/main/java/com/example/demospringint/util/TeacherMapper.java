package com.example.demospringint.util;

import com.example.demospringint.dtoModel.TeacherDTO;
import com.example.demospringint.model.Teacher;
import org.mapstruct.Mapper;

import java.util.stream.Collectors;

@Mapper
public class TeacherMapper {

    public static TeacherDTO teacherToTeacherDTO(Teacher teacher) {
        return TeacherDTO.builder()
                .id(teacher.getId())
                .name(teacher.getName())
                .phoneNumber(teacher.getPhoneNumber())
                .age(teacher.getAge())
                .coursesList(teacher.getCoursesList().stream()
                        .map(CourseMapper::courseToCourseDTO)
                        .collect(Collectors.toList()))
                .build();
    }

    public static Teacher teacherDTOToTeacher(TeacherDTO teacherDTO) {
        return Teacher.builder()
                .id(teacherDTO.getId())
                .name(teacherDTO.getName())
                .phoneNumber(teacherDTO.getPhoneNumber())
                .age(teacherDTO.getAge())
                .coursesList(teacherDTO.getCoursesList().stream()
                        .map(CourseMapper::courseDTOToCourse)
                        .collect(Collectors.toList()))
                .build();
    }
}
