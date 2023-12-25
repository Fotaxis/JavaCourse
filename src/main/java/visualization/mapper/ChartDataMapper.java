package visualization.mapper;


import models.db.model.StudentEntity;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ChartDataMapper {
    public static PieDataset createStudentsByCities(List<StudentEntity> students) {
        var studentsCountByCities = students.stream()
                .collect(
                        Collectors.groupingBy(
                                StudentEntity::getCity,
                                HashMap::new,
                                Collectors.counting()
                        )
                );
        DefaultPieDataset dataset = new DefaultPieDataset();
        studentsCountByCities.forEach(dataset::setValue);
        return dataset;
    }

    public static CategoryDataset createCityToSumHomeworkGrade(List<StudentEntity> students) {
        DefaultCategoryDataset dataset =  new DefaultCategoryDataset();
        var s = students.stream()
                .collect(
                        Collectors.groupingBy(
                                StudentEntity::getCity,
                                Collectors.summingInt(StudentEntity::getHomeworkGrade))
                );
        s.forEach((key, value) -> dataset.addValue(value, key, key));
        return dataset;
    }
}
