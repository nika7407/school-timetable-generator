package com.solvd.schooltimetablegenerator.persistence;

import com.solvd.schooltimetablegenerator.domain.Timetable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TimeTableRepository {

    void insert(Timetable timetable);

    void update(Timetable timetable);

    void delete(Long id);

    Optional<Timetable> selectById(Long id);

    List<Timetable> selectAll();

    List<Timetable> selectByDate(LocalDate date);
}