package UnitTest;

import static org.junit.Assert.assertEquals;
import javafx.scene.paint.Color;

import org.junit.Test;

import simulationObjects.SugarCell;

public class SugarCellTester {

    @Test
    public void testCellCreation() {
	SugarCell mySugarCell = new SugarCell();
	assertEquals(mySugarCell, mySugarCell);
    }

    @Test
    public void testColorInitialization() {
	SugarCell mySugarCell = new SugarCell();
	assertEquals(Color.WHITE, mySugarCell.getInitialColors().get(0));
	assertEquals(Color.BROWN, mySugarCell.getInitialColors().get(5));
    }

    @Test
    public void testGetState() {
	SugarCell mySugarCell = new SugarCell();
	assertEquals(0, mySugarCell.getState());
    }

    @Test
    public void testNextState() {
	SugarCell mySugarCell = new SugarCell();
	assertEquals(1, mySugarCell.getNextState());

    }

    @Test
    public void testSetStates() {
	SugarCell mySugarCell = new SugarCell();
	mySugarCell.setState(1);
	assertEquals(1, mySugarCell.getState());
    }

    @Test
    public void testStateInitialization() {
	SugarCell mySugarCell = new SugarCell();
	assertEquals("AGENT", mySugarCell.getStateTypes().get(5));
    }
}
