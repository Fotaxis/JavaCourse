package models;

public record Exercise(String name, int grade, ExerciseType type, int maxGrade) {

    @Override
    public String toString() {
        if (name.isEmpty()){
            return String.format("[Тип: %s, балл: %d, максимальный бал: %d]", type.getType(), grade , maxGrade);
        }
        return String.format("\t[Тип: %s, Название: %s, балл: %d, максимальный бал: %d]",
                type.getType(), name, grade, maxGrade);
    }
}