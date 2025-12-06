package com.solvd.schooltimetablegenerator.service;

import com.solvd.schooltimetablegenerator.domain.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherService {
    void create(Teacher teacher, Long subjectId);
    void update(Teacher teacher);
    void delete(Long id);
    Optional<Teacher> findById(Long id);
    List<Teacher> findAll();
}