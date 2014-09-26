package controller;

import java.util.ArrayList;
import java.util.List;
import simulationObjects.Patch;
/**
 * Toroidal grid rules
 * @author Will Chang
 *
 */
public class ToroidalEdgeRules extends GridEdgeRules {

    public ToroidalEdgeRules (int x, int y, Grid g) {
        super(x, y, g);
        // TODO Auto-generated constructor stub
    }
    

    /**
     * Sets up the Toroidal edge rules
     */
    @Override
    public void applyRulesAndGetNeighbors (int nextX, int nextY, List<Patch> neighbors) {
        if(isOutOfBounds(nextX,nextY))
        {
            nextX = wrapCoordAround(nextX, xUpperBound);
            nextY = wrapCoordAround(nextY, yUpperBound);
        }
        neighbors.add(grid.getPatchAtPoint(nextX,nextY)); 
        
    }
    
    /**
     * Wraps a coordinate around the edges of the grid.
     * @param coord out of bounds coordinate to wrap around
     * @param max boundary reference
     * @return the wrapped around coordinate
     */
    private int wrapCoordAround(int coord, int max) {
        if (coord > max - 1) {
            coord = 0;
        } else if (coord < 0) {
            coord = max - 1;
        }       
        return coord;
    }

}
