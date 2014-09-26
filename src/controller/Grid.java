package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;
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
    private GridInfo infoSheet;
    private HashMap<Integer, Integer> cellCounts;
    private int totalCells;
    private GridEdgeRules myEdgeRules;
    private List<Patch> emptyPatches;

    /**
     * Constructs the Grid, initiates private variables
     *
     * @param width
     *        of the grid
     * @param height
     *        of the grid
     * @param rules Edge rules for the grid
     */

    public Grid (int width, int height) {
        gridArray = new Patch[width][height];
        gWidth = width;
        gHeight = height;
        infoSheet = new GridInfo();
        myEdgeRules = new DefaultEdgeRules(width, height, this);
        totalCells = 0;

    }

    public void initializeCellCounts ()
    {
        cellCounts = new HashMap();
        for (Integer i = 1; i <= infoSheet.getMaxCellState(); i++){
            cellCounts.put(i, 0);
        }
    }

    /**
     * Makes a step in the simulation, updates everything sequentially
     */
    public void updateGrid () {
        prepareToUpdateAllPatches();
        resetCellCounts();
        updateAllPatches();
    }

    private void updateAllPatches () {
        for (Patch[] row : gridArray) {
            for (Patch p : row) {
                p.update();
                countCellInPatch(p);
            }
        }
    }

    private void prepareToUpdateAllPatches () {
        for (Patch[] row : gridArray) {
            for (Patch p : row) {
                p.prepareToUpdate();
            }
        }
    }

    private void countCellInPatch (Patch p) {
        if (!p.isEmpty())
        {
            int state = p.getCell().getState();
            cellCounts.put(state, cellCounts.get(state) + 1);
            totalCells++;
        }
    }

    private void resetCellCounts () {
        Set<Integer> s = cellCounts.keySet();
        for (Integer i : s){
            cellCounts.put(i, 0);
        }
        totalCells = 0;
    }

    public HashMap<Integer, Integer> getCellCounts ()
    {
        return cellCounts;
    }

    /**
     * Adds patch in specific location in the Grid
     *
     * @param patch
     *        to be added
     * @param xCoord
     *        in grid
     * @param yCoord
     *        in grid
     */
    public void addPatchAtPoint (Patch patch) {
        gridArray[patch.getGridX()][patch.getGridY()] = patch;
        addPatchToGrid(patch, patch.getGridX(), patch.getGridY());
    }

    private void addPatchToGrid (Patch patch, int gridX, int gridY) {
        patch.createBody(0);
        getChildren().add(patch);
    }

    /**
     * Remove cell from specific location in the Grid
     *
     * @param xCoord
     *        in grid
     * @param yCoord
     *        in grid
     */
    public void removeCellinPatch (int xCoord, int yCoord) {
        gridArray[xCoord][yCoord].removeCell();
    }

    /**
     * Retrieves all neighboring patches around a point
     *
     * @param xCoord
     * @param yCoord
     * @return
     */
    public List<Patch> getNeighborsAround (int xCoord, int yCoord) {
        List<Patch> neighbors = new ArrayList<Patch>();
        for (int i = 0; i < infoSheet.getAdjacentType(); i++) {
            int nextX = xCoord + xDelta[i];
            int nextY = yCoord + yDelta[i];
            myEdgeRules.applyRulesAndGetNeighbors(nextX, nextY, neighbors);
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
    public Patch getPatchAtPoint (int i, int j) {
        return gridArray[i][j];
    }

    /**
     * get patch given a scene coordinate
     * this is with respect to the scene (pixels), NOT to the grid
     *
     * @param i the explicit pixel x location
     * @param j the explicit pixel y location
     * @return
     */
    public Patch getPatchAtCoordinate (int i, int j) {
        for (Patch[] row : gridArray) {
            for (Patch p : row) {
                if (p.getBoundsInParent().contains(i, j)) { return p; }
            }
        }
        return null;
    }

    /**
     * Get the grid's width
     * 
     * @return gWidth
     */
    public int getGridWidth () {
        return gWidth;
    }

    /**
     * Get the grid's height
     * 
     * @return gHeight
     */
    public int getGridHeight () {
        return gHeight;
    }

    /**
     * Finds empty Patch
     *
     * @return an empty Patch
     */
    public Patch findEmptyPatch () {
        List<Patch> emptyPatches = new ArrayList<Patch>();
        for (Patch[] row : gridArray) {
            for (Patch p : row) {
                if (p.getCell() == null) {
                    emptyPatches.add(p);
                }
            }
        }
        if (emptyPatches.size() == 0) {
            return null;
        }
        else {
            int random = new Random().nextInt(emptyPatches.size());
            return emptyPatches.get(random);
        }
    }

    /**
     * Updates the neighborhoods of all patches
     * based on the grid's rules
     */
    public void updateAllNeighborhoods () {
        for (Patch[] row : gridArray) {
            for (Patch p : row) {
                p.getNeighbors();
            }
        }
    }

    /**
     * Change current grid's edge rules to new rules
     * 
     * @param rules
     */
    public void changeRulesTo (GridEdgeRules rules)
    {
        myEdgeRules = rules;
        updateAllNeighborhoods();
    }

    /**
     * Updates the background color.
     * 
     * @param myColor
     */
    public void updateBackgroundColor (Color myColor) {
        for (Patch[] row : gridArray) {
            for (Patch p : row) {
                p.setColorToBody(myColor);
            }
        }
    }

    /**
     * Return the total number of cells in the simulation.
     * 
     * @return
     */
    public int getTotalCells ()
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
