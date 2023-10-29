import java.util.Dictionary;

public class Student {
    private final String fullName;
    private final String group;
    private final Dictionary<String, Module> modules;

    public Student(String fullName, String group, Dictionary<String, Module> modules) {
        this.fullName = fullName;
        this.group = group;
        this.modules = modules;
    }

    public String getFullName() {
        return fullName;
    }

    public String getGroup() {
        return group;
    }

    public Dictionary<String, Module> getModules() {
        return modules;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(String.format("Имя: %s, Группа: %s\n Баллы:", fullName, group));
        while(modules.elements().hasMoreElements()) {
            result.append(modules.elements().nextElement().toString()).append('\n');
        }
        return result.toString();
    }
}

