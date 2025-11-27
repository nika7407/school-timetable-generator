package com.solvd.schooltimetablegenerator;

import com.solvd.schooltimetablegenerator.domain.Classroom;
import com.solvd.schooltimetablegenerator.domain.Subject;
import com.solvd.schooltimetablegenerator.domain.Teacher;
import com.solvd.schooltimetablegenerator.domain.Timetable;
import com.solvd.schooltimetablegenerator.service.algorithm.TimeTableGeneticAlgorithm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        List<Teacher> teachers = new ArrayList<>();
        Teacher teacher1 = new Teacher(1L, "Teacher1", "One", "teacher1@school.com");
        Teacher teacher2 = new Teacher(2L, "Teacher2", "Two", "teacher2@school.com");
        Teacher teacher3 = new Teacher(3L, "Teacher3", "Three", "teacher3@school.com");
        Teacher teacher4 = new Teacher(4L, "Teacher4", "Four", "teacher4@school.com");
        Teacher teacher5 = new Teacher(5L, "Teacher5", "Five", "teacher5@school.com");
        Teacher teacher6 = new Teacher(6L, "Teacher6", "Six", "teacher6@school.com");
        Teacher teacher7 = new Teacher(7L, "Teacher7", "Seven", "teacher7@school.com");
        Teacher teacher8 = new Teacher(8L, "Teacher8", "Eight", "teacher8@school.com");

        List<Subject> subjects = new ArrayList<>();
        Subject math = new Subject(1L, "Mathematics", "Mathematics");
        Subject physics = new Subject(2L, "Physics", "Physics");
        Subject chemistry = new Subject(3L, "Chemistry", "Chemistry");
        Subject biology = new Subject(4L, "Biology", "Biological");
        Subject history = new Subject(5L, "History", "history");
        Subject geography = new Subject(6L, "Geography", "sciences");
        Subject english = new Subject(7L, "English", "English");
        Subject computerScience = new Subject(8L, "Computer Science", "Programming");
        Subject pe = new Subject(9L, "PE", "Physical Education");

        List<Classroom> classrooms = new ArrayList<>();
        Classroom room101 = new Classroom(1L, 101, false);
        Classroom room102 = new Classroom(2L, 102, false);
        Classroom room201 = new Classroom(3L, 201, true);
        Classroom room202 = new Classroom(4L, 202, true);
        Classroom room301 = new Classroom(5L, 301, false);
        Classroom room302 = new Classroom(6L, 302, false);
        Classroom room401 = new Classroom(7L, 401, true);
        Classroom room402 = new Classroom(8L, 402, false);

        teacher1.addSubject(math);
        teacher1.addSubject(physics);
        teacher2.addSubject(physics);
        teacher2.addSubject(chemistry);
        teacher3.addSubject(chemistry);
        teacher3.addSubject(biology);
        teacher4.addSubject(biology);
        teacher4.addSubject(history);
        teacher5.addSubject(history);
        teacher5.addSubject(geography);
        teacher6.addSubject(geography);
        teacher6.addSubject(english);
        teacher7.addSubject(english);
        teacher7.addSubject(computerScience);
        teacher8.addSubject(computerScience);
        teacher8.addSubject(pe);
        teacher8.addSubject(math);

        math.addTeacher(teacher1);
        math.addTeacher(teacher8);
        physics.addTeacher(teacher1);
        physics.addTeacher(teacher2);
        chemistry.addTeacher(teacher2);
        chemistry.addTeacher(teacher3);
        biology.addTeacher(teacher3);
        biology.addTeacher(teacher4);
        history.addTeacher(teacher4);
        history.addTeacher(teacher5);
        geography.addTeacher(teacher5);
        geography.addTeacher(teacher6);
        english.addTeacher(teacher6);
        english.addTeacher(teacher7);
        computerScience.addTeacher(teacher7);
        computerScience.addTeacher(teacher8);
        pe.addTeacher(teacher8);

        math.addClassroom(room101);
        math.addClassroom(room102);
        math.addClassroom(room301);
        math.addClassroom(room302);
        math.addClassroom(room402);

        physics.addClassroom(room201);
        physics.addClassroom(room202);

        chemistry.addClassroom(room201);
        chemistry.addClassroom(room202);

        biology.addClassroom(room201);
        biology.addClassroom(room202);

        history.addClassroom(room101);
        history.addClassroom(room102);
        history.addClassroom(room301);
        history.addClassroom(room302);
        history.addClassroom(room402);

        geography.addClassroom(room101);
        geography.addClassroom(room102);
        geography.addClassroom(room301);
        geography.addClassroom(room302);
        geography.addClassroom(room402);

        english.addClassroom(room101);
        english.addClassroom(room102);
        english.addClassroom(room301);
        english.addClassroom(room302);
        english.addClassroom(room402);

        computerScience.addClassroom(room401);

        pe.addClassroom(room101);
        pe.addClassroom(room102);
        pe.addClassroom(room301);
        pe.addClassroom(room302);
        pe.addClassroom(room402);

        room101.addSubject(math);
        room101.addSubject(history);
        room101.addSubject(geography);
        room101.addSubject(english);
        room101.addSubject(pe);

        room102.addSubject(math);
        room102.addSubject(history);
        room102.addSubject(geography);
        room102.addSubject(english);
        room102.addSubject(pe);

        room201.addSubject(physics);
        room201.addSubject(chemistry);
        room201.addSubject(biology);

        room202.addSubject(physics);
        room202.addSubject(chemistry);
        room202.addSubject(biology);

        room301.addSubject(math);
        room301.addSubject(history);
        room301.addSubject(geography);
        room301.addSubject(english);
        room301.addSubject(pe);

        room302.addSubject(math);
        room302.addSubject(history);
        room302.addSubject(geography);
        room302.addSubject(english);
        room302.addSubject(pe);

        room401.addSubject(computerScience);

        room402.addSubject(math);
        room402.addSubject(history);
        room402.addSubject(geography);
        room402.addSubject(english);
        room402.addSubject(pe);

        teachers.add(teacher1);
        teachers.add(teacher2);
        teachers.add(teacher3);
        teachers.add(teacher4);
        teachers.add(teacher5);
        teachers.add(teacher6);
        teachers.add(teacher7);
        teachers.add(teacher8);

        subjects.add(math);
        subjects.add(physics);
        subjects.add(chemistry);
        subjects.add(biology);
        subjects.add(history);
        subjects.add(geography);
        subjects.add(english);
        subjects.add(computerScience);
        subjects.add(pe);

        classrooms.add(room101);
        classrooms.add(room102);
        classrooms.add(room201);
        classrooms.add(room202);
        classrooms.add(room301);
        classrooms.add(room302);
        classrooms.add(room401);
        classrooms.add(room402);

        TimeTableGeneticAlgorithm ga = new TimeTableGeneticAlgorithm(teachers, subjects, classrooms);
        List<List<Timetable>> timetable = ga.generateTimetable();

        for (List<Timetable> timetableDay : timetable) {
          for (Timetable timetable1 : timetableDay){
              log.info(timetable1.toString());
          }
          log.info("------");
        }

        log.info("Generated {} teachers", teachers.size());
        log.info("Generated {} subjects", subjects.size());
        log.info("Generated {} classrooms", classrooms.size());
    }
}