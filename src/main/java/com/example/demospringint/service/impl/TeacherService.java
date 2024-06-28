package com.example.demospringint.service.impl;

import com.example.demospringint.dtoModel.TeacherDTO;
import com.example.demospringint.model.Teacher;
import com.example.demospringint.repository.TeacherRepository;
import com.example.demospringint.service.ServicesInt;
import com.example.demospringint.util.TeacherMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class TeacherService implements ServicesInt<TeacherDTO> {
    private final TeacherRepository teacherRepository;

    public TeacherDTO create(TeacherDTO teacherDTO){
        Teacher teacher = TeacherMapper.teacherDTOToTeacher(teacherDTO);
        return TeacherMapper.teacherToTeacherDTO(teacherRepository.save(teacher));
    }

    public List<TeacherDTO> read(){
        List<Teacher> teachers = teacherRepository.findAll();
        return teachers.stream().map(TeacherMapper::teacherToTeacherDTO).collect(Collectors.toList());
    }

    public void deleteById(int id){
        if (!teacherRepository.existsById(id)) {
            throw new RuntimeException("Teacher not found");
        }
        teacherRepository.deleteById(id);
    }

    public TeacherDTO getById(int id){
        return TeacherMapper.teacherToTeacherDTO(teacherRepository.findById(id).get());
    }

    public void update(int id, TeacherDTO teacherUpdate){
        Teacher existingTeacher = TeacherMapper.teacherDTOToTeacher(getById(id));
        BeanUtils.copyProperties(teacherUpdate, existingTeacher, "id");
        teacherRepository.save(existingTeacher);
    }
}
