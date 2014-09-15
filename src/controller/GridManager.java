package controller;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import simulationObjects.Cell;
import simulationObjects.PredatorCell;

public class GridManager {

    private Cell[][] grid;
    private int[] xDelta = { -1, -1, -1, 0, 0, 1, 1, 1 };
    private int[] yDelta = { -1, 0, 1, -1, 1, -1, 0, 1 };
    private int gWidth;
    private int gHeight;

    /**
     * Constructs the GridManager, initiates private variables
     * 
     * @param width
     *            of the grid
     * @param height
     *            of the grid
     */
    public GridManager(int width, int height) {
	grid = new Cell[width][height];
	gWidth = width;
	gHeight = height;
    }

    /*
     * public void fillGrid(int CellType) { for(Cell[] row : grid){
     * Arrays.fill(row, new PredatorCell()); } }
     */

    /**
     * Updates all Cells in the grid
     */
    public void updateBasedOnNeighbors() {
	for (Cell[] row : grid) {
	    for (Cell unit : row) {
		unit.update(this.getCellsAroundPoint(unit.getX(), unit.getY()));
	    }
	}
    }

    /**
     * Adds cell in specific location in the Grid
     * 
     * @param cell
     *            to be added
     * @param xCoord
     *            in grid
     * @param yCoord
     *            in grid
     */
    public void addCellAtPoint(Cell cell, int xCoord, int yCoord) {
	grid[xCoord][yCoord] = cell;
    }

    /**
     * Retrieves all neighboring cells around a point
     * 
     * @param xCoord
     * @param yCoord
     * @return
     */
    public ArrayList<Cell> getCellsAroundPoint(int xCoord, int yCoord) {
	ArrayList<Cell> neighbors = new ArrayList<>();
	for (int i = 0; i < xDelta.length; i++) {
	    int nextX = xCoord + xDelta[i];
	    int nextY = yCoord + yDelta[i];

	    if (!isOutOfBounds(nextX, nextY)) {
		neighbors.add(grid[nextX][nextY]);
	    }
	}
	return neighbors;
    }

    /**
     * Checks if a location is not in the grid
     * 
     * @param xCoord
     *            in grid
     * @param yCoord
     *            in grid
     * @return true if out of bounds, false otherwise
     */
    private boolean isOutOfBounds(int xCoord, int yCoord) {
	return xCoord > gWidth - 1 || xCoord < 0 || yCoord > gHeight - 1
		|| yCoord < 0;
    }

    public void setWidth(int x) {
	this.gWidth=x;
    }

    public void setHeight(int y) {
	this.gHeight=y;
    }

    public int getWidth() {
	return gWidth;
    }

    public int getHeight() {
	return gHeight;
    }

}
