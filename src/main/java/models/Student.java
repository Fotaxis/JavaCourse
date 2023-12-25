package models;

import java.util.ArrayList;

public class Student {
    private final String fullName;
    private final String group;
    private final int activityGrade;
    private final int exerciseGrade;
    private final int homeworkGrade;
    private final int seminarGrade;
    private final ArrayList<Module> modules;
    private String city;

    public Student(String fullName, String group, int activityGrade, int exerciseGrade,
                   int homeWorkGrade, int seminarGrade, ArrayList<Module> modules) {
        this.fullName = fullName;
        this.group = group;
        this.activityGrade = activityGrade;
        this.exerciseGrade = exerciseGrade;
        this.homeworkGrade = homeWorkGrade;
        this.seminarGrade = seminarGrade;
        this.modules = modules;
    }

    public String getFullName() {
        return fullName;
    }

    public int getActivityGrade() {
        return activityGrade;
    }

    public int getExerciseGrade() {
        return exerciseGrade;
    }

    public int getHomeworkGrade() {
        return homeworkGrade;
    }

    public int getSeminarGrade() {
        return seminarGrade;
    }

    public String getGroup() {
        return group;
    }

    public ArrayList<Module> getModules() {
        return modules;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return String.format("Имя: %s, Группа: %s, Город: %s, Всего баллов: Сем:%d, Акт:%d, :Упр:%d, Дз: %d\n",
                        fullName, group, city, seminarGrade, activityGrade, exerciseGrade, homeworkGrade);
    }
}

