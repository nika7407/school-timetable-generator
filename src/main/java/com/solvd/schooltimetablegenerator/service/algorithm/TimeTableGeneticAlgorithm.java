package com.solvd.schooltimetablegenerator.service.algorithm;

import com.solvd.schooltimetablegenerator.domain.Classroom;
import com.solvd.schooltimetablegenerator.domain.Subject;
import com.solvd.schooltimetablegenerator.domain.Teacher;
import com.solvd.schooltimetablegenerator.domain.Timetable;
import com.solvd.schooltimetablegenerator.service.algorithm.dna.TimetableChromosome;

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
    private static int dailySubjects = 5;
    private static Random random = new Random();
    private static LocalDate date = LocalDate.now();

    public TimeTableGeneticAlgorithm() {
    }

    public TimeTableGeneticAlgorithm(List<Teacher> teachers, List<Subject> subjects, List<Classroom> classrooms) {
        this.teachers = teachers;
        this.subjects = subjects;
        this.classrooms = classrooms;
    }

    public List<List<Timetable>> generateTimetable() {
        List<List<Timetable>> list = new ArrayList<>();

        LocalDate originalDate = date;
        for (int i = 0; i < daysInAWeek; i++) {
            date = originalDate.plusDays(i);
            list.add(generateDay());
        }
        date = originalDate;
        return list;
    }

    private List<Timetable> generateTimetablePlain() {
        List<List<Timetable>> NestedList = generateTimetable();
        List<Timetable> timetablesToReturn = new ArrayList<>();

        for (List<Timetable> timetableDay : NestedList) {
            timetablesToReturn.addAll(timetableDay);
        }
        return timetablesToReturn;

    }

    private List<Timetable> generateDay() {
        List<Timetable> timetableList = new ArrayList<>(dailySubjects);

        for (int i = 0; i < dailySubjects; i++) {
            Timetable currentTimetable = generateSingleTimetable();
            currentTimetable.setPeriodNumber(i + 1);

            currentTimetable.setDate(date);
            DayOfWeek day = date.getDayOfWeek();
            currentTimetable.setDayOfWeek(day);

            timetableList.add(currentTimetable);
        }
        return timetableList;
    }

    private Timetable generateSingleTimetable() {
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

    private List<TimetableChromosome> initPopulation(int populationSize) {
       List<TimetableChromosome> chromosomeList = new ArrayList<>();
        for (int i = 0; i<populationSize; i++){
            chromosomeList.add(new TimetableChromosome(generateTimetablePlain()));
        }
        return chromosomeList;
    }

}
