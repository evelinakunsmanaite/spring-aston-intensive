package com.example.demospringint.dtoModel;

import com.example.demospringint.model.Student;
import com.example.demospringint.model.Teacher;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {
    private int id;
    private String courseName;
    private TeacherDTO teacher;
    private List<StudentDTO> studentList;
}
