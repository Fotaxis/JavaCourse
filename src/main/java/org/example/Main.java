package org.example;

import models.*;
import models.UlearnCSVParser.UlearnCsvParser;
import models.db.ORMDbRepository;
import models.db.model.StudentEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import visualization.drawer.BarStudentChartDrawer;
import visualization.drawer.PieChartDrawer;
import vkApi.vkRepository;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private final static Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws SQLException {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        Scanner sc = new Scanner(System.in);
        ORMDbRepository db = new ORMDbRepository();
        db.connect();
        db.createTables();
        List<StudentEntity> students = db.getStudents();
        if (db.getStudents().isEmpty()) {
            LOGGER.warn("База пуста. Загрузка из csv-файла");
            UlearnCsvParser parser = new UlearnCsvParser("programmingStats.csv");
            Course course = parser.parseCourse();
            LOGGER.info("Создание базы данных и загрузка данных (может занять до 10 минут)");
            vkRepository vk = new vkRepository();
            vk.setCitiesToStudents(course.getStudents());
            db.saveStudents(course.getStudents());
            LOGGER.info("Загрузка в базу данных окончена");
        } else {
            LOGGER.info("База найдена, загрузка из базы");
            students.forEach(student -> LOGGER.info(String.format("Студент: %s", student.getFullName())));
            LOGGER.info("Чтение базы заверщено");
        }
        System.out.println("Введите одну из комманд: CityPie, CityBar, Exit");
        while(true) {
            String command = sc.nextLine();
            switch(command) {
                case("CityPie"):
                    new PieChartDrawer("Круговая диаграмма", db.getStudentWithCities()).setVisible(true);
                    break;
                case("CityBar"):
                    new BarStudentChartDrawer("Диаграмма", db.getStudentWithCities()).setVisible(true);
                    break;
                case("Exit"):
                    return;
                default:
                    System.out.println("Неизвестный ввод");
                    System.out.println("Комманды: CityPie, CityBar, Exit");
                    break;
            }
        }
    }
}

