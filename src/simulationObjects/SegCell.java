package simulationObjects;

import java.util.ArrayList;

public class SegCell extends Cell {
    
    public static double t=0;
    
    public enum State {
	EMPTY, X, O
    }

    public SegCell() {

    }
    
    @Override
    public void update(ArrayList<Cell> neighbors) {
	// TODO Auto-generated method stub

    }

    
    
    /**
     * 
     * Check the update condition
     * 
     * return true if not satisfied
     * 
     * @param cell
     * 	 state for current cell
     * 
     * @param neighbors
     * 	 arraylist for neighbor cells
     * 
     * @see simulationObjects.Cell#prepareToUpdate(int, java.util.ArrayList)
     */
    @Override
    public boolean prepareToUpdate(int myState,ArrayList<Cell> neighbors) {
	int satisfiedNeighbor=0,dissatisfiedNeighbor=0;
	for (Cell neighborCell:neighbors){
	   if (myState==neighborCell.getState())
	       satisfiedNeighbor++;
	   else
	       if (neighborCell.getState()!=0) dissatisfiedNeighbor++;
	}
	
	
	return (satisfiedNeighbor/dissatisfiedNeighbor<t);
	
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
