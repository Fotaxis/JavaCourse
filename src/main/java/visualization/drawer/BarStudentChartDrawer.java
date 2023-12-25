package visualization.drawer;

import models.db.model.StudentEntity;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.ui.RectangleInsets;
import visualization.mapper.ChartDataMapper;

import javax.swing.*;
import java.util.List;

public class BarStudentChartDrawer extends JFrame {
    public BarStudentChartDrawer(String title, List<StudentEntity> students) {
        super(title);
        setContentPane(createActivityByCitiesPanel(students));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
    }

    private static JPanel createActivityByCitiesPanel(List<StudentEntity> students) {
        JFreeChart chart = createBarChart(ChartDataMapper.createCityToSumHomeworkGrade(students),
                "Количество баллов за домашние работы от города");
        chart.setPadding(new RectangleInsets(0, 2, 0, 0));
        return new ChartPanel(chart);
    }


    private static JFreeChart createBarChart(CategoryDataset dataset, String tittle) {
        JFreeChart chart = ChartFactory.createBarChart(
                tittle,
                "Город", "Количество баллов", dataset, PlotOrientation.HORIZONTAL,
                true, false, false
        );
        return chart;
    }
}
