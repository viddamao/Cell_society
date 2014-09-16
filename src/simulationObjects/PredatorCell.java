package simulationObjects;

import java.util.ArrayList;

public class PredatorCell extends Cell {
    //This is it's pregnancylevel
    private int pregnancyLevel = 0;
    //Birthmark
    private int maxState =9;

    public enum State {
	EMPTY
    }

    public PredatorCell() {

    }

    @Override
    public Patch update(ArrayList<Patch> neighbors) {
	// TODO Auto-generated method stub
        return null;
        
    }

    @Override
    public void prepareToUpdate(ArrayList<Cell> neighbors) {

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
