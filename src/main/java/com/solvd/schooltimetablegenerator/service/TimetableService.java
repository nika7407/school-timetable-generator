package com.solvd.schooltimetablegenerator.service;

import com.solvd.schooltimetablegenerator.domain.Timetable;

import java.util.List;
import java.util.Optional;

public interface TimetableService {

    void create(List<Timetable> weeklyTimeTable);

    void update(Timetable timetable);

    void delete(Long id);

    Optional<Timetable> findById(Long id);

    List<Timetable> findAll();
}