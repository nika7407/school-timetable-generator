package com.solvd.schooltimetablegenerator.service.impl;

import com.solvd.schooltimetablegenerator.domain.Subject;
import com.solvd.schooltimetablegenerator.persistence.SubjectRepository;
import com.solvd.schooltimetablegenerator.persistence.repositoryImp.SubjectRepositoryImp;
import com.solvd.schooltimetablegenerator.service.SubjectService;

import java.util.List;
import java.util.Optional;

public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository = new SubjectRepositoryImp();

    @Override
    public void create(Subject subject) {
        subjectRepository.insert(subject);
    }

    @Override
    public void update(Subject subject) {
        subjectRepository.update(subject);
    }

    @Override
    public void delete(Long id) {
        subjectRepository.delete(id);
    }

    @Override
    public Optional<Subject> findById(Long id) {
        return subjectRepository.selectById(id);
    }

    @Override
    public List<Subject> findAll() {
        return subjectRepository.selectAll();
    }
}