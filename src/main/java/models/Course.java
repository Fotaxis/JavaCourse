package models;

import java.util.ArrayList;

public class Course {
    private final ArrayList<Student> students;

    public Course(ArrayList<Student> students) {
        this.students = students;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public String getReport() {
        StringBuilder result = new StringBuilder();
        for(Student student: students) {
            result.append(String.format("{%s\n", student.toString()));
        }
        return result.toString();
    }
}
