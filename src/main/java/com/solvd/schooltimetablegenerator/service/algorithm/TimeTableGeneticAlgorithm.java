package com.solvd.schooltimetablegenerator.service.algorithm;

import com.solvd.schooltimetablegenerator.Main;
import com.solvd.schooltimetablegenerator.domain.Classroom;
import com.solvd.schooltimetablegenerator.domain.Subject;
import com.solvd.schooltimetablegenerator.domain.Teacher;
import com.solvd.schooltimetablegenerator.domain.Timetable;
import com.solvd.schooltimetablegenerator.service.algorithm.dna.TimetableChromosome;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

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

    // generating full timetable for each class for each daysInAWeek and assigning class
    // this essentially returns randomly generated timetable
    public List<Timetable> generateTimetableForAWeek() {
        List<Timetable> list = new ArrayList<>();
        LocalDate originalDate = date;
        for (Classroom classroom : classrooms) {
            for (int i = 0; i < daysInAWeek; i++) {
                date = originalDate.plusDays(i);
                List<Timetable> timetableForADaySingularClass = generateDay();
                timetableForADaySingularClass.forEach(timetable -> timetable.setClassroom(classroom));
                list.addAll(timetableForADaySingularClass);
            }
        }
        date = originalDate;
        return list;
    }

    //generating timetable for a day without class
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

    // generates singular timetable object without time
    private Timetable generateSingleTimetable() {
        Timetable timetable = new Timetable();


        Subject subject1 = randomListElement(subjects);
        timetable.setSubject(subject1);

        List<Teacher> listOfTeachersWithSubject1 = teachers.stream()
                .filter(teacher -> teacher.getSubjects().contains(subject1)).toList();
        timetable.setTeacher(randomListElement(listOfTeachersWithSubject1));


        return timetable;
    }

    //util class to get random element of inputted list
    private <T> T randomListElement(List<T> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        int randomIndex = random.nextInt(list.size());
        return list.get(randomIndex);
    }

    private List<TimetableChromosome> initPopulation(int populationSize) {
        List<TimetableChromosome> chromosomeList = new ArrayList<>();
        for (int i = 0; i < populationSize; i++) {
            chromosomeList.add(new TimetableChromosome(generateTimetableForAWeek()));
        }
        return chromosomeList;
    }

    private double calculateFitness(TimetableChromosome chromosome) {
        double fitness = 0.0; // the less is better
        fitness += teachersViolation(chromosome) * 100; //big multiplier for worst violations

        return fitness;
    }

    //check if the teacher appears in multiple classrooms in a single day in a single time
    //and return amount of those violations
    public int teachersViolation(TimetableChromosome timetableChromosome){
       int amountOfConflicts = 0;
       Map<String, Set<Teacher>> scheduleMap = new HashMap<>();

        for (Timetable slot : timetableChromosome.getWeeklyTimetable()) {
            String key = slot.getDate() + " " + slot.getPeriodNumber();
            scheduleMap.putIfAbsent(key, new HashSet<>());

            if (!scheduleMap.get(key).add(slot.getTeacher())) {
                amountOfConflicts++;
            }
        }
        return amountOfConflicts;




    }


}
