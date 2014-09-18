package simulationObjects;

import java.util.ArrayList;
import javafx.scene.paint.Color;
import controller.GridInfo;

public class SegCell extends Cell {

    private GridInfo object = new GridInfo();

    public double t = 0;

    final private int BLUE = 0;
    final private int RED = 1;
    
    final private double SATISFIED_RATIO = 0.3;

    public SegCell() {
        super();
    }

    @Override
    public Patch update(Patch currentPatch, ArrayList<Patch> neighbors) {
	if (isSatisfied(neighbors)){
	    return currentPatch;
	}
	else{
	    return currentPatch.randomEmptyPatch();
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
    
    private boolean isSatisfied(ArrayList<Patch> neighbors){
        double satisfiedCount = 0;
        double dissatisfiedCount = 0;
        for (Patch p: neighbors){
            if (p.getCell() != null){
                if(p.getCell().getState() == myState){
                    satisfiedCount++;
                }
                else{
                    dissatisfiedCount++;
                }
            }
        }
        System.out.println(satisfiedCount);
        System.out.println("d"+dissatisfiedCount);
        if (satisfiedCount+dissatisfiedCount > 0){
            return satisfiedCount/(satisfiedCount+dissatisfiedCount) > SATISFIED_RATIO;
        }
        else{
            return false;
        }
    }

    @Override
    public int getState() {
	return myState;
    }

    @Override
    public void setState(int state) {
        if (state == RED){
            setFill(Color.RED);
        }
        else if (state == BLUE){
            setFill(Color.BLUE);
        }
	myState = state;
    }

    @Override
    public void prepareToUpdate(ArrayList<Cell> neighbors) {

    }

}
