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


    final private int BLUE = 1;
    final private int RED = 2;
    

    public SegCell() {
	super();
    }
    
    /**
     * make changes to the cells position, if needed
     */
    @Override
    public Patch update(Patch currentPatch, ArrayList<Patch> neighbors) {
	if (isSatisfied(currentPatch,neighbors)){
	    return currentPatch;
	}
	else{
	    currentPatch.randomEmptyPatch().addCell(this);;
	    return null;
	}
    }
    
    /**
     * Determines whether the cell is satisfied given its neighbors
     * 
     * @param currentPatch
     * the patch the cell is in
     * @param neighbors
     * the neighbors of the cell's patch
     * @return
     * satisfied or not
     */
    private boolean isSatisfied(Patch currentPatch, ArrayList<Patch> neighbors){
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
        if (satisfiedCount+dissatisfiedCount > 0){
            return satisfiedCount/(satisfiedCount+dissatisfiedCount) > infoSheet.getParam();
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
	if (state == RED) {
	    setFill(Color.RED);
	} else if (state == BLUE) {
	    setFill(Color.BLUE);
	}
	myState = state;
    }

    /**
     * this simulation does not require the inherited prepare to update method
     */
    public void prepareToUpdate(Patch currentPatch, ArrayList<Patch> neighbors) {
        return;
    }

}
