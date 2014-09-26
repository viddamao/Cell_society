package controller;

import java.util.ArrayList;
import simulationObjects.Patch;

public class ToroidalEdgeRules extends GridEdgeRules {

    public ToroidalEdgeRules (int x, int y, Grid g) {
        super(x, y, g);
        // TODO Auto-generated constructor stub
    }
    
   /* nextX = wrapCoordAround(nextX, xUpperBound);
    nextY = wrapCoordAround(nextY, yUpperBound);
    neighbors.add(grid.getPatchAtPoint(nextX,nextY));*/
    
    private int wrapCoordAround(int coord, int max) {
        if (coord > max - 1) {
            coord = 0;
        } else if (coord < 0) {
            coord = max - 1;
        }       
        return coord;
    }

    @Override
    public void applyConditionsAndGetNeighbors (int nextX, int nextY, ArrayList<Patch> neighbors) {
        if(isOutOfBounds(nextX,nextY))
        {
            nextX = wrapCoordAround(nextX, xUpperBound);
            nextY = wrapCoordAround(nextY, yUpperBound);
        }
        neighbors.add(grid.getPatchAtPoint(nextX,nextY));     
    }


}
