package simulationObjects;

import java.util.ArrayList;
import javafx.scene.paint.Color;
import controller.GridInfo;

public class SegCell extends Cell {

    private GridInfo object = new GridInfo();

    public double t = 0;

    final private int EMPTY = 0;
    final private int RED = 1;
    final private int BLUE = 2;

    public SegCell() {
	super();
    }

    @Override
    public Patch update(Patch currentPatch, ArrayList<Patch> neighbors) {
	currentPatch.setPreviousState(myState);
	if (needUpdate(neighbors)) {
	    prepareToUpdate(currentPatch, neighbors);
	    return null;
	} else {
	    return currentPatch;
	}
    }

    /**
     * 
     * Check the update condition
     * 
     * return true if not satisfied
     * 
     * @param cell
     *            state for current cell
     * 
     * @param neighbors
     *            arraylist for neighbor cells
     * 
     * 
     */

    @Override
    public boolean needUpdate(ArrayList<Patch> neighbors) {
	int satisfiedNeighbor = 0, dissatisfiedNeighbor = 0;
	for (Patch neighborPatch : neighbors) {
	    if (myState == (neighborPatch.getPreviousState()))
		satisfiedNeighbor++;
	    else if (!(neighborPatch.isEmpty()))
		dissatisfiedNeighbor++;
	}

	return (satisfiedNeighbor / dissatisfiedNeighbor < object.getParam());

    }

    @Override
    public int getState() {
	return myState;
    }

    @Override
    public void setState(int state) {
	if (state == EMPTY) {
	    setFill(Color.WHITE);
	} else if (state == RED) {
	    setFill(Color.RED);
	} else if (state == BLUE) {
	    setFill(Color.BLUE);
	}
	myState = state;
    }

    public void prepareToUpdate(Patch currentPatch, ArrayList<Patch> neighbors) {

	for (int i = 0; i < neighbors.size(); i++)

	    if (neighbors.get(i).isEmpty()) {
		neighbors.get(i).setPreviousState(
			neighbors.get(i).getCell().myState);
		neighbors.get(i).addCell(this);

	    }
    }

}
