package com.example.demospringint.controller;

import com.example.demospringint.dtoModel.CourseDTO;
import com.example.demospringint.service.impl.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;

    @GetMapping("/")
    public List<CourseDTO> getAllCourses() {
        return courseService.read();
    }

    @PostMapping
    public ResponseEntity<?> addCourses (@RequestBody CourseDTO course){
        log.info("CourseController: course");
        CourseDTO resource = courseService.create(course);
        return ResponseEntity.ok(resource);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse (@PathVariable ("id") int id){
        log.info("CourseController:  delete courses");
        courseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> userByID(@PathVariable ("id") int id) {
        log.info("CourseController:  list course");
        CourseDTO resource = courseService.getById(id);
        return ResponseEntity.ok(resource);
    }

}

