package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import simulationObjects.Patch;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

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
    private int totalCells;
    private GridEdgeRules myEdgeRules;
    
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
     * @param rules Edge rules for the grid
     */

    

    
    public Grid(int width, int height) {
        gridArray = new Patch[width][height];
        gWidth = width;
        gHeight = height;
        myEdgeRules = new DefaultEdgeRules(width, height, this);
        totalCells = 0;
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
		totalCells = 0;
	    }
	}
	
	//Reset state count
	Set<Integer> s = cellCounts.keySet();
	for(Integer i : s)
	{
	    cellCounts.put(i, 0);
	}
	for (Patch[] row : gridArray) {
	    for (Patch p : row) {
		p.update();
		if(!p.isEmpty())
		{
		    int state = p.getCell().getState();
		    cellCounts.put(state, cellCounts.get(state) + 1);
		    totalCells++;
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
	patch.createBody(0);
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
	    myEdgeRules.applyConditionsAndGetNeighbors(nextX, nextY, neighbors);
	}
	
	    
	    
        
	return neighbors;
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

    /**
     * get patch given a scene coordinate
     * this is with respect to the scene (pixels), NOT to the grid
     *
     * @param i
     * the explicit pixel x location
     * @param j
     * the explicit pixel y location
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

    //TODO deprecated
    public void setEdgeRules(int mode) {
	myMode = mode;
	updateAllNeighborhoods();
    }

    // TODO Look this over and change implementation -Will
    //deprecated
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

    public void changeRulesTo(GridEdgeRules rules)
    {
        myEdgeRules = rules;
        updateAllNeighborhoods();
    }
    
    public void updateBackgroundColor(Color myColor) {
	for (Patch[] row : gridArray) {
	    for (Patch p : row) {
		p.setColorToBody(myColor);
	    }
	}
    }
    public int getTotalCells()
    {
        return totalCells;
    }

    /**
     * create a patch body for each cell, given the user selection
     * 
     * @param i
     * 
     */
    public void setPatchBody (int i) {
        for (Patch[] row : gridArray) {
            for (Patch p : row) {
                p.createBody(i);
            }
        }
    }
}
