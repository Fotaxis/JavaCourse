package models;

public enum ExerciseType {
    ACTIVITY("Акт"),
    ADDITIONAL("Доп"),
    SEMINAR("Сем"),
    HOMEWORK("ДЗ"),
    EXERCISE("Упр");

    private final String type;

    ExerciseType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static ExerciseType fromString(String type) {
        for (ExerciseType taskType : ExerciseType.values()) {
            if (taskType.type.equals(type)) {
                return taskType;
            }
        }
        throw new IllegalArgumentException(String.format("%s type not found", type));
    }
}
