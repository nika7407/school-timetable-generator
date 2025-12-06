package com.solvd.schooltimetablegenerator.service.impl;

import com.solvd.schooltimetablegenerator.domain.Timetable;
import com.solvd.schooltimetablegenerator.persistence.TimeTableRepository;
import com.solvd.schooltimetablegenerator.persistence.repositoryImp.TimeTableRepositoryImp;
import com.solvd.schooltimetablegenerator.service.TimetableService;

import java.util.List;
import java.util.Optional;

public class TimetableServiceImpl implements TimetableService {

    private final TimeTableRepository timeTableRepository = new TimeTableRepositoryImp();

    @Override
    public void create(List<Timetable> weeklyTimeTable) {
        for (Timetable timetable : weeklyTimeTable) {
            timeTableRepository.insert(timetable);
        }
    }

    @Override
    public void update(Timetable timetable) {
        timeTableRepository.update(timetable);
    }

    @Override
    public void delete(Long id) {
        timeTableRepository.delete(id);
    }

    @Override
    public Optional<Timetable> findById(Long id) {
        return timeTableRepository.selectById(id);
    }

    @Override
    public List<Timetable> findAll() {
        return timeTableRepository.selectAll();
    }
}