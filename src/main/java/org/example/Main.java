package org.example;

import models.*;
import models.UlearnCSVParser.UlearnCsvParser;
import models.db.ORMDbRepository;
import models.db.model.StudentEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import vkApi.vkRepository;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.List;


public class Main {
    private final static Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws SQLException {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        ORMDbRepository db = new ORMDbRepository();
        db.connect();
        db.createTables();
        List<StudentEntity> students = db.getStudents();
        if (db.getStudents().isEmpty()) {
            LOGGER.warn("База пуста. Загрузка из csv-файла");
            UlearnCsvParser parser = new UlearnCsvParser("programmingStats.csv");
            Course course = parser.parseCourse();
            vkRepository vk = new vkRepository();
            vk.setCitiesToStudents(course.getStudents());
            LOGGER.info("Создание базы данных и загрузка данных (может занять до 10 минут)");
            db.saveStudents(course.getStudents());
            LOGGER.info("Загрузка в базу данных окончена");
        } else {
            LOGGER.info("База найдена, загрузка из базы");
            students.forEach(student -> LOGGER.info(String.format("Студент: %s", student.getFullName())));
            LOGGER.info("Чтение базы заверщено");
        }
    }
}

