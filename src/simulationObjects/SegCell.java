package simulationObjects;

import java.util.ArrayList;

public class SegCell extends Cell {

    public enum State {
	EMPTY, X, O
    }

    public SegCell() {

    }

    @Override
    public void update(ArrayList<Cell> neighbors) {
	// TODO Auto-generated method stub

    }

    @Override
    public void prepareToUpdate() {

    }

    @Override
    public int getState() {
	return myState;
    }

    @Override
    public void setState(int state) {
	myState = state;
    }

}
