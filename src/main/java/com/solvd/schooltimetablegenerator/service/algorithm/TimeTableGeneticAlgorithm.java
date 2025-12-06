package com.solvd.schooltimetablegenerator.service.algorithm;

import com.solvd.schooltimetablegenerator.domain.Classroom;
import com.solvd.schooltimetablegenerator.domain.Subject;
import com.solvd.schooltimetablegenerator.domain.Teacher;
import com.solvd.schooltimetablegenerator.domain.Timetable;
import com.solvd.schooltimetablegenerator.service.algorithm.dna.TimetableChromosome;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class TimeTableGeneticAlgorithm {

    private static final Logger log = LogManager.getLogger(TimeTableGeneticAlgorithm.class);
    private List<Teacher> teachers;
    private List<Subject> subjects;
    private List<Classroom> classrooms;
    private static final int DAYS_IN_A_WEEK = 5;
    private static final int DAILY_SUBJECTS = 5;
    private static final double MUTATION_RATE = 0.01;
    private static final int POPULATION_SIZE = 100;
    private static final int GENERATION = 1000;
    private static final Random random = new Random();
    private static LocalDate date = LocalDate.of(2025, 12, 1);

    public TimeTableGeneticAlgorithm() {
    }

    public  static  List<Subject> subjectOfTeacher(Teacher teacher , List<Subject> subjects){
        return  subjects.stream().filter(x->x.getTeachers().contains(teacher)).toList();
    }

    public TimeTableGeneticAlgorithm(List<Teacher> teachers, List<Subject> subjects, List<Classroom> classrooms) {
        this.teachers = teachers;
        this.subjects = subjects;
        this.classrooms = classrooms;
    }

    // generating full timetable for each class for each daysInAWeek and assigning class
    // this essentially returns randomly generated timetable
    private List<Timetable> generateTimetableForAWeek() {
        List<Timetable> list = new ArrayList<>();
        LocalDate originalDate = date;
        for (Classroom classroom : classrooms) {
            for (int i = 0; i < DAYS_IN_A_WEEK; i++) {
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
        List<Timetable> timetableList = new ArrayList<>(DAILY_SUBJECTS);

        for (int i = 0; i < DAILY_SUBJECTS; i++) {
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

        List<Teacher> listOfTeachersWithSubject1 = subject1.getTeachers();
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

    private List<TimetableChromosome> initPopulation() {
        List<TimetableChromosome> chromosomeList = new ArrayList<>();
        for (int i = 0; i < POPULATION_SIZE; i++) {
            chromosomeList.add(new TimetableChromosome(generateTimetableForAWeek()));
        }
        return chromosomeList;
    }

    // applying all constraints and violations to get fitness characteristic the lower fitness is the better
    private double calculateFitness(TimetableChromosome chromosome) {
        double fitness = 0.0; // the less is better
        fitness += teachersViolation(chromosome) * 100; //big multiplier for worst violations
        fitness += peLastViolation(chromosome) * 100;
        fitness += teacherWorkloadConstraint(chromosome) * 50;
        fitness += subjectFrequencyConstraint(chromosome) * 70;
        return fitness;
    }

    //check if the teacher appears in multiple classrooms in a single day in a single time
    //and return amount of those violations
    private int teachersViolation(TimetableChromosome timetableChromosome) {
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

    //checks if the pe is not last and adds violations
    private int peLastViolation(TimetableChromosome timetableChromosome) {
        int violationCounter = 0;

        List<Timetable> weeklyTimetable = timetableChromosome.getWeeklyTimetable()
                .stream()
                .filter(timetable -> timetable.getSubject().getName().equals("PE")).toList();

        for (Timetable timetableSlot : weeklyTimetable) {
            if (!timetableSlot.getPeriodNumber().equals(DAILY_SUBJECTS)) {
                ++violationCounter;
            }
        }
        return violationCounter;
    }

    // checks if teacher works more than subjecss -1
    private int teacherWorkloadConstraint(TimetableChromosome timetableChromosome) {
        List<Timetable> weeklyTimetable = timetableChromosome.getWeeklyTimetable();
        int teacherOverworkedConstraint = 0;

        for (int i = 0; i < DAYS_IN_A_WEEK; i++) {
            LocalDate currentDate = date.plusDays(i);
            for (Teacher teacher : teachers) {
                int dailyLoadOfTeacher = weeklyTimetable.stream()
                        .filter(timetable -> timetable.getDate().equals(currentDate))
                        .filter(timetable -> timetable.getTeacher().equals(teacher))
                        .toList()
                        .size();
                if (dailyLoadOfTeacher >= DAILY_SUBJECTS - 1) {
                    teacherOverworkedConstraint++;
                }
            }
        }
        return teacherOverworkedConstraint;
    }

    //checks if subject appears for one class more than 2 times a day
    private int subjectFrequencyConstraint(TimetableChromosome timetableChromosome) {
        List<Timetable> weeklyTimetable = timetableChromosome.getWeeklyTimetable();
        int subjectFrequencyConstraint = 0;

        for (int i = 0; i < DAYS_IN_A_WEEK; i++) {
            LocalDate currentDate = date.plusDays(i);
            for (Classroom classroom : classrooms) {
                for (Subject subject : subjects) {
                    int dailySubjectForClass = weeklyTimetable.stream()
                            .filter(timetable -> timetable.getDate().equals(currentDate))
                            .filter(timetable -> timetable.getClassroom().equals(classroom))
                            .filter(timetable -> timetable.getSubject().equals(subject))
                            .toList()
                            .size();
                    if (dailySubjectForClass > 1) {
                        subjectFrequencyConstraint += (dailySubjectForClass - 1);
                    }
                }
            }
        }
        return subjectFrequencyConstraint;
    }

    //test method to check best chromosome
    private TimetableChromosome findBestChromosome(List<TimetableChromosome> population) {

        for (TimetableChromosome chromosome : population) {
            chromosome.setFitness(calculateFitness(chromosome));
        }

        return population.stream()
                .min(Comparator.comparingDouble(TimetableChromosome::getFitness))
                .orElse(null);
    }

    //selects random best patterns
    private TimetableChromosome selectParent(List<TimetableChromosome> population) {
        TimetableChromosome best = null;

        for (int i = 0; i < 3; i++) {
            TimetableChromosome candidate = population.get(random.nextInt(population.size()));
            if (best == null || candidate.getFitness() < best.getFitness()) {
                best = candidate;
            }
        }
        return best;
    }

    // combining multiple parts of parent timetablechromosomes
    private TimetableChromosome crossover(TimetableChromosome parent1, TimetableChromosome parent2) {
        List<Timetable> childTimetable = new ArrayList<>();
        int crossoverPoint = random.nextInt(parent1.getWeeklyTimetable().size());

        // take first part from parent1, second from parent2
        for (int i = 0; i < parent1.getWeeklyTimetable().size(); i++) {
            if (i < crossoverPoint) {
                childTimetable.add(copyTimetable(parent1.getWeeklyTimetable().get(i)));
            } else {
                childTimetable.add(copyTimetable(parent2.getWeeklyTimetable().get(i)));
            }
        }
        return new TimetableChromosome(childTimetable);
    }

    private Timetable copyTimetable(Timetable original) {

        Timetable copy = new Timetable();
        copy.setDate(original.getDate());
        copy.setSubject(original.getSubject());
        copy.setTeacher(original.getTeacher());
        copy.setClassroom(original.getClassroom());
        copy.setDayOfWeek(original.getDayOfWeek());
        copy.setPeriodNumber(original.getPeriodNumber());
        return copy;

    }

    //creating new gen and applying all the parameters
    private List<TimetableChromosome> createNewGeneration(List<TimetableChromosome> population) {
        List<TimetableChromosome> newPopulation = new ArrayList<>();

        newPopulation.add(findBestChromosome(population));

        while (newPopulation.size() < population.size()) {
            TimetableChromosome parent1 = selectParent(population);
            TimetableChromosome parent2 = selectParent(population);
            TimetableChromosome child = crossover(parent1, parent2);
            mutate(child);
            newPopulation.add(child);
        }

        return newPopulation;
    }

    // random changes to not to get stuck on one output
    private void mutate(TimetableChromosome chromosome) {
        Random random = new Random();

        for (Timetable slot : chromosome.getWeeklyTimetable()) {
            if (random.nextDouble() < MUTATION_RATE) {
                Subject newSubject = randomListElement(subjects);
                slot.setSubject(newSubject);
                List<Teacher> teachersForSubject =newSubject.getTeachers();
                if (!teachersForSubject.isEmpty()) {
                    slot.setTeacher(randomListElement(teachersForSubject));
                }
            }

      }
    }

    //final method
    public List<Timetable> generateBestTimetable() {
        List<TimetableChromosome> population = initPopulation();

        TimetableChromosome bestChromosome = null;

        for (int generation = 0; generation < GENERATION; generation++) {
            bestChromosome = findBestChromosome(population);
            population = createNewGeneration(population);

            if (generation % 10 == 0) {
                System.out.println("Generation " + generation + " Best Fitness: " + bestChromosome.getFitness());
            }

            if (bestChromosome.getFitness() == 0) {
                return bestChromosome.getWeeklyTimetable();
            }

        }
        return bestChromosome.getWeeklyTimetable();
    }

}
