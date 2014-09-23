package controller;

import java.util.ArrayList;
import java.util.Random;
import javafx.scene.layout.GridPane;
import simulationObjects.Patch;

/**
 * Manages grid properties and contents
 * 
 * @author Will Chang
 * 
 */
public class GridManager extends GridPane {

    private Patch[][] grid;
    private int[] xDelta = { -1, 1, 0, 0, 1, 1, -1, -1 };
    private int[] yDelta = { 0, 0, 1, -1, 1, -1, 1, -1 };
    private int gWidth;
    private int gHeight;
    private GridInfo infoSheet = new GridInfo();
    private int mode;
    private final int TOROIDAL = 1;
    private final int INFINITE = 2;
    
    // private String patchType;
    // {4,8} indicates adjacent type to be 4 or 8 blocks around
    // private int adjacentType;

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
	mode = 1; //Default is bounded.
    }

    // TODO Duplicated for loop...
    /**
     * Makes a step in the simulation, updates everything sequentially
     */
    public void step() {
	for (Patch[] row : grid) {
	    for (Patch p : row) {
		p.prepareToUpdate();
	    }
	}

	for (Patch[] row : grid) {
	    for (Patch p : row) {
		p.update();
	    }
	}

    }

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
	grid[patch.getGridX()][patch.getGridY()] = patch;
	this.add(patch, patch.getGridX(), patch.getGridY());
    }

    /**
     * Remove cell from specific location in the Grid
     * 
     * @param xCoord
     *            in grid
     * @param yCoord
     *            in grid
     */
    public void removeCellinPatch(int xCoord, int yCoord) {
	grid[xCoord][yCoord].removeCell();
    }

    // TODO Needs to be updated for unique boundary conditions.
    /**
     * Retrieves all neighboring patches around a point
     * 
     * @param xCoord
     * @param yCoord
     * @return
     */
    public ArrayList<Patch> getNeighborsAround(int xCoord, int yCoord) {
	ArrayList<Patch> neighbors = new ArrayList<>();
	// deal with different neighbor settings
	for (int i = 0; i < infoSheet.getAdjacentType(); i++) {
	    int nextX = xCoord + xDelta[i];
	    int nextY = yCoord + yDelta[i];
	    
	    if (!isOutOfBounds(nextX, nextY))
	    {
	        neighbors.add(grid[nextX][nextY]);
	    }
	    else
	    {
	       
	        switch (mode) {
	            case TOROIDAL:
	                processToroidal(nextX, nextY, neighbors);
	                break;
	            case INFINITE:
	                processInfinite(nextX, nextY, neighbors);
	                break;
	            default:
	                break;
	        }
	    }
	    
	}
	return neighbors;
    }

    private void processInfinite (int nextX, int nextY, ArrayList<Patch> neighbors) {
       
        
    }

    private void processToroidal (int nextX, int nextY, ArrayList<Patch> neighbors) {
        // TODO Auto-generated method stub
        if(nextX > gWidth - 1) 
        {
            nextX = 0;
        }
        if(nextX < 0)
        {
            nextX = gWidth - 1;
        }
        if(nextY > gHeight - 1)
        {
            nextX = 0;
        }
        if(nextY < 0)
        {
            nextX = gHeight - 1;
        }
        neighbors.add(grid[nextX][nextY]);
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
    
    private boolean isXOOB(int xCoord, int yCoord) {
        return yCoord > gHeight - 1
                || yCoord < 0;
    }
    
    private boolean isYOOB(int xCoord, int yCoord) {
        return xCoord > gWidth - 1 || xCoord < 0 || yCoord > gHeight - 1
                || yCoord < 0;
    }

    /**
     * get patch in our grid system
     * 
     * @param i
     * @param j
     * @return
     */
    public Patch getPatchAtPoint(int i, int j) {
	return grid[i][j];
    }
    
    /**
     * get patch given a scene coordinate
     * 
     * @param i
     * @param j
     * @return
     */
    public Patch getPatchAtCoordinate(int i, int j){
        for (Patch[] row : grid) {
            for (Patch p : row) {
                if (p.getBoundsInParent().contains(i, j)){
                    return p;
                }
            }
        }
        return null;
    }

    public int getGridWidth() {
	return gWidth;
    }

    
    
    //TODO Look this over and change implementation -Will
    /**
     * Finds empty Patch
     * 
     * @return an empty Patch
     */
    public Patch findEmptyPatch() {
	ArrayList<Patch> emptyPatches = new ArrayList<Patch>();
	for (Patch[] row : grid) {
	    for (Patch p : row) {
		if (p.getCell() == null) {
		    emptyPatches.add(p);
		}
	    }
	}
	if (emptyPatches.size() == 0) {
	    return null;
	} else {
	    int random = new Random().nextInt(emptyPatches.size());
	    return emptyPatches.get(random);
	}
    }

}
