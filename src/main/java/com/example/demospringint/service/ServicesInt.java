package com.example.demospringint.service;

import com.example.demospringint.model.Course;
import org.springframework.beans.BeanUtils;

import java.util.List;

public interface ServicesInt<T> {

    T create(T t);

    List<T> read();

    T getById(int id);

    void deleteById(int id);

    void update(int id, T t);
}
