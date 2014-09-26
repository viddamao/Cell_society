package controller;


import java.util.List;
import simulationObjects.Patch;
/**
 * Parent class for the grid's edge rules
 * @author Will Chang
 *
 */
public abstract class GridEdgeRules {

    protected int xUpperBound;
    protected int yUpperBound;
    protected Grid grid;

    /**
     * Constructor for the grid's edge rules
     * @param x upper bound
     * @param y upper bound
     * @param g grid reference
     */
    public GridEdgeRules(int x, int y, Grid g)
    {
        xUpperBound = x;
        yUpperBound = y;
        grid = g;
    }
    
    /**
     * Checks if a location is not in the grid
     * @param xCoord in grid
     * @param yCoord in grid
     * @return true if out of bounds, false otherwise
     */
    public boolean isOutOfBounds(int xCoord, int yCoord) {
        return xCoord > xUpperBound - 1 
                || xCoord < 0 
                || yCoord > yUpperBound - 1
                || yCoord < 0;
    }
    
    /**
     * Method to apply each subclass's specific set of rules
     * @param nextX coordinate to check for a neighbor
     * @param nextY coordinate to check for a neighbor
     * @param neighbors list of all potential neighbors
     */
    public abstract void applyRulesAndGetNeighbors(int nextX, int nextY,
                                    List<Patch> neighbors);    
    

   
}
