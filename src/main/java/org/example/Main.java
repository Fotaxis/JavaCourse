package org.example;

import models.*;
import vkApi.vkRepository;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        UlearnCsvParser parser = new UlearnCsvParser("programmingStats.csv");
        Course course = parser.parseCourse();
        vkRepository vk = new vkRepository();
        vk.setCitiesToStudents(course.getStudents());
        System.out.println(course.getReport());
    }
}
