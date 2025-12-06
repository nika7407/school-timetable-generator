package com.solvd.schooltimetablegenerator.persistence;

import com.solvd.schooltimetablegenerator.domain.Timetable;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

public interface TimeTableRepository {

    void insert(@Param("timetable") Timetable timetable,
                @Param("classRoomId") Long classRoomId,
                @Param("teacherId")Long teacherId,
                @Param("subjectId") Long subjectId);

    void update(Timetable timetable);

    void delete( Long id);

    Optional<Timetable> selectById(Long id);

    List<Timetable> selectAll();

}
