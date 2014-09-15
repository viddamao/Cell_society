package controller;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import simulationObjects.Cell;
import simulationObjects.PredatorCell;


public class GridManager {
    private Cell[][] grid;

    public GridManager (int size)
    {

    }

    /*
     * public void fillGrid(int CellType)
     * {
     * for(Cell[] row : grid){
     * Arrays.fill(row, new PredatorCell());
     * }
     * }
     */

    public void update ()
    {
        for (Cell[] row : grid) {
            for (Cell unit : row) {
                unit.update();
            }
        }
    }

    public void addCellAtPoint (Cell c, Point pt)
    {

    }

    public ArrayList<Cell> getEmptyCellsAroundPoint (Point p)
    {
        return null;
    }

    public ArrayList<Cell> getCellsAroundPoint (Point p)
    {
        return null;
    }

}
