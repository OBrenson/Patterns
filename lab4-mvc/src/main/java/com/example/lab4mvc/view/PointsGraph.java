package com.example.lab4mvc.view;

import com.example.lab4mvc.controller.Controller;
import com.example.lab4mvc.model.Point;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;

import java.util.HashMap;
import java.util.Map;

public class PointsGraph {

    private final Controller controller;

    private final Map<Point, XYChart.Data> pointXYChartMap = new HashMap<>();

    public PointsGraph(Pane pane, Controller controller) {
        this.controller = controller;
        createGraph(pane);
    }

    private void createGraph(Pane pane) {
        NumberAxis x = new NumberAxis();
        x.setLabel("X");
        NumberAxis y = new NumberAxis();
        y.setLabel("Y");
        LineChart<Double, Double> lineChart = new LineChart(x,y);
        XYChart.Series series = new XYChart.Series();
        lineChart.getData().add(series);
        series.setName("Graph");
        pane.getChildren().add(lineChart);
        controller.setAddListener(p -> {
                XYChart.Data chart = new XYChart.Data<>(p.getXDouble(), p.getYDouble());
                series.getData().add(chart);
                pointXYChartMap.put(p, chart);
            }
        );
        controller.setRemoveListener(p -> series.getData().remove(pointXYChartMap.get(p)));
    }
}
