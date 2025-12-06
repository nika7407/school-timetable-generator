package com.solvd.schooltimetablegenerator.service.controller;

import com.solvd.schooltimetablegenerator.domain.Classroom;
import com.solvd.schooltimetablegenerator.domain.Subject;
import com.solvd.schooltimetablegenerator.domain.Teacher;
import com.solvd.schooltimetablegenerator.domain.Timetable;
import com.solvd.schooltimetablegenerator.service.ClassroomService;
import com.solvd.schooltimetablegenerator.service.TeacherService;
import com.solvd.schooltimetablegenerator.service.algorithm.TimeTableGeneticAlgorithm;
import com.solvd.schooltimetablegenerator.service.impl.ClassroomServiceImpl;
import com.solvd.schooltimetablegenerator.service.impl.SubjectServiceImpl;
import com.solvd.schooltimetablegenerator.service.impl.TeacherServiceImpl;
import com.solvd.schooltimetablegenerator.service.impl.TimetableServiceImpl;
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
    private static final ClassroomService classroomService = new ClassroomServiceImpl();
    private static final TeacherService teacherService = new TeacherServiceImpl();
    private static final TimetableServiceImpl timetableService = new TimetableServiceImpl();

    public Controller() {
    }

    public void testController(List<Subject> subjectss, List<Classroom> classroomss, List<Teacher> teachers) {
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

    public void printWeeklyGrid(List<Timetable> list) {

        Map<Classroom, Map<LocalDate, List<Timetable>>> grouped =
                list.stream()
                        .sorted(
                                Comparator.comparing((Timetable t) -> t.getClassroom().getNumber())
                                        .thenComparing(Timetable::getDate)
                                        .thenComparing(Timetable::getPeriodNumber)
                        )
                        .collect(Collectors.groupingBy(
                                Timetable::getClassroom,
                                Collectors.groupingBy(Timetable::getDate)
                        ));

        final int COL_WIDTH = 38;

        for (Classroom room : grouped.keySet()) {

            List<LocalDate> weekDates = grouped.get(room).keySet()
                    .stream()
                    .sorted()
                    .toList();

            if (weekDates.isEmpty()) continue;

            LocalDate monday = weekDates.get(0);

            System.out.println();
            System.out.println("==============================================================");
            System.out.printf("                 CLASSROOM %s — WEEK OF: %s%n",
                    room.getNumber(), monday);
            System.out.println("==============================================================");
            System.out.println();

            System.out.printf("%-15s", "");
            for (LocalDate d : weekDates) {
                String day = d.getDayOfWeek().name().substring(0, 3);
                System.out.printf("%-" + COL_WIDTH + "s", day);
            }
            System.out.println();

            for (int period = 1; period <= 5; period++) {

                System.out.printf("Period %-7d", period);

                for (LocalDate d : weekDates) {

                    List<Timetable> dayList = grouped.get(room).get(d);
                    if (dayList == null) {
                        System.out.printf("%-" + COL_WIDTH + "s", "");
                        continue;
                    }

                    int p = period;
                    Timetable match = dayList.stream()
                            .filter(t -> t.getPeriodNumber() == p)
                            .findFirst()
                            .orElse(null);

                    if (match == null) {
                        System.out.printf("%-" + COL_WIDTH + "s", "");
                    } else {
                        String subject = match.getSubject().getName();
                        String teacher = match.getTeacher().getFirstName();
                        String text = subject + " (" + teacher + ")";
                        System.out.printf("%-" + COL_WIDTH + "s", text);
                    }
                }

                System.out.println();
            }

        }
        System.out.println("\n");
    }


    public void controllerStart() {
        List<Subject> subjectList = subjectService.findAll();
        List<Classroom> classroomList = classroomService.findAll();
        List<Teacher> teacherList = teacherService.findAll();

        List<Subject> subjects = new ArrayList<>();

        List<Classroom> classrooms = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        log.info("hello, welcome to the timetable generator!");
        log.info("first select the subjects that you want:");

        for (Subject subject : subjectList) {
            log.info("\nname: {} | id: {} |", subject.getName(), subject.getId());
        }

        log.info("select by writing subjects id (type 'done' when finished or 'all' to add all subjects)");

        String input;
        while (!(input = scanner.nextLine()).equals("done")) {
            if (input.equalsIgnoreCase("all")) {
                subjects.addAll(subjectList);
                log.info("all subjects added");
                break;
            }

            try {
                Long id = Long.parseLong(input);
                subjectList.stream()
                        .filter(subject -> subject.getId().equals(id))
                        .findFirst()
                        .ifPresent(subject -> {
                            if (!subjects.contains(subject)) {
                                subjects.add(subject);
                            }
                        });
            } catch (NumberFormatException e) {
                log.info("Please enter a valid number or 'all', or 'done'");
            }
        }
        log.info("subjects selected");

        for (Classroom classroom : classroomList) {
            log.info("\nclassroom number: {} | id: {} |", classroom.getNumber(), classroom.getId());
        }

        log.info("now select classes by id and type 'done' after ");
        while (!(input = scanner.nextLine()).equals("done")) {
            try {
                Long id = Long.parseLong(input);
                classroomList.stream()
                        .filter(classroom -> classroom.getId().equals(id))
                        .findFirst()
                        .ifPresent(classrooms::add);
            } catch (NumberFormatException e) {
                log.info("please enter a valid number or 'done'");
            }
        }
        // scanner.close();

        generationSequence(subjectList, classroomList, teacherList);
    }

    private void generationSequence(List<Subject> subjects, List<Classroom> classrooms, List<Teacher> teachers) {
        log.info("great, generating timetable");
        TimeTableGeneticAlgorithm ga = new TimeTableGeneticAlgorithm(teachers, subjects, classrooms);
        List<Timetable> timetableList = ga.generateBestTimetable();
        printWeeklyGrid(timetableList);

        Scanner scanner = new Scanner(System.in);
        log.info("\ndo you want to save this timetable?  \n 1.yes\n 2.no\n 3.regenerate ");

        String saveResponse = scanner.nextLine();

        if (saveResponse.equals("1")) {
            timetableService.create(timetableList);
            log.info("timetable saved successfully!");
            log.info("thanks for using the app\n closing the program");
        }
        if (saveResponse.equals("3")) {
            generationSequence(subjects, classrooms, teachers);

        } else {
            log.info("Timetable not saved.");
            log.info("thanks for using the app\n closing the program");
        }

    }

}
