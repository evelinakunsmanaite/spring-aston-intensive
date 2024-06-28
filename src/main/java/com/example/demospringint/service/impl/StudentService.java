package com.example.demospringint.service.impl;

import com.example.demospringint.dtoModel.StudentDTO;
import com.example.demospringint.model.Student;
import com.example.demospringint.repository.StudentRepository;
import com.example.demospringint.service.ServicesInt;
import com.example.demospringint.util.StudentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService implements ServicesInt<StudentDTO> {
    private final StudentRepository studentRepository;

    public StudentDTO create(StudentDTO studentDTO){
        Student student = StudentMapper.studentDTOToStudent(studentDTO);
        return StudentMapper.studentToStudentDTO(studentRepository.save(student));
    }

    public List<StudentDTO> read(){
        List<Student> students = studentRepository.findAll();
        return students.stream().map(StudentMapper::studentToStudentDTO).collect(Collectors.toList());
    }

    public void deleteById(int id){
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student not found");
        }
        studentRepository.deleteById(id);
    }

    public StudentDTO getById(int id){
        return StudentMapper.studentToStudentDTO(studentRepository.findById(id).get());
    }

    public void update(int id, StudentDTO studentUpdate){
        Student existingStudent = StudentMapper.studentDTOToStudent(getById(id));
        BeanUtils.copyProperties(studentUpdate, existingStudent, "id");
        studentRepository.save(existingStudent);
    }

}
