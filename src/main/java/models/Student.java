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

    public String getGroup() {
        return group;
    }

    public ArrayList<Module> getModules() {
        return modules;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(
                String.format("Имя: %s, Группа: %s, Всего баллов: Сем:%d, Акт:%d, :Упр:%d, Дз: %d\n",
                        fullName, group, seminarGrade, activityGrade, exerciseGrade, homeworkGrade));
        for(Module module: modules) {
            result.append(String.format("\t%s\n", module.toString()));
        }
        return result.toString();
    }
}

