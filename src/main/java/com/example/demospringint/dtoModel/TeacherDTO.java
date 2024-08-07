package com.example.demospringint.dtoModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDTO {
    private int id;
    private String name;
    private String phoneNumber;
    private int age;
    private List<CourseDTO> coursesList;
}
