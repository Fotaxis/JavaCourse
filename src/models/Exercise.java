public record Exercise(String name, int grade) {

    @Override
    public String toString() {
        return String.format("[Название: %s , Оценка:  %d]\n", name, grade);
    }
}