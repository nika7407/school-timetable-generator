package com.solvd.schooltimetablegenerator.service;

import com.solvd.schooltimetablegenerator.domain.Subject;

import java.util.List;
import java.util.Optional;

public interface SubjectService {
    void create(Subject subject);

    void update(Subject subject);

    void delete(Long id);

    Optional<Subject> findById(Long id);

    List<Subject> findAll();
}