package simulationObjects;

import java.util.ArrayList;
import javafx.scene.paint.Color;
import controller.GridInfo;

public class SegCell extends Cell {

    private GridInfo object = new GridInfo();

    public double t = 0;

    final private int EMPTY = 0;
    final private int RED = 1;
    final private int BLUE = 2;

    public SegCell() {
        super();
    }

    @Override
    public Patch update(Patch currentPatch, ArrayList<Patch> neighbors) {
	System.out.println(neighbors);
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
        if (state == EMPTY){
            setFill(Color.WHITE);
        }
        else if (state == RED){
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
