package com.example.demospringint.dtoModel;

import com.example.demospringint.model.Course;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    private int id;
    private String name;
    private int age;
    private List<CourseDTO> coursesList;
}