package com.solvd.schooltimetablegenerator;

import com.solvd.schooltimetablegenerator.domain.Classroom;
import com.solvd.schooltimetablegenerator.domain.Subject;
import com.solvd.schooltimetablegenerator.domain.Teacher;
import com.solvd.schooltimetablegenerator.domain.Timetable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        List<Teacher> teachers = new ArrayList<>();
        teachers.add(new Teacher(1L, "Teacher1", "One", "teacher1@school.com"));
        teachers.add(new Teacher(2L, "Teacher2", "Two", "teacher2@school.com"));
        teachers.add(new Teacher(3L, "Teacher3", "Three", "teacher3@school.com"));
        teachers.add(new Teacher(4L, "Teacher4", "Four", "teacher4@school.com"));
        teachers.add(new Teacher(5L, "Teacher5", "Five", "teacher5@school.com"));
        teachers.add(new Teacher(6L, "Teacher6", "Six", "teacher6@school.com"));
        teachers.add(new Teacher(7L, "Teacher7", "Seven", "teacher7@school.com"));
        teachers.add(new Teacher(8L, "Teacher8", "Eight", "teacher8@school.com"));

        List<Subject> subjects = new ArrayList<>();
        subjects.add(new Subject(1L, "Mathematics", "Mathematics"));
        subjects.add(new Subject(2L, "Physics", "Physics"));
        subjects.add(new Subject(3L, "Chemistry", "Chemistry"));
        subjects.add(new Subject(4L, "Biology", "Biological"));
        subjects.add(new Subject(5L, "History", "history"));
        subjects.add(new Subject(6L, "Geography", "sciences"));
        subjects.add(new Subject(7L, "English", "English"));
        subjects.add(new Subject(8L, "Computer Science", "Programming"));

        List<Classroom> classrooms = new ArrayList<>();
        classrooms.add(new Classroom(1L, 101, false));
        classrooms.add(new Classroom(2L, 102, false));
        classrooms.add(new Classroom(3L, 201, true));
        classrooms.add(new Classroom(4L, 202, true));
        classrooms.add(new Classroom(5L, 301, false));
        classrooms.add(new Classroom(6L, 302, false));
        classrooms.add(new Classroom(7L, 401, true));

//        TimetableGeneticAlgorithm ga = new TimetableGeneticAlgorithm(
//                50,
//                0.1,
//                500,
//                teachers,
//                subjects,
//                classrooms,
//                8,
//                5
//        );
//
//       List<Timetable> timetable = ga.generateTimetable();

//        for (Timetable timetableElement : timetable) {
//            log.info(timetableElement.toString());
//        }

        log.info("Generated {} teachers", teachers.size());
        log.info("Generated {} subjects", subjects.size());
        log.info("Generated {} classrooms", classrooms.size());
    }
}