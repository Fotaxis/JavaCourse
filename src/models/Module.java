public class Module {

    private final String theme;
    private final int activityGrade;
    private final int seminarGrade;
    private final Exercise[] exercises;
    private final Exercise[] homeworks;

    public Module(String theme, int activityGrade, int seminarGrade, Exercise[] exercises, Exercise[] homeworks) {
        this.theme = theme;
        this.activityGrade = activityGrade;
        this.seminarGrade = seminarGrade;
        this.exercises = exercises;
        this.homeworks = homeworks;
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

    public Exercise[] getExercises() {
        return exercises;
    }

    public Exercise[] getHomeworks() {
        return homeworks;
    }

    public int getExerciesesGrade() {
        return getGrades(exercises);
    }

    public int getHomeworksGrade() {
        return getGrades(homeworks);
    }

    private int getGrades(Exercise[] exercises) {
        int result = 0;
        for (Exercise exercise : exercises) {
            result += exercise.grade();
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Тема: " + theme);
        result.append(String.format("Всего { Семинарские: %d, Активности: %d, Упражнения: %d, Домашнии работы: %d}\n",
                seminarGrade, activityGrade, getExerciesesGrade(), getHomeworksGrade()));
        for(Exercise exercise: exercises){
            result.append(exercise.toString()).append('\n');
        }
        for(Exercise homework: homeworks) {
            result.append(homework.toString()).append('\n');
        }
        return result.toString();
    }
}