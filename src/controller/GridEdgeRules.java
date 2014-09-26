package controller;

import java.util.ArrayList;
import simulationObjects.Patch;

public abstract class GridEdgeRules {

    protected int xUpperBound;
    protected int yUpperBound;
    protected Grid grid;

    public GridEdgeRules(int x, int y, Grid g)
    {
        xUpperBound = x;
        yUpperBound = y;
        grid = g;
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
    public boolean isEdgeCase(int xCoord, int yCoord)
    {
        return xCoord == 0
                || yCoord == 0
                ||xCoord == xUpperBound - 1
                ||yCoord == yUpperBound - 1;
    }

    public boolean isOutOfBounds(int xCoord, int yCoord) {
        return xCoord > xUpperBound - 1 
                || xCoord < 0 
                || yCoord > yUpperBound - 1
                || yCoord < 0;
    }
    public abstract void applyConditionsAndGetNeighbors(int nextX, int nextY,
                                    ArrayList<Patch> neighbors);    
    

   
}
