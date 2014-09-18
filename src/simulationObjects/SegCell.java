package simulationObjects;

import java.util.ArrayList;
import javafx.scene.paint.Color;
import controller.GridInfo;

public class SegCell extends Cell {


    final private int BLUE = 1;
    final private int RED = 2;
    

    public SegCell() {
	super();
    }

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

    public void prepareToUpdate(Patch currentPatch, ArrayList<Patch> neighbors) {
        return;
    }

}
