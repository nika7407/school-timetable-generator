package com.solvd.schooltimetablegenerator.service.algorithm.dna;

import com.solvd.schooltimetablegenerator.domain.Timetable;

import java.util.List;

public class TimetableChromosome {

    private List<Timetable> weeklyTimetable;
    private double fitness;

    public TimetableChromosome(List<Timetable> weeklyTimetable) {
        this.weeklyTimetable = weeklyTimetable;
    }

    public List<Timetable> getWeeklyTimetable() {
        return weeklyTimetable;
    }

    public void setWeeklyTimetable(List<Timetable> weeklyTimetable) {
        this.weeklyTimetable = weeklyTimetable;
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

}
