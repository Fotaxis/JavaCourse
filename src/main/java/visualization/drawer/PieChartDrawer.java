package visualization.drawer;

import models.db.model.StudentEntity;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.RectangleInsets;
import visualization.mapper.ChartDataMapper;

import javax.swing.*;
import java.util.List;

public class PieChartDrawer extends JFrame {

    public PieChartDrawer(String title, List<StudentEntity> studentList) {
        super(title);
        setContentPane(createStudentsByCitiesPanel(studentList));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(800, 600);
    }

    public static JPanel createStudentsByCitiesPanel(List<StudentEntity> students) {
        JFreeChart chart = createPieChart(ChartDataMapper.createStudentsByCities(students));
        chart.setPadding(new RectangleInsets(4, 8, 2, 2));
        return new ChartPanel(chart);
    }

    private static JFreeChart createPieChart(PieDataset dataset) {
        return ChartFactory.createPieChart(
                "Соотношение городов у студентов",
                dataset,
                false,
                true,
                false
        );
    }

}