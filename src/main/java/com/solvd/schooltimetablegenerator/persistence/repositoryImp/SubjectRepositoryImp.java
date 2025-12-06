package com.solvd.schooltimetablegenerator.persistence.repositoryImp;

import com.solvd.schooltimetablegenerator.domain.Subject;
import com.solvd.schooltimetablegenerator.persistence.SubjectRepository;
import com.solvd.schooltimetablegenerator.persistence.connection.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;
import java.util.Optional;

public class SubjectRepositoryImp implements SubjectRepository {

    private static final SqlSessionFactory sqlSessionFactory = MyBatisUtil.getSession();

    @Override
    public void insert(Subject subject) {

        try (SqlSession session = sqlSessionFactory.openSession()) {
            SubjectRepository subjectRepository = session.getMapper(SubjectRepository.class);
            subjectRepository.insert(subject);
            session.commit();
        }
    }

    @Override
    public void update(Subject subject) {

        try (SqlSession session = sqlSessionFactory.openSession()) {
            SubjectRepository subjectRepository = session.getMapper(SubjectRepository.class);
            subjectRepository.update(subject);
            session.commit();
        }
    }

    @Override
    public void delete(Long id) {

        try (SqlSession session = sqlSessionFactory.openSession()) {
            SubjectRepository subjectRepository = session.getMapper(SubjectRepository.class);
            subjectRepository.delete(id);
            session.commit();
        }
    }

    @Override
    public Optional<Subject> selectById(Long id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            SubjectRepository subjectRepository = session.getMapper(SubjectRepository.class);
            return subjectRepository.selectById(id);

        }
    }

    @Override
    public List<Subject> selectAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            SubjectRepository subjectRepository = session.getMapper(SubjectRepository.class);
            return subjectRepository.selectAll();
        }
    }
}
