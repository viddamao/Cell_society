package simulationObjects;

import java.util.ArrayList;

import javafx.scene.paint.Color;
import controller.GridInfo;

public class ForestCell extends Cell {


    final private int EMPTY = 0;
    final private int ONFIRE = 1;
    final private int TREE = 2;

    public ForestCell() {
	super();
    }

    @Override
    public Patch update(Patch currentPatch, ArrayList<Patch> neighbors) {
	switch (myState) {
	case TREE:

	    if (willCatchFire(neighbors))
		catchFire(currentPatch);

	case ONFIRE:
	    burnDown(currentPatch);
	}
	return currentPatch;
    }

    private void burnDown(Patch currentPatch) {
	this.setState(EMPTY);
    }

    public void catchFire(Patch currentPatch) {
	this.setState(ONFIRE);
    }

    @Override
    public int getState() {
	return myState;
    }

    @Override
    public void setState(int state) {
	if (state == EMPTY) {
	    setFill(Color.WHITE);
	} else if (state == ONFIRE) {
	    setFill(Color.RED);
	} else if (state == TREE) {
	    setFill(Color.GREEN);
	}
	myState = state;
    }

    public boolean willCatchFire(ArrayList<Patch> neighbors) {
	boolean haveNeighborOnFire = false;
	for (Patch neighborPatch : neighbors)
	    if (neighborPatch.getPreviousState() == ONFIRE) {
		haveNeighborOnFire = true;
		break;
	    }
	return ((haveNeighborOnFire) && (Math.random() <= infoSheet.getParam()));

    }

    @Override
    public void prepareToUpdate(Patch currentPatch, ArrayList<Patch> neighbors) {
	currentPatch.setPreviousState(myState);
    }


}
