package com.solvd.schooltimetablegenerator.service.impl;

import com.solvd.schooltimetablegenerator.domain.Classroom;
import com.solvd.schooltimetablegenerator.persistence.ClassRoomRepository;
import com.solvd.schooltimetablegenerator.persistence.repositoryImp.ClassRoomRepositoryImp;
import com.solvd.schooltimetablegenerator.service.ClassroomService;

import java.util.List;
import java.util.Optional;

public class ClassroomServiceImpl implements ClassroomService {

    private final ClassRoomRepository classRoomRepository = new ClassRoomRepositoryImp();

    @Override
    public void create(Classroom classroom) {
        classRoomRepository.insert(classroom);
    }

    @Override
    public void update(Classroom classroom) {
        classRoomRepository.update(classroom);
    }

    @Override
    public void delete(Long id) {
        classRoomRepository.delete(id);
    }

    @Override
    public Optional<Classroom> findById(Long id) {
        return classRoomRepository.selectById(id);
    }

    @Override
    public List<Classroom> findAll() {
        return classRoomRepository.selectAll();
    }
}