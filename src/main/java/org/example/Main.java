package org.example;


import models.*;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        UlearnCsvParser parser = new UlearnCsvParser("programmingStats.csv");
        System.out.println((parser.parseCourse().getReport()));
    }
}
