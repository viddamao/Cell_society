package simulationObjects;

import java.util.ArrayList;

import javafx.scene.paint.Color;

/**
 * Cell for the segregation simulation
 * 
 * @author Davis Gossage
 *
 */

public class SegCell extends Cell {

    final private int STATE_X = 1;
    final private int STATE_O = 2;

    public SegCell() {
	super();
    }

    /**
     * make changes to the cells position, if needed
     */
    @Override
    public void update (Patch currentPatch, ArrayList<Patch> neighbors) {
        if (!isSatisfied(currentPatch, neighbors)) {
            currentPatch.randomEmptyPatch().addCell(this);
            currentPatch.removeCell();
        }
    }

    /**
     * Determines whether the cell is satisfied given its neighbors
     * 
     * @param currentPatch
     *            the patch the cell is in
     * @param neighbors
     *            the neighbors of the cell's patch
     * @return satisfied or not
     */
    private boolean isSatisfied(Patch currentPatch, ArrayList<Patch> neighbors) {
	double satisfiedCount = 0;
	double dissatisfiedCount = 0;
	currentPatch.myCell.setState(myState);
	for (Patch p : neighbors) {
	    if (p.getCell() != null) {
		if (p.getCell().getState() == myState) {
		    satisfiedCount++;
		} else {
		    dissatisfiedCount++;
		}
	    }
	}
	if (satisfiedCount + dissatisfiedCount > 0) {
	    return satisfiedCount / (satisfiedCount + dissatisfiedCount) > infoSheet
		    .getParam();
	} else {
	    return false;
	}
    }

    @Override
    public int getState() {
	return myState;
    }

    @Override
    public void setState(int state) {
	if (state == STATE_X) {
	    setFill(infoSheet.getColor("X"));
	} else if (state == STATE_O) {
	    setFill(infoSheet.getColor("O"));
	}
	myState = state;
    }

    /**
     * this simulation does not require the inherited prepare to update method
     */
    public void prepareToUpdate(Patch currentPatch, ArrayList<Patch> neighbors) {
	return;
    }

    @Override
    public ArrayList<String> getStateTypes() {
	ArrayList<String> myStateType = new ArrayList<String>();
	myStateType.add("X");
	myStateType.add("O");
	return myStateType;
    }

    @Override
    public ArrayList<Color> getInitialColors() {
	ArrayList<Color> myStateColors = new ArrayList<Color>();
	myStateColors.add(Color.RED);
	myStateColors.add(Color.BLUE);
	return myStateColors;
    }

}
