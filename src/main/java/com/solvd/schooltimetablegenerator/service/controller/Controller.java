package com.solvd.schooltimetablegenerator.service.controller;

import com.solvd.schooltimetablegenerator.domain.Classroom;
import com.solvd.schooltimetablegenerator.domain.Subject;
import com.solvd.schooltimetablegenerator.domain.Teacher;
import com.solvd.schooltimetablegenerator.domain.Timetable;
import com.solvd.schooltimetablegenerator.service.algorithm.TimeTableGeneticAlgorithm;
import com.solvd.schooltimetablegenerator.service.impl.SubjectServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Controller {

    private static final Logger log = LogManager.getLogger(Controller.class);
    private static final SubjectServiceImpl subjectService = new SubjectServiceImpl();

    public void controllerStart() {
        List<Subject> subjects;
        Scanner scanner = new Scanner(System.in);
        log.info("Hello, Welcome to the timetable Generator!");
        log.info("First Select the subjects that you want:");
        for (Subject subject : subjectService.findAll()) {
            log.info("\nname: {},  id: {}", subject.getName(), subject.getId());
        }

    }

    ;

    public static void testController(List<Subject> subjectss, List<Classroom> classroomss, List<Teacher> teachers) {
        List<Subject> subjects = new ArrayList<>();
        List<Classroom> classrooms = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        log.info("hello, welcome to the timetable generator!");
        log.info("first select the subjects that you want:");

        for (Subject subject : subjectss) {
            log.info("\nname: {},  id: {}", subject.getName(), subject.getId());
        }

        log.info("select by writing subjects id (type done when finished)");

        String input;
        while (!(input = scanner.nextLine()).equals("done")) {
            try {
                Long id = Long.parseLong(input);
                subjectss.stream()
                        .filter(subject -> subject.getId().equals(id))
                        .findFirst()
                        .ifPresent(subjects::add);
            } catch (NumberFormatException e) {
                log.info("please enter a valid number or 'done'");
            }
        }
        log.info("selected!");

        for (Classroom classroom : classroomss) {
            log.info("\nclassroom number: {},  id: {}", classroom.getNumber(), classroom.getId());
        }

        log.info("now select classes by id:");
        while (!(input = scanner.nextLine()).equals("done")) {
            try {
                Long id = Long.parseLong(input);
                classroomss.stream()
                        .filter(classroom -> classroom.getId().equals(id))
                        .findFirst()
                        .ifPresent(classrooms::add);
            } catch (NumberFormatException e) {
                log.info("please enter a valid number or 'done'");
            }
        }
        log.info("great, generating timetable");
        TimeTableGeneticAlgorithm ga = new TimeTableGeneticAlgorithm(teachers, subjects, classrooms);
        List<Timetable> timetableList = ga.generateBestTimetable();
        printWeeklyGrid(timetableList);
    }

    public static void printWeeklyGrid(List<Timetable> list) {

        Map<Classroom, Map<LocalDate, List<Timetable>>> grouped =
                list.stream()
                        .sorted(
                                Comparator.comparing((Timetable t) -> t.getClassroom().getNumber())
                                        .thenComparing(Timetable::getDate)
                                        .thenComparing(Timetable::getPeriodNumber)
                        )
                        .collect(
                                Collectors.groupingBy(
                                        Timetable::getClassroom,
                                        Collectors.groupingBy(Timetable::getDate)
                                )
                        );

        for (Classroom room : grouped.keySet()) {

            List<LocalDate> weekDates = grouped.get(room).keySet()
                    .stream()
                    .sorted()
                    .toList();

            if (weekDates.isEmpty()) continue;

            LocalDate monday = weekDates.get(0);

            System.out.println("\n==============================================================");
            System.out.printf("                 CLASSROOM %s — WEEK OF: %s\n",
                    room.getNumber(), monday);
            System.out.println("==============================================================\n");

            System.out.printf("%-15s", "");
            for (LocalDate d : weekDates) {
                System.out.printf("%-30s", d.getDayOfWeek().name().substring(0, 3));
            }
            System.out.println();

            for (int period = 1; period <= 5; period++) {

                System.out.printf("Period %-7d", period);

                for (LocalDate d : weekDates) {
                    List<Timetable> dayList = grouped.get(room).get(d);

                    int finalPeriod = period;

                    Timetable match = dayList.stream()
                            .filter(t -> t.getPeriodNumber() == finalPeriod)
                            .findFirst()
                            .orElse(null);

                    if (match == null) {
                        System.out.printf("%-30s", "");
                    } else {
                        String subject = match.getSubject().getName();
                        String teacher = match.getTeacher().getFirstName() + " " + match.getTeacher().getLastName();
                        System.out.printf("%-30s", subject + " (" + teacher + ")");
                    }
                }
                System.out.println();
            }

            System.out.println("--------------------------------------------------------------");
        }
    }

}
