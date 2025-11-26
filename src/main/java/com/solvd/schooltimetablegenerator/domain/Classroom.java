package com.solvd.schooltimetablegenerator.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Classroom {

    private Long id;
    private Integer number;
    private Boolean isLab;
    private List<Subject> subjects = new ArrayList<>();

    public Classroom() {}

    public Classroom(Integer number, Boolean isLab) {
        this.number = number;
        this.isLab = isLab;
    }

    public Classroom(Long id, Integer number, Boolean isLab) {
        this.id = id;
        this.number = number;
        this.isLab = isLab;
    }

    public Classroom(Long id, Integer number, Boolean isLab, List<Subject> subjects) {
        this.id = id;
        this.number = number;
        this.isLab = isLab;
        this.subjects = subjects;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getNumber() { return number; }
    public void setNumber(Integer number) { this.number = number; }

    public Boolean getIsLab() { return isLab; }
    public void setIsLab(Boolean isLab) { this.isLab = isLab; }

    public List<Subject> getSubjects() { return subjects; }
    public void setSubjects(List<Subject> subjects) { this.subjects = subjects; }

    public void addSubject(Subject subject) {
        this.subjects.add(subject);
    }

    public void removeSubject(Subject subject) {
        this.subjects.remove(subject);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Classroom classroom = (Classroom) o;
        return Objects.equals(id, classroom.id) &&
                Objects.equals(number, classroom.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number);
    }

}