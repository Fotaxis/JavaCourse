package models.db.model;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.field.DatabaseField;
import models.Student;


@DatabaseTable(tableName = "students")
public class StudentEntity {
    public static final String NAME_COLUMN = "fullname";
    public static final String GROUP_COLUMN = "group";
    public static final String CITY_COLUMN = "city";
    public static final String ACTIVITY_COLUMN = "activityGrade";
    public static final String SEMINAR_COLUMN = "seminarGrade";
    public static final String EXERCISE_COLUMN = "exerciseGrade";
    public static final String HOMEWORK_COLUMN = "homeworkGrade";

    @DatabaseField(generatedId = true)
    private long studentId;

    @DatabaseField(canBeNull = false)
    private String fullName;

    @DatabaseField(canBeNull = false)
    private String group;

    @DatabaseField()
    private String city;

    @DatabaseField()
    private int activityGrade;

    @DatabaseField()
    private int seminarGrade;

    @DatabaseField()
    private int exerciseGrade;

    @DatabaseField()
    private int homeworkGrade;

    @ForeignCollectionField(eager = false)
    private ForeignCollection<ModuleEntity> modules;

    public StudentEntity(){
    }

    public StudentEntity(Student student){
        this.fullName = student.getFullName();
        this.group = student.getGroup();
        this.city = student.getCity();
        this.activityGrade = student.getActivityGrade();
        this.seminarGrade = student.getSeminarGrade();
        this.exerciseGrade = student.getExerciseGrade();
        this.homeworkGrade = student.getHomeworkGrade();
    }

    public long getStudentID(){
        return studentId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getGroup() {
        return group;
    }

    public String getCity() {
        return city;
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

    public ForeignCollection<ModuleEntity> getModules() {
        return modules;
    }

    @Override
    public String toString() {
        return String.format("Имя: %s, Группа: %s, Город: %s, Всего баллов: Сем:%d, Акт:%d, :Упр:%d, Дз: %d\n",
                fullName, group, city, seminarGrade, activityGrade, exerciseGrade, homeworkGrade);
    }

}
