package com.example.demospringint.controller;

import com.example.demospringint.dtoModel.StudentDTO;
import com.example.demospringint.service.impl.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/")
    public List<StudentDTO> getAllStudent() {
        return studentService.read();
    }

    @PostMapping
    public ResponseEntity<?> addStudent (@RequestBody StudentDTO student){
        log.info("StudentController: student");
        StudentDTO resource = studentService.create(student);
        return ResponseEntity.ok(resource);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent (@PathVariable ("id") int id){
        log.info("StudentController:  delete student");
        studentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> userByID(@PathVariable ("id") int id) {
        log.info("StudentController:  list student");
        StudentDTO resource = studentService.getById(id);
        return ResponseEntity.ok(resource);
    }
}
