package simulationObjects;

import java.util.ArrayList;

public class ForestCell extends Cell {

    public enum State {
	EMPTY, TREE, ONFIRE
    }

    public ForestCell() {

    }

    @Override
    public Patch update(Patch currentPatch, ArrayList<Patch> neighbors) {
	// TODO Auto-generated method stub
        return null;
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
    public boolean needUpdate(ArrayList<Cell> neighbors) {
	// TODO Auto-generated method stub
	return false;
    }

}
