package com.solvd.schooltimetablegenerator;

import com.solvd.schooltimetablegenerator.domain.Classroom;
import com.solvd.schooltimetablegenerator.domain.Subject;
import com.solvd.schooltimetablegenerator.domain.Teacher;
import com.solvd.schooltimetablegenerator.domain.Timetable;
import com.solvd.schooltimetablegenerator.service.algorithm.TimeTableGeneticAlgorithm;
import com.solvd.schooltimetablegenerator.service.controller.Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        List<Teacher> teachers = new ArrayList<>();
        teachers.add(new Teacher(1L, "Michael", "Smith", "michael.smith@school.com"));
        teachers.add(new Teacher(2L, "Anna", "Brown", "anna.brown@school.com"));
        teachers.add(new Teacher(3L, "Daniel", "Johnson", "daniel.johnson@school.com"));
        teachers.add(new Teacher(4L, "Laura", "Davis", "laura.davis@school.com"));
        teachers.add(new Teacher(5L, "Steven", "Miller", "steven.miller@school.com"));
        teachers.add(new Teacher(6L, "Emily", "Wilson", "emily.wilson@school.com"));
        teachers.add(new Teacher(7L, "David", "Taylor", "david.taylor@school.com"));
        teachers.add(new Teacher(8L, "Sarah", "Anderson", "sarah.anderson@school.com"));
        teachers.add(new Teacher(9L, "Michael", "Serra", "michael.serra@school.com"));

        Subject math = new Subject(1L, "Mathematics", "Mathematics");
        Subject physics = new Subject(2L, "Physics", "Physics");
        Subject chemistry = new Subject(3L, "Chemistry", "Chemistry");
        Subject biology = new Subject(4L, "Biology", "Biology");
        Subject history = new Subject(5L, "History", "History");
        Subject geography = new Subject(6L, "Geography", "Geography");
        Subject english = new Subject(7L, "English", "English");
        Subject computerScience = new Subject(8L, "Computer Science", "Computer Science");
        Subject pe = new Subject(9L, "PE", "Physical Education");

        math.addTeacher(teachers.get(1));
        physics.addTeacher(teachers.get(2));
        chemistry.addTeacher(teachers.get(4));
        biology.addTeacher(teachers.get(5));
        history.addTeacher(teachers.get(6));
        geography.addTeacher(teachers.get(7));
        english.addTeacher(teachers.get(8));
        computerScience.addTeacher(teachers.get(3));
        pe.addTeacher(teachers.get(0));


        List<Subject> subjects = List.of(math, physics, chemistry, biology, history, geography, english, computerScience, pe);


        List<Classroom> classrooms = new ArrayList<>();
        classrooms.add(new Classroom(1L, 101, false));
        classrooms.add(new Classroom(2L, 102, false));
        classrooms.add(new Classroom(3L, 201, true));


       // printWeeklyGrid(timetableList);

        Controller.testController(subjects,classrooms, teachers);


      /*  ClassRoomRepositoryImp classRoomRepositoryImp= new ClassRoomRepositoryImp();
        Classroom newClassroom = new Classroom();
        newClassroom.setNumber(304);
        newClassroom.setId(6L);
        classRoomRepositoryImp.update(newClassroom);
        System.out.println(classRoomRepositoryImp.selectAll());

       */

//        SubjectRepository subjectRepository= new SubjectRepositoryImp();
//
//        System.out.println(subjectRepository.selectById(2L));
//        System.out.println(subjectRepository.selectAll());




    }
}
