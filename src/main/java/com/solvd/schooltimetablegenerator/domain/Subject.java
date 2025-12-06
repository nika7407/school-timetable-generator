package com.solvd.schooltimetablegenerator.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Subject {

    private Long id;
    private String name;
    private String description;
    private List<Teacher> teachers;

    public Subject() {
    }

    public Subject(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Subject(Long id, String name, String description, List<com.solvd.schooltimetablegenerator.domain.Teacher> teachers, List<Classroom> classrooms) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Subject(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public List<com.solvd.schooltimetablegenerator.domain.Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<com.solvd.schooltimetablegenerator.domain.Teacher> teachers) {
        this.teachers = teachers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return Objects.equals(id, subject.id) &&
                Objects.equals(name, subject.name);
    }

    public void addTeacher(com.solvd.schooltimetablegenerator.domain.Teacher teacher) {
        if (teachers == null) {
            teachers = new ArrayList<>();
        }
        this.teachers.add(teacher);
    }

    public com.solvd.schooltimetablegenerator.domain.Teacher teacher(com.solvd.schooltimetablegenerator.domain.Teacher teacher) {
        if (teachers == null || teachers.isEmpty()) return null;
        for (Teacher t : teachers) {
            if (t.equals(teacher)) {
                return t;
            }
        }
        return null;
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}