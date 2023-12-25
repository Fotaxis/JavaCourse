package models.db;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import models.Exercise;
import models.ExerciseType;
import models.Module;
import models.Student;
import models.db.model.ExerciseEntity;
import models.db.model.ModuleEntity;
import models.db.model.StudentEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.sql.SQLException;
import java.util.List;

public class ORMDbRepository {
    private final static Logger LOGGER = LogManager.getLogger(ORMDbRepository.class);
    private final String URL = "jdbc:sqlite:src/main/resources/database.db";
    private ConnectionSource connectionSource = null;
    private Dao<StudentEntity, String> studentDao = null;
    private Dao<ModuleEntity, String> moduleDao = null;
    private Dao<ExerciseEntity, String> exerciseDao = null;

    public void connect() throws SQLException {
        connectionSource = new JdbcConnectionSource(URL);
        studentDao = DaoManager.createDao(connectionSource, StudentEntity.class);
        moduleDao = DaoManager.createDao(connectionSource, ModuleEntity.class);
        exerciseDao = DaoManager.createDao(connectionSource, ExerciseEntity.class);
    }

    public void createTables() throws SQLException {
        TableUtils.createTableIfNotExists(connectionSource, StudentEntity.class);
        TableUtils.createTableIfNotExists(connectionSource, ModuleEntity.class);
        TableUtils.createTableIfNotExists(connectionSource, ExerciseEntity.class);
    }

    public void saveStudents(List<Student> students) throws SQLException {
        for (Student student : students) {
            StudentEntity studentEntity = new StudentEntity(student);
            studentDao.createIfNotExists(studentEntity);
            for (Module module : student.getModules()) {
                ModuleEntity moduleEntity = new ModuleEntity(module, studentEntity);
                moduleDao.createIfNotExists(moduleEntity);
                for (Exercise exercise : module.getAllExercises()) {
                    ExerciseEntity exerciseEntity = new ExerciseEntity(exercise, moduleEntity);
                    exerciseDao.createIfNotExists(exerciseEntity);
                }
            }
            LOGGER.info(String.format("В базу добавлен студент %s", studentEntity.getFullName()));
        }
    }

    public List<StudentEntity> getStudents() throws SQLException {
        return studentDao.queryForAll();
    }
    public List<ModuleEntity> getStudentModules(StudentEntity student) throws SQLException {
        return moduleDao.queryForEq("StudentID", student.getStudentID());
    }

    public List<StudentEntity> getStudentWithCities() throws SQLException {
        return studentDao.queryBuilder()
                .where()
                 .ne(StudentEntity.CITY_COLUMN, "Не найдено")
                .query();
    }

    public List<ExerciseEntity> getHomeWorks() throws SQLException {
        return exerciseDao.queryBuilder()
                .where()
                    .eq(ExerciseEntity.TYPE_COLUMN, ExerciseType.HOMEWORK)
                    .and()
                    .ne(ExerciseEntity.NAME_COLUMN, "")
                .query();
    }
}
