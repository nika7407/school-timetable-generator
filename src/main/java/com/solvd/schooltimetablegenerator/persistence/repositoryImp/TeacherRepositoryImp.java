package com.solvd.schooltimetablegenerator.persistence.repositoryImp;

import com.solvd.schooltimetablegenerator.domain.Teacher;
import com.solvd.schooltimetablegenerator.persistence.TeacherRepository;
import com.solvd.schooltimetablegenerator.persistence.connection.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;
import java.util.Optional;

public class TeacherRepositoryImp implements TeacherRepository {

    private static final SqlSessionFactory sqlSessionFactory = MyBatisUtil.getSession();

    @Override
    public void insert(Teacher teacher, Long subjectId) {

        try (SqlSession session = sqlSessionFactory.openSession()) {
            TeacherRepository teacherRepository = session.getMapper(TeacherRepository.class);
            teacherRepository.insert(teacher, subjectId);
            session.commit();
        }

    }

    @Override
    public void update(Teacher teacher) {

        try (SqlSession session = sqlSessionFactory.openSession()) {
            TeacherRepository teacherRepository = session.getMapper(TeacherRepository.class);
            teacherRepository.update(teacher);
            session.commit();
        }
    }

    @Override
    public void delete(Long id) {

        try (SqlSession session = sqlSessionFactory.openSession()) {
            TeacherRepository teacherRepository = session.getMapper(TeacherRepository.class);
            teacherRepository.delete(id);
            session.commit();
        }

    }

    @Override
    public Optional<Teacher> selectById(Long id) {

        try (SqlSession session = sqlSessionFactory.openSession()) {
            TeacherRepository teacherRepository = session.getMapper(TeacherRepository.class);
            return teacherRepository.selectById(id);
        }
    }

    @Override
    public List<Teacher> selectAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            TeacherRepository teacherRepository = session.getMapper(TeacherRepository.class);
            return teacherRepository.selectAll();
        }
    }
}
