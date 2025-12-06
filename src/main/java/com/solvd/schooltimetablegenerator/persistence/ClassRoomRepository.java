package com.solvd.schooltimetablegenerator.persistence;

import com.solvd.schooltimetablegenerator.domain.Classroom;

import java.util.List;
import java.util.Optional;

public interface ClassRoomRepository {

    void insert(Classroom classroom);

    void update(Classroom classroom);

    void delete(Long id);

    Optional<Classroom> selectById(Long id);

    List<Classroom> selectAll();
}
