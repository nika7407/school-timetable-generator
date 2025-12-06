package com.solvd.schooltimetablegenerator.persistence;

import com.solvd.schooltimetablegenerator.domain.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

public interface TeacherRepository {

    void insert(@Param("teacher") Teacher teacher,
                @Param("subjectId") Long subjectId);

    void update(Teacher teacher);

    void delete(Long id);

    Optional<Teacher> selectById(Long id);

    List<Teacher> selectAll();
}
