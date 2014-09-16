package controller;


import java.util.ArrayList;
import simulationObjects.Patch;


public class GridManager {

    private Patch[][] grid;
    //private int[][] gridIsEmpty; // 1 for moved patchs; 0 for patchs not moving ;3
				 // for empty
    //private int[] availableCoord;
    private int[] xDelta = { -1, -1, -1, 0, 0, 1, 1, 1 };
    private int[] yDelta = { -1, 0, 1, -1, 1, -1, 0, 1 };
    private int gWidth;
    private int gHeight;
    //private String patchType;
    // {4,8} indicates adjacent type to be 4 or 8 blocks around
    //private int adjacentType;

    /**
     * Constructs the GridManager, initiates private variables
     * 
     * @param width
     *            of the grid
     * @param height
     *            of the grid
     */
    public GridManager(int width, int height) {
	grid = new Patch[width][height];
	gWidth = width;
	gHeight = height;
    }

    /*
     * public void fillGrid(int PatchType) { for(Patch[] row : grid){
     * Arrays.fill(row, new PredatorPatch()); } }
     */

    /**
     * Makes a step in the simulation, updates everything sequentially
     */
    public void step() {
	for (Patch[] row : grid) {
	    for (Patch p : row) {
		p.update();
	    }
	}
    }

   
    /**
     * 
     * 
     * 
     * 
     * @return first empty grid patch coordinate
     */
    /*private int[] findFirstEmptyPatch() {
	int[] coordinates = new int[2];
	for (int i = 0; i < gWidth; i++)
	    for (int j = 0; j < gHeight; j++)
		if ((gridIsEmpty[i][j] == 1) || (gridIsEmpty[i][j] == 3)) {
		    coordinates[0] = i;
		    coordinates[1] = j;
		}

	return coordinates;

    }*/

    /**
     * Adds patch in specific location in the Grid
     * 
     * @param patch
     *            to be added
     * @param xCoord
     *            in grid
     * @param yCoord
     *            in grid
     */
    public void addPatchAtPoint(Patch patch) {
	
        grid[patch.getX()][patch.getY()] = patch;
    }

    /**
     * Remove patch from specific location in the Grid
     * 
     * @param xCoord
     *            in grid
     * @param yCoord
     *            in grid
     */
    public void removeCellinPatch(int xCoord, int yCoord) {
	grid[xCoord][yCoord].removeCell();
    }

    
    //TODO Needs to be updated for unique boundary conditions.
    /**
     * Retrieves all neighboring patchs around a point
     * 
     * @param xCoord
     * @param yCoord
     * @return
     */
    public ArrayList<Patch> getNeighborsAround(int xCoord, int yCoord) {
	ArrayList<Patch> neighbors = new ArrayList<>();
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
    /*public void setWidth(int x) {
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
    }*/

}
