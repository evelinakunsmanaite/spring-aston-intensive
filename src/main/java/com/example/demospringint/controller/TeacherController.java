package com.example.demospringint.controller;

import com.example.demospringint.dtoModel.TeacherDTO;
import com.example.demospringint.service.impl.TeacherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/teachers")
public class TeacherController {
    private final TeacherService teacherService;

    @GetMapping("/")
    public List<TeacherDTO> getAllTeachers() {
        return teacherService.read();
    }

    @PostMapping
    public ResponseEntity<?> addTeachers (@RequestBody TeacherDTO teacher){
        log.info("TeacherController: teacher");
        TeacherDTO resource = teacherService.create(teacher);
        return ResponseEntity.ok(resource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeachers (@PathVariable ("id") int id){
        log.info("TeacherController:  delete teacher");
        teacherService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> userByID(@PathVariable ("id") int id) {
        log.info("TeacherController:  list teacher");
        TeacherDTO resource = teacherService.getById(id);
        return ResponseEntity.ok(resource);
    }

}
