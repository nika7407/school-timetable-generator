package com.solvd.schooltimetablegenerator.persistence.repositoryImp;

import com.solvd.schooltimetablegenerator.domain.Classroom;
import com.solvd.schooltimetablegenerator.persistence.ClassRoomRepository;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;
import java.util.Optional;

public class ClassRoomRepositoryImp implements ClassRoomRepository {

    private final static SqlSessionFactory sqlSessionFactory = MyBatisUtil.getSession();

    @Override
    public void insert(Classroom classroom) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ClassRoomRepository classRoomRepository = session.getMapper(ClassRoomRepository.class);
            classRoomRepository.insert(classroom);
            session.commit();
        }


    }

    @Override
    public void update(Classroom classroom) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ClassRoomRepository classRoomRepository = session.getMapper(ClassRoomRepository.class);
            classRoomRepository.update(classroom);
            session.commit();
        }
    }

    @Override
    public void delete(Long id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ClassRoomRepository classRoomRepository = session.getMapper(ClassRoomRepository.class);
            classRoomRepository.delete(id);
            session.commit();
        }

    }

    @Override
    public Optional<Classroom> selectById(Long id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ClassRoomRepository classRoomRepository = session.getMapper(ClassRoomRepository.class);
            return classRoomRepository.selectById(id);
        }
    }

    @Override
    public List<Classroom> selectAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ClassRoomRepository classRoomRepository = session.getMapper(ClassRoomRepository.class);
            return classRoomRepository.selectAll();
        }
    }
}
