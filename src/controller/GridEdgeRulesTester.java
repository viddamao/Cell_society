package controller;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;
import simulationObjects.Patch;
public class GridEdgeRulesTester {

/**
 * Tester for The GridEdgeRules Class
 */
    
    @Test
    public void testThatRulesAreNotNull () {
        GridEdgeRules rules = new DefaultEdgeRules(0,0,new Grid(0,0));
        assertEquals(rules, rules);
    }
    @Test
    public void testThatRulesAreRules () {
        GridEdgeRules rules = new DefaultEdgeRules(0,0,new Grid(0,0));
        GridEdgeRules rules1 = new ToroidalEdgeRules(0,0,new Grid(0,0));
        assertEquals(rules, rules);
        assertEquals(rules1, rules1);
        assertEquals(rules.getClass().getSuperclass(), rules1.getClass().getSuperclass());
    }
    @Test
    public void testWrappingCoordinates () {
        int x = 0;
        int y = 0;
        ToroidalEdgeRules rules = new ToroidalEdgeRules(x,y,new Grid(x,y));
        assertEquals(rules.wrapCoordAround(x,0), 0);
        assertEquals(rules.wrapCoordAround(0,y), 0);
    }
    
    @Test
    public void testIsOutOfBounds () {
        GridEdgeRules rules = new DefaultEdgeRules(0,0,new Grid(0,0));
        assertEquals(rules.isOutOfBounds(0, 0), true);
    }
    
    @Test
    public void testIsOutOfBounds2 () {
        GridEdgeRules rules = new DefaultEdgeRules(0,0,new Grid(1,1));
        assertEquals(rules.isOutOfBounds(1, 1), true);
    }
    @Test
    public void testIsOutOfBounds3 () {
        GridEdgeRules rules = new DefaultEdgeRules(2,2,new Grid(2,2));
        assertEquals(!rules.isOutOfBounds(0,0), true);
    }
    @Test
    public void testApplyRulesAndGetNeighbors () {
        Grid g = new Grid(3,3);
        GridEdgeRules rules = new DefaultEdgeRules(0,0,g);
        g.addPatchAtPoint(new Patch(0,0,g));
        g.addPatchAtPoint(new Patch(1,0,g));
        g.addPatchAtPoint(new Patch(0,1,g));
        g.addPatchAtPoint(new Patch(1,1,g));
        List<Patch> neighbors = g.getNeighborsAround(0,0);
        int comparedSize = neighbors.size();
        neighbors = rules.applyRulesAndGetNeighbors(0, 0, neighbors);
        assertEquals(neighbors.size(), comparedSize);
    }
}
