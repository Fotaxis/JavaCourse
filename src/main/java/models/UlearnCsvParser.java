package models;
import com.opencsv.*;
import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class UlearnCsvParser {
    private final CSVParser parser;
    private final String pathToCSV;

    public UlearnCsvParser(String pathToCSV) {
        this.pathToCSV = pathToCSV;
        parser = new CSVParserBuilder().withSeparator(';').build();
    }

    public Course parseCourse() {
        CSVReader reader;
        try {
            reader = new CSVReaderBuilder(new InputStreamReader(new FileInputStream(pathToCSV), StandardCharsets.UTF_8))
                    .withCSVParser(parser)
                    .build();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ArrayList<Student> students = new ArrayList<>();
        try {
            String[] themes = reader.readNext();
            String[] header = reader.readNext();
            String[] maxGrades = reader.readNext();
            for(String[] line: reader.readAll()) {
                students.add(parseStudent(line, themes, header, maxGrades));
            }
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }
        return new Course(students);
    }

    private ExerciseType getExerciseType(String exercise) {
        int index = exercise.indexOf(":");
        if (index == -1) {
            return ExerciseType.fromString(exercise);
        }
        return ExerciseType.fromString(exercise.substring(0, index));
    }

    private String getExerciseName(String exercise) {
        int index = exercise.indexOf(":");
        if (index == -1) {
            return "";
        }
        return exercise.substring(index + 2);
    }

    private int getExerciseGrade(ArrayList<Exercise> exercises, ExerciseType type) {

        return exercises.stream()
                .filter(exercise -> exercise.type().equals(type))
                .filter(exercise -> exercise.name().isEmpty())
                .mapToInt(Exercise::grade)
                .sum();
    }

    private ArrayList<Module> parseModules(String[] values, String[] themes, String[] exercisesNames, String[] maxGrades){
        int position = 8; // initial position
        String moduleName = null;
        ArrayList<Module> modules = new ArrayList<>();
        ArrayList<Exercise> exercises = new ArrayList<>();
        for(; position <= values.length; position++) {
            if (position == 8){ // initial module
                moduleName = themes[position];
                exercises.add(new Exercise("", Integer.parseInt(values[position]),
                        ExerciseType.ACTIVITY, Integer.parseInt(maxGrades[position])));
                continue;
            }
            if (position != values.length && themes[position].isEmpty()) {
                exercises.add(new Exercise(getExerciseName(exercisesNames[position]),
                        Integer.parseInt(values[position]), getExerciseType(exercisesNames[position]),
                        Integer.parseInt(maxGrades[position])));
                continue;
            }
            modules.add(new Module(moduleName, getExerciseGrade(exercises, ExerciseType.ACTIVITY),
                    getExerciseGrade(exercises, ExerciseType.EXERCISE), getExerciseGrade(exercises, ExerciseType.HOMEWORK),
                    getExerciseGrade(exercises, ExerciseType.SEMINAR), exercises));
            if (position != values.length ) {
                moduleName = themes[position];
                exercises = new ArrayList<>();
            }
        }
        return modules;
    }

    private Student parseStudent(String[] values, String[] themes, String[] exercisesNames, String[] maxGrades) {
        String name = values[0];
        String group = values[1];
        int activityGrade = Integer.parseInt(values[2]);
        int exerciseGrade = Integer.parseInt(values[3]);
        int homeworkGrade = Integer.parseInt(values[4]);
        int seminarGrade = Integer.parseInt(values[5]);
        ArrayList<Module> modules = parseModules(values, themes, exercisesNames, maxGrades);
        return new Student(name, group, activityGrade, exerciseGrade, homeworkGrade, seminarGrade, modules);
    }
}
