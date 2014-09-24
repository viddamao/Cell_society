package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import simulationObjects.Patch;

/**
 * Manages grid properties and contents
 *
 * @author Will Chang
 *
 */
public class Grid extends Pane {

    private Patch[][] gridArray;
    private int[] xDelta = { -1, 1, 0, 0, 1, 1, -1, -1 };
    private int[] yDelta = { 0, 0, 1, -1, 1, -1, 1, -1 };
    private int gWidth;
    private int gHeight;
    private GridInfo infoSheet = new GridInfo();
    private int myMode;
    private final int TOROIDAL = 1;
    private final int INFINITE = 2;
    private HashMap<Integer,Integer> cellCounts;
    
    // private String patchType;
    // {4,8} indicates adjacent type to be 4 or 8 blocks around
    // private int adjacentType;

    /**
     * Constructs the Grid, initiates private variables
     *
     * @param width
     *            of the grid
     * @param height
     *            of the grid
     */
    public Grid(int width, int height) {
	gridArray = new Patch[width][height];
	gWidth = width;
	gHeight = height;
	initializeCellCounts();
	myMode = 0; //Default is bounded.
    }

    public void initializeCellCounts()
    {
        cellCounts = new HashMap();
        for(Integer i = 1; i <= infoSheet.getMaxCellState(); i++)
        {
            cellCounts.put(i, 0);
        }
    }
    // TODO Duplicated for loop...
    /**
     * Makes a step in the simulation, updates everything sequentially
     */
    public void updateGrid() {
	for (Patch[] row : gridArray) {
	    for (Patch p : row) {
		p.prepareToUpdate();
	    }
	}

	for (Patch[] row : gridArray) {
	    for (Patch p : row) {
		p.update();
		if(!p.isEmpty())
		{
		    int state = p.getCell().getState();
		   // cellCounts.put(state, cellCounts.get(state) + 1);
		}
	    }
	}

    }
    
    public HashMap<Integer,Integer> getCellCounts()
    {
        return cellCounts;
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
	gridArray[patch.getGridX()][patch.getGridY()] = patch;
	this.addPatchToGrid(patch, patch.getGridX(), patch.getGridY());
    }

    private void addPatchToGrid(Patch patch, int gridX, int gridY) {
	patch.createBody();
	getChildren().add(patch);
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
	gridArray[xCoord][yCoord].removeCell();
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
	for (int i = 0; i < infoSheet.getAdjacentType(); i++) {
	    int nextX = xCoord + xDelta[i];
	    int nextY = yCoord + yDelta[i];

	    if (isOutOfBounds(nextX, nextY)) {
		applyBoundaryRules(nextX, nextY, neighbors);
	    } else {
		neighbors.add(gridArray[nextX][nextY]);
	    }
	}
	return neighbors;
    }

    private void applyBoundaryRules(int nextX, int nextY,
	    ArrayList<Patch> neighbors) {
	if (myMode == 1) {
	    processAndAddToroidal(nextX, nextY, neighbors);
	} else if (myMode == INFINITE) {
	    processAndAddInfinite(nextX, nextY, neighbors);
	}
    }

    private void processAndAddInfinite(int nextX, int nextY,
	    ArrayList<Patch> neighbors) {

    }

    private void processAndAddToroidal(int nextX, int nextY,
	    ArrayList<Patch> neighbors) {
	nextX = wrapCoordAround(nextX, gWidth);
	nextY = wrapCoordAround(nextY, gHeight);
	neighbors.add(gridArray[nextX][nextY]);
    }

    // TODO add min/change for infinite
    private int wrapCoordAround(int coord, int max) {
	if (coord > max - 1) {
	    coord = 0;
	} else if (coord < 0) {
	    coord = max - 1;
	}
	return coord;
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

    /**
     * get patch in our grid system
     *
     * @param i
     * @param j
     * @return
     */
    public Patch getPatchAtPoint(int i, int j) {
	return gridArray[i][j];
    }

    // TODO duplicate method??
    /**
     * get patch given a scene coordinate
     *
     * @param i
     * @param j
     * @return
     */
    public Patch getPatchAtCoordinate(int i, int j) {
	for (Patch[] row : gridArray) {
	    for (Patch p : row) {
		if (p.getBoundsInParent().contains(i, j))
		    return p;
	    }
	}
	return null;
    }

    public int getGridWidth() {
	return gWidth;
    }

    public int getGridHeight() {
	return gHeight;
    }

    public void setAndUpdateMode(int mode) {
	myMode = mode;
	updateAllNeighborhoods();
    }

    // TODO Look this over and change implementation -Will
    /**
     * Finds empty Patch
     *
     * @return an empty Patch
     */
    public Patch findEmptyPatch() {
	ArrayList<Patch> emptyPatches = new ArrayList<Patch>();
	for (Patch[] row : gridArray) {
	    for (Patch p : row) {
		if (p.getCell() == null) {
		    emptyPatches.add(p);
		}
	    }
	}
	if (emptyPatches.size() == 0)
	    return null;
	else {
	    int random = new Random().nextInt(emptyPatches.size());
	    return emptyPatches.get(random);
	}
    }

    public void updateAllNeighborhoods() {
	for (Patch[] row : gridArray) {
	    for (Patch p : row) {
		p.getNeighbors();
	    }
	}
    }

    public void updateBackgroundColor(Color myColor) {
	for (Patch[] row : gridArray) {
	    for (Patch p : row) {
		p.setColorToBody(myColor);
	    }
	}
    }
}
