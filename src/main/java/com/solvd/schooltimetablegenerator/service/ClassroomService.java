package com.solvd.schooltimetablegenerator.service;

import com.solvd.schooltimetablegenerator.domain.Classroom;

import java.util.List;
import java.util.Optional;

public interface ClassroomService {

    void create(Classroom classroom);

    void update(Classroom classroom);

    void delete(Long id);

    Optional<Classroom> findById(Long id);

    List<Classroom> findAll();
}