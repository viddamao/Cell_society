package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.layout.Pane;


/**
 * Chart that graphs the cell data from the grid
 * @author Will Chang
 *
 */
public class SimulationChart extends Pane {
    
    private final int X_AXIS_RANGE = 1000;
    private final int CHART_WIDTH = 500;
    private final int CHART_HEIGHT = 130;
    private final int X_DELTA = 10;
    private final int TICK_MARKS = 4;
    
    private Grid grid;
    private GridInfo infoSheet;
    private LineChart myLineChart;
    private NumberAxis myXAxis;
    private NumberAxis myYAxis;
    private int myWidth;
    private int myHeight;
    private int myXCoord;
    private List<Series> myDataPoints;
    private Map<Integer, Integer> cellCounts;

    /**
     * Constructor for the simulation's chart
     * @param g
     */
    public SimulationChart (Grid g) {
        grid = g;
        myXCoord = 0;
        infoSheet = new GridInfo();
        myDataPoints = new ArrayList<>();
        myWidth = grid.getGridWidth();
        myHeight = grid.getGridHeight();
        intializeChart();
    }

    /**
     * Sets up the axes, datapoints, and linechart
     */
    private void intializeChart () {
        //creating axes
        myXAxis = new NumberAxis(0, X_AXIS_RANGE, X_AXIS_RANGE / TICK_MARKS);
        myYAxis = new NumberAxis(0, myHeight * myWidth, myHeight * myWidth / TICK_MARKS);

        myXAxis.setTickMarkVisible(false);
        myXAxis.setTickLabelsVisible(false);
        myYAxis.setTickMarkVisible(false);
        myYAxis.setTickLabelsVisible(false);

        // creating the chart
        LineChart<Number, Number> lineChart = new LineChart<Number, Number>(myXAxis, myYAxis);
        lineChart.setMaxWidth(CHART_WIDTH);
        lineChart.setMinHeight(CHART_HEIGHT);
        lineChart.setMaxHeight(CHART_HEIGHT);
        lineChart.setCreateSymbols(false);
        lineChart.setLegendVisible(false);

        //adding datapoint lists
        for (int i = 0; i < infoSheet.getMaxCellState(); i++) {
            Series series = new Series();
            myDataPoints.add(series);

        }
        for (Series series : myDataPoints) {
            lineChart.getData().add(series);
        }

        getChildren().add(lineChart);
    }

    /**
     * Checks if the data has reached the length of the graph,
     * plots new datapoints each step
     */
    public void updateDisplay () {
        checkBoundaryAndReset();
        plotData();
    }

    /**
     * Gets cellcounts from grid and plots it in the Chart
     */
    protected void plotData () {
        cellCounts = grid.getCellCounts();
        for (int i = 0; i < myDataPoints.size(); i++) {
            myDataPoints.get(i).getData().add(new XYChart.Data(myXCoord, cellCounts.get(i + 1)));
        }
        myXCoord += X_DELTA;
    }

    /**
     * Checks to see if x coordinate has reached the end of the axis
     */
    public void checkBoundaryAndReset () {
        if (myXCoord >= X_AXIS_RANGE) {
            for (int i = 0; i < myDataPoints.size(); i++) {
                myDataPoints.get(i).getData().clear();
            }
            myXCoord = 0;
        }
    }
}
