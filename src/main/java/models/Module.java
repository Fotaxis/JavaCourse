package models;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Module {

    private final String theme;
    private final int activityGrade;
    private final int exerciseGrade;
    private final int homeworkGrade;
    private final int seminarGrade;

    private final ArrayList<Exercise> exercises;

    public Module(String theme, int activityGrade, int exerciseGrade,
                  int homeworkGrade, int seminarGrade, ArrayList<Exercise> exercises) {
        this.theme = theme;
        this.activityGrade = activityGrade;
        this.exerciseGrade = exerciseGrade;
        this.homeworkGrade = homeworkGrade;
        this.seminarGrade = seminarGrade;
        this.exercises = exercises;
    }

    public String getTheme() {
        return theme;
    }

    public int getActivityGrade() {
        return activityGrade;
    }

    public int getSeminarGrade() {
        return seminarGrade;
    }

    public ArrayList<Exercise> getAllExercises() {
        return exercises;
    }

    public int getExercisesGrade() {
        return exerciseGrade;
    }

    public int getHomeworksGrade() {
        return homeworkGrade;
    }



    public int getMaxGrade(ExerciseType type) {
        return exercises.stream()
                .filter(exercise -> exercise.type().equals(type))
                .mapToInt(Exercise::maxGrade)
                .sum();
    }

    public ArrayList<Exercise> getSpecificExercises(ExerciseType type) {
        return exercises.stream()
                .filter(exercise -> exercise.type().equals(type))
                .collect(Collectors.toCollection(ArrayList::new));
    }

   @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Тема: " + theme);
        result.append(String.format(" Всего {Семинарские: %d (максимум: %d), Активности: %d (максимум: %d), Упражнения: %d " +
                        "(максимум: %d), Домашнии работы: %d (максимум: %d)}\n",
                seminarGrade, getMaxGrade(ExerciseType.SEMINAR),
                activityGrade, getMaxGrade(ExerciseType.ACTIVITY),
                exerciseGrade, getMaxGrade(ExerciseType.EXERCISE),
                homeworkGrade, getMaxGrade(ExerciseType.HOMEWORK)));
        for(Exercise exercise: getSpecificExercises(ExerciseType.EXERCISE)){
            result.append(String.format("\t\t%s\n", exercise.toString()));
        }
        for(Exercise homework: getSpecificExercises(ExerciseType.HOMEWORK)) {
            result.append(String.format("\t\t%s\n", homework.toString()));
        }
        return result.toString();
    }
}