package com.solvd.schooltimetablegenerator.persistence.repositoryImp;

import com.solvd.schooltimetablegenerator.domain.Timetable;
import com.solvd.schooltimetablegenerator.persistence.TimeTableRepository;

import java.util.List;
import java.util.Optional;

public class TimeTableRepositoryImp implements TimeTableRepository {

    @Override
    public void insert(Timetable timetable, Long classRoomId, Long teacherId, Long subjectId) {

    }

    @Override
    public void update(Timetable timetable) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Optional<Timetable> selectById(Long id) {
        return null;
    }

    @Override
    public List<Timetable> selectAll() {
        return List.of();
    }
}
