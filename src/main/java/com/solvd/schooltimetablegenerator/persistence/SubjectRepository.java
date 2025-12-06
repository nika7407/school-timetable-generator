package com.solvd.schooltimetablegenerator.persistence;

import com.solvd.schooltimetablegenerator.domain.Subject;

import java.util.List;
import java.util.Optional;

public interface SubjectRepository {

    void insert(Subject subject);

    void update(Subject subject);

    void delete(Long id);

    Optional<Subject> selectById(Long id);

    List<Subject> selectAll();
}
