package com.solvd.schooltimetablegenerator.domain;

import java.util.Objects;

public class Classroom {

    private Long id;
    private Integer number;

    public Classroom() {
    }

    public Classroom(Integer number, Boolean isLab) {
        this.number = number;
    }

    public Classroom(Long id, Integer number, Boolean isLab) {
        this.id = id;
        this.number = number;
    }

    public Classroom(Long id, Integer number) {
        this.id = id;
        this.number = number;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
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

    @Override
    public String toString() {
        return "Classroom{" +
                "id=" + id +
                ", number=" + number +
                '}';
    }
}