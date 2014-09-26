package controller;

import java.util.List;
import simulationObjects.Patch;
/**
 * Default grid rules.
 * @author Will Chang
 *
 */
public class DefaultEdgeRules extends GridEdgeRules {

    public DefaultEdgeRules (int x, int y, Grid g) {
        super(x, y, g);
    }

    /**
     * Default rules for a finite grid
     */
    @Override
    public void applyRulesAndGetNeighbors (int nextX, int nextY, List<Patch> neighbors) {
        if (!isOutOfBounds(nextX, nextY)) {
            neighbors.add(grid.getPatchAtPoint(nextX, nextY));
        }
    }

}
