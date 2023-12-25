package models.db.model;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import models.Exercise;
import models.ExerciseType;

@DatabaseTable(tableName = "exercises")
public class ExerciseEntity {
    private static final String NAME_COLUMN = "name";
    private static final String GRADE_COLUMN = "grade";
    private static final String TYPE_COLUMN = "type";
    private static final String MAX_GRADE = "maxGrade";

    @DatabaseField(generatedId = true)
    private int exerciseId;

    @DatabaseField
    private String name;

    @DatabaseField
    private int grade;

    @DatabaseField
    private ExerciseType type;

    @DatabaseField
    private int maxGrade;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private ModuleEntity module;

    public ExerciseEntity() {
    }

    public ExerciseEntity(Exercise exercise, ModuleEntity module) {
        this.name = exercise.name();
        this.grade = exercise.grade();
        this.type = exercise.type();
        this.maxGrade = exercise.maxGrade();
        this.module = module;
    }

    public String getName() {
        return name;
    }

    public int getGrade() {
        return grade;
    }

    public ExerciseType getType() {
        return type;
    }

    public int getMaxGrade() {
        return maxGrade;
    }
}
