package controller;

import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.layout.Pane;

public class SimulationChart extends Pane {
    private Grid grid;
    private ArrayList<XYChart.Series> datapoints;
    private GridInfo infoSheet;
    private LineChart lineChart;
    private NumberAxis xAxis;
    private NumberAxis yAxis;
    private int width;
    private int height;
    private int xCoord;
    private HashMap<Integer,Integer> cellCounts;
    
    public SimulationChart(Grid g)
    {
        grid = g;
        xCoord = 0;
        infoSheet = new GridInfo();
        datapoints = new ArrayList<>();
        width = grid.getGridWidth();
        height =grid.getGridHeight();
        intialize();
    }

    private void intialize () {
        xAxis = new NumberAxis(0,1000,1000/4);
        yAxis = new NumberAxis(0,height*width,height*width/4);
        
        xAxis.setTickMarkVisible(false);
        xAxis.setTickLabelsVisible(false);
        yAxis.setTickMarkVisible(false);
        yAxis.setTickLabelsVisible(false);
        
        
        //creating the chart
        LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);
        lineChart.setMaxWidth(500);
        lineChart.setMinHeight(130);
        lineChart.setMaxHeight(130);
        lineChart.setCreateSymbols(false);

        lineChart.setLegendVisible(false);
        
        for(int i = 0; i<infoSheet.getMaxCellState(); i++)
        {
            XYChart.Series series = new XYChart.Series();
            datapoints.add(series);
            
        }
        for(XYChart.Series series : datapoints)
        {
            lineChart.getData().add(series);
        }

        this.getChildren().add(lineChart);        
    }
    
    public void updateDisplay()
    {
        checkBoundaryAndReset();
        plotData();
    }

    protected void plotData () {
        cellCounts = grid.getCellCounts();
        for(int i = 0; i < datapoints.size(); i++)
        {
            datapoints.get(i).getData().add(new XYChart.Data(xCoord,cellCounts.get(i+1)));
        }
        xCoord +=10;
    }
    public void checkBoundaryAndReset()
    {
        if(xCoord >= 1000)
        {
            for(int i =0; i < datapoints.size();i++)
            {
                datapoints.get(i).getData().clear();
            }
            xCoord = 0;
        }
    }
}
