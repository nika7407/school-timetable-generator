package com.solvd.schooltimetablegenerator.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Objects;

public class Timetable {

    private Integer id;
    private LocalDate date;
    private Subject subject;
    private Teacher teacher;
    private Classroom classroom;
    private DayOfWeek dayOfWeek;
    private Integer periodNumber;

    public Timetable() {
    }

    public Timetable(Integer id, LocalDate date, Subject subject, Teacher teacher,
                     Classroom classroom, DayOfWeek dayOfWeek, Integer periodNumber) {
        this.id = id;
        this.date = date;
        this.subject = subject;
        this.teacher = teacher;
        this.classroom = classroom;
        this.dayOfWeek = dayOfWeek;
        this.periodNumber = periodNumber;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Integer getPeriodNumber() {
        return periodNumber;
    }

    public void setPeriodNumber(Integer periodNumber) {
        this.periodNumber = periodNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Timetable timetable = (Timetable) o;
        return Objects.equals(id, timetable.id) &&
                Objects.equals(date, timetable.date) &&
                Objects.equals(subject, timetable.subject) &&
                Objects.equals(teacher, timetable.teacher) &&
                Objects.equals(classroom, timetable.classroom) &&
                dayOfWeek == timetable.dayOfWeek &&
                Objects.equals(periodNumber, timetable.periodNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, subject, teacher, classroom, dayOfWeek, periodNumber);
    }

    @Override
    public String toString() {
        return "Timetable{" +
                "id=" + id +
                ", date=" + date +
                ", subject=" + subject.getName() +
                ", teacher=" + teacher.getFirstName() +
                ", classroom=" + classroom.getNumber() +
                ", dayOfWeek=" + dayOfWeek +
                ", periodNumber=" + periodNumber +
                '}';
    }
}