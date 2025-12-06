package com.solvd.schooltimetablegenerator;

import com.solvd.schooltimetablegenerator.domain.Classroom;
import com.solvd.schooltimetablegenerator.domain.Subject;
import com.solvd.schooltimetablegenerator.domain.Teacher;
import com.solvd.schooltimetablegenerator.domain.Timetable;
import com.solvd.schooltimetablegenerator.service.algorithm.TimeTableGeneticAlgorithm;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

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

        Subject math = new Subject(1L, "Mathematics", "Mathematics");
        Subject physics = new Subject(2L, "Physics", "Physics");
        Subject chemistry = new Subject(3L, "Chemistry", "Chemistry");
        Subject biology = new Subject(4L, "Biology", "Biology");
        Subject history = new Subject(5L, "History", "History");
        Subject geography = new Subject(6L, "Geography", "Geography");
        Subject english = new Subject(7L, "English", "English");
        Subject computerScience = new Subject(8L, "Computer Science", "Computer Science");
        Subject pe = new Subject(9L, "PE", "Physical Education");

        math.addTeacher(teachers.getFirst());
        math.addTeacher(teachers.get(1));
        physics.addTeacher(teachers.get(2));
        chemistry.addTeacher(teachers.get(4));
        biology.addTeacher(teachers.get(5));
        history.addTeacher(teachers.get(6));
        geography.addTeacher(teachers.get(7));

        List<Subject> subjects = List.of(math, physics, chemistry, biology, history, geography);


        List<Classroom> classrooms = new ArrayList<>();
        classrooms.add(new Classroom(1L, 101, false));
        classrooms.add(new Classroom(2L, 102, false));
        classrooms.add(new Classroom(3L, 201, true));

      TimeTableGeneticAlgorithm ga = new TimeTableGeneticAlgorithm(teachers, subjects, classrooms);
        List<Timetable> timetableList = ga.generateBestTimetable();

        printWeeklyGrid(timetableList);

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
