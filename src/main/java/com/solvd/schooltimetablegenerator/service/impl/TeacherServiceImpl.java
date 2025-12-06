package com.solvd.schooltimetablegenerator.service.impl;

import com.solvd.schooltimetablegenerator.domain.Teacher;
import com.solvd.schooltimetablegenerator.persistence.TeacherRepository;
import com.solvd.schooltimetablegenerator.persistence.repositoryImp.TeacherRepositoryImp;
import com.solvd.schooltimetablegenerator.service.TeacherService;

import java.util.List;
import java.util.Optional;

public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository = new TeacherRepositoryImp();

    @Override
    public void create(Teacher teacher, Long subjectId) {
        teacherRepository.insert(teacher, subjectId);
    }

    @Override
    public void update(Teacher teacher) {
        teacherRepository.update(teacher);
    }

    @Override
    public void delete(Long id) {
        teacherRepository.delete(id);
    }

    @Override
    public Optional<Teacher> findById(Long id) {
        return teacherRepository.selectById(id);
    }

    @Override
    public List<Teacher> findAll() {
        return teacherRepository.selectAll();
    }
}