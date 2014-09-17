package simulationObjects;

import java.util.ArrayList;

import controller.GridInfo;

public class SegCell extends Cell {

    private GridInfo object = new GridInfo();

    public double t = 0;

    public enum State {
	X, O
    }

    public SegCell() {
        super();
    }

    @Override
    public Patch update(Patch currentPatch, ArrayList<Patch> neighbors) {
	/*
	 * if (needUpdate(neighbors)) { myState = 0;
	 * 
	 * }
	 */
	return null;
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
    public boolean needUpdate(ArrayList<Cell> neighbors) {
	int satisfiedNeighbor = 0, dissatisfiedNeighbor = 0;
	for (Cell neighborCell : neighbors) {
	    if (myState == neighborCell.getState())
		satisfiedNeighbor++;
	    else if (neighborCell.getState() != 0)
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
	myState = state;
    }

    @Override
    public void prepareToUpdate(ArrayList<Cell> neighbors) {

    }

}
