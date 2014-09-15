package controller;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;

import simulationObjects.Cell;
import simulationObjects.PredatorCell;

public class GridManager {

    private Cell[][] grid;
    private int[][] gridIsEmpty; // 1 for moved cells; 0 for cells not moving ;3
				 // for empty
    private int[] availableCoord;
    private int[] xDelta = { -1, -1, -1, 0, 0, 1, 1, 1 };
    private int[] yDelta = { -1, 0, 1, -1, 1, -1, 0, 1 };
    private int gWidth;
    private int gHeight;
    private String cellType;
    // {4,8} indicates adjacent type to be 4 or 8 blocks around
    private int adjacentType;

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
	for (int i = 0; i < gWidth; i++)
	    for (int j = 0; j < gHeight; j++)
		gridIsEmpty[i][j] = 3;

	for (Cell[] row : grid) {
	    for (Cell unit : row) {
		gridIsEmpty[unit.getX()][unit.getY()] = ((unit.needUpdate(this
			.getCellsAroundPoint(unit.getX(), unit.getY()))) ? 1
			: 0);

	    }
	}

	for (Cell[] row : grid) {
	    for (Cell unit : row) {

		if (gridIsEmpty[unit.getX()][unit.getY()] == 1) {
		    gridIsEmpty[unit.getX()][unit.getY()] = 3;

		    availableCoord = findFirstEmptyCell();
		    removeCellAtPoint(unit.getX(), unit.getY());
		    addCellAtPoint(unit, availableCoord[0], availableCoord[1]);
		    unit.setX(availableCoord[0]);
		    unit.setY(availableCoord[1]);

		}

	    }

	}
    }

    
    /**
     * 
     * 
     * 
     * 
     * @return first empty grid cell coordinate
     */
    private int[] findFirstEmptyCell() {
	int[] coordinates = new int[2];
	for (int i = 0; i < gWidth; i++)
	    for (int j = 0; j < gHeight; j++)
		if ((gridIsEmpty[i][j] == 1) || (gridIsEmpty[i][j] == 3)) {
		    coordinates[0] = i;
		    coordinates[1] = j;
		}

	return coordinates;

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
     * Remove cell from specific location in the Grid
     * 
     * @param xCoord
     *            in grid
     * @param yCoord
     *            in grid
     */
    public void removeCellAtPoint(int xCoord, int yCoord) {
	grid[xCoord][yCoord] = null;
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

    /*
     * 
     * getter and setting methods for dimensions
     */
    public void setWidth(int x) {
	this.gWidth = x;
    }

    public void setHeight(int y) {
	this.gHeight = y;
    }

    public int getWidth() {
	return gWidth;
    }

    public int getHeight() {
	return gHeight;
    }

    public void setType(String type) {
	this.cellType = type;
    }

    public void setAdjacent(int adjacentType) {
	this.adjacentType = adjacentType;
    }

    public String getType() {
	return cellType;
    }

    public int getAdjacentType() {
	return adjacentType;
    }

}
