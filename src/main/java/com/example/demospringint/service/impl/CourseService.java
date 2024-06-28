package com.example.demospringint.service.impl;

import com.example.demospringint.dtoModel.CourseDTO;
import com.example.demospringint.model.Course;
import com.example.demospringint.repository.CourseRepository;
import com.example.demospringint.service.ServicesInt;
import com.example.demospringint.util.CourseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService implements ServicesInt<CourseDTO> {

    private final CourseRepository courseRepository;

    public CourseDTO create(CourseDTO courseDTO) {
        Course course = courseRepository.save(CourseMapper.courseDTOToCourse(courseDTO));
        return CourseMapper.courseToCourseDTO(course);
    }

    public List<CourseDTO> read() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream().map(CourseMapper::courseToCourseDTO)
                .collect(Collectors.toList());
    }

    public CourseDTO getById(int id) {
        return CourseMapper.courseToCourseDTO(courseRepository.findById(id).get()) ;
    }

    public void deleteById(int id) {
        if (!courseRepository.existsById(id)) {
            throw new RuntimeException("Course not found");
        }
        courseRepository.deleteById(id);
    }

    public void update(int id, CourseDTO updatedCourse) {
        Course existingCourse = CourseMapper.courseDTOToCourse(getById(id));
        BeanUtils.copyProperties(updatedCourse, existingCourse, "id");
        courseRepository.save(existingCourse);
    }
}
