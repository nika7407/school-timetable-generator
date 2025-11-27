package com.solvd.schooltimetablegenerator.service.algorithm;

import com.solvd.schooltimetablegenerator.domain.Classroom;
import com.solvd.schooltimetablegenerator.domain.Subject;
import com.solvd.schooltimetablegenerator.domain.Teacher;
import com.solvd.schooltimetablegenerator.domain.Timetable;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TimeTableGeneticAlgorithm {
    private List<Teacher> teachers;
    private List<Subject> subjects;
    private List<Classroom> classrooms;
    private static int daysInAWeek = 5;
    private static Random random = new Random();
    private static LocalDate date = LocalDate.now();


    public TimeTableGeneticAlgorithm() {

    }

    public TimeTableGeneticAlgorithm(List<Teacher> teachers, List<Subject> subjects, List<Classroom> classrooms) {
        this.teachers = teachers;
        this.subjects = subjects;
        this.classrooms = classrooms;
    }

    public List<Timetable> generateTimetable() {
        List<Timetable> list = new ArrayList<>();

             list = chromosome();
        return list;
    }

    public List<Timetable> chromosome() {
        List<Timetable> timetableList = new ArrayList<>(daysInAWeek);

        for (int i = 0; i < daysInAWeek; i++) {
            Timetable currentTimetable = randomGene();
            currentTimetable.setDate(date.plusDays(i));
            currentTimetable.setPeriodNumber(i+1);
            DayOfWeek day = date.plusDays(i).getDayOfWeek();
            currentTimetable.setDayOfWeek(day);
            timetableList.add(i, currentTimetable);
        }
        return timetableList;
    }

    public Timetable randomGene() {
        Timetable timetable = new Timetable();

        Subject subject1 = randomListElement(subjects);
        timetable.setSubject(subject1);

        List<Teacher> listOfTeachersWithSubject1 = teachers.stream()
                .filter(teacher -> teacher.getSubjects().contains(subject1)).toList();
        timetable.setTeacher(randomListElement(listOfTeachersWithSubject1));

        List<Classroom> listOfSuitableClasrooms = classrooms.stream()
                .filter(classroom -> classroom.getSubjects().contains(subject1)).toList();
        timetable.setClassroom(randomListElement(listOfSuitableClasrooms));

        return timetable;
    }

    private <T> T randomListElement(List<T> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        int randomIndex = random.nextInt(list.size());
        return list.get(randomIndex);
    }


}
