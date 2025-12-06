package com.solvd.schooltimetablegenerator.persistence.repositoryImp;

import com.solvd.schooltimetablegenerator.domain.Timetable;
import com.solvd.schooltimetablegenerator.persistence.TimeTableRepository;
import com.solvd.schooltimetablegenerator.persistence.connection.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class TimeTableRepositoryImp implements TimeTableRepository {

    private final SqlSessionFactory sqlSessionFactory;

    public TimeTableRepositoryImp() {
        this.sqlSessionFactory = MyBatisUtil.getSession();
    }

    @Override
    public void insert(Timetable timetable) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            TimeTableRepository mapper = session.getMapper(TimeTableRepository.class);
            mapper.insert(timetable);
            session.commit();
        }
    }

    @Override
    public void update(Timetable timetable) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            TimeTableRepository mapper = session.getMapper(TimeTableRepository.class);
            mapper.update(timetable);
            session.commit();
        }
    }

    @Override
    public void delete(Long id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            TimeTableRepository mapper = session.getMapper(TimeTableRepository.class);
            mapper.delete(id);
            session.commit();
        }
    }

    @Override
    public Optional<Timetable> selectById(Long id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            TimeTableRepository mapper = session.getMapper(TimeTableRepository.class);
            return mapper.selectById(id);
        }
    }

    @Override
    public List<Timetable> selectAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            TimeTableRepository mapper = session.getMapper(TimeTableRepository.class);
            return mapper.selectAll();
        }
    }

    public List<Timetable> selectByDate(LocalDate date) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            TimeTableRepository mapper = session.getMapper(TimeTableRepository.class);
            return mapper.selectByDate(date);
        }
    }
}