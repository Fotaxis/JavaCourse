public class Course {
    private final Student[] students;

    public Course(Student[] students) {
        this.students = students;
    }

    public Student[] getStudents() {
        return students;
    }

    public String getReport() {
        StringBuilder result = new StringBuilder();
        for(Student student: students) {
            result.append(String.format("{ %s\n", student.toString()));
        }
        return result.toString();
    }
}
