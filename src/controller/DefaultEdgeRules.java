package controller;

import java.util.ArrayList;
import simulationObjects.Patch;

public class DefaultEdgeRules extends GridEdgeRules {

    public DefaultEdgeRules (int x, int y, Grid g) {
        super(x, y, g);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void applyBoundaryRulesToNeighbors (int nextX, int nextY, ArrayList<Patch> neighbors) {
        if(!isOutOfBounds(nextX,nextY))
        {
            neighbors.add(grid.getPatchAtPoint(nextX,nextY));
        }

    }

}
