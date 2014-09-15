package simulationObjects;

import java.util.ArrayList;

public class SegCell extends Cell {

    public double t = 0;

    public enum State {
	EMPTY, X, O
    }

    public SegCell() {

    }

    @Override
    public void update(ArrayList<Cell> neighbors) {
	if (needUpdate(neighbors)) {
	    myState = 0;

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

    public boolean needUpdate(ArrayList<Cell> neighbors) {
	int satisfiedNeighbor = 0, dissatisfiedNeighbor = 0;
	for (Cell neighborCell : neighbors) {
	    if (myState == neighborCell.getState())
		satisfiedNeighbor++;
	    else if (neighborCell.getState() != 0)
		dissatisfiedNeighbor++;
	}

	return (satisfiedNeighbor / dissatisfiedNeighbor < t);

    }

    @Override
    public int getState() {
	return myState;
    }

    @Override
    public void setState(int state) {
	myState = state;
    }

    @Override
    public void prepareToUpdate(ArrayList<Cell> neighbors) {

    }

}
