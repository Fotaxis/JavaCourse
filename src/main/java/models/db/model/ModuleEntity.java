package models.db.model;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.field.DatabaseField;
import models.Module;


@DatabaseTable(tableName = "modules")
public class ModuleEntity {
    public static final String THEME_COLUMN = "theme";
    public static final String STUDENT_ID_COLUMN = "student";
    public static final String ACTIVITY_COLUMN = "activityGrade";
    public static final String SEMINAR_COLUMN = "seminarGrade";
    public static final String EXERCISE_COLUMN = "exerciseGrade";
    public static final String HOMEWORK_COLUMN = "homeworkGrade";

    @DatabaseField(generatedId = true)
    private long modelueId;

    @DatabaseField(canBeNull = false)
    private String theme;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private StudentEntity student;

    @DatabaseField
    private int activityGrade;

    @DatabaseField
    private int seminarGrade;

    @DatabaseField
    private int exerciseGrade;

    @DatabaseField
    private int homeworkGrade;

    @ForeignCollectionField(eager = true)
    private ForeignCollection<ExerciseEntity> exercises;

    public ModuleEntity() {
    }

    public ModuleEntity(Module module, StudentEntity student) {
        this.theme = module.getTheme();
        this.activityGrade = module.getActivityGrade();
        this.seminarGrade = module.getSeminarGrade();
        this.exerciseGrade = module.getExercisesGrade();
        this.homeworkGrade = module.getHomeworksGrade();
        this.student = student;
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

    public int getExerciseGrade() {
        return exerciseGrade;
    }

    public int getHomeworkGrade() {
        return homeworkGrade;
    }

}
