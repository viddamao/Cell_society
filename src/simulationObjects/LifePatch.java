package simulationObjects;

import controller.Grid;

/**
 * Patch that Simulates GameOfLife
 *
 * @author Will Chang
 *
 */
public class LifePatch extends Patch {

    // TODO Comment everything
    // Need to load this through the XML...
    // Edit from Constructor
    private int comfortableAmount = 3;
    private int baselineAmount = 2;

    public LifePatch() {
	super();
    }

    public LifePatch(int x, int y, Grid m) {
	super(x, y, m);

    }

    @Override
    public void prepareToUpdate() {
	processNeighborsAndUpdateState();
    }

    /**
     * Special rules for Game of Life
     */
    public void processNeighborsAndUpdateState() {
	int occupiedNeighbors = countNeighbors();

	if (occupiedNeighbors == comfortableAmount && isEmpty()) {
	    myState = State.GENERATING;
	} else if ((occupiedNeighbors > comfortableAmount || occupiedNeighbors < baselineAmount)
		&& !isEmpty()) {
	    myState = State.EMPTYING;
	}
    }

    public int countNeighbors() {
	int occupiedNeighbors = 0;
	for (Patch p : myNeighbors) {
	    if (!p.isEmpty()) {
		occupiedNeighbors++;
	    }
	}
	return occupiedNeighbors;
    }

    // TODO Update this
    // talk to Kevin about this switch
    // some duplicated code.
    @Override
    public void update() {
	// Update this
	switch (myState) {
	case OCCUPIED:
	    //myCell.setFill(infoSheet.getColor("CELL"));
	    break;
	case GENERATING:
	    Cell generated = new LifeCell(xCoord, yCoord);
	    addCell(generated);
	    myState = State.OCCUPIED;
	    break;
	case EMPTYING:
	    removeCell();
	    break;
	default:
	    break;
	}
    }
    
    @Override
    public void toggleCellState() {
	switch (myState){
	case OCCUPIED:
	    removeCell();
	    break;
	case EMPTY:
	    Cell generated = new LifeCell(xCoord, yCoord);
	    addCell(generated);
	    myState = State.OCCUPIED;
	    break;
	    
	}
    }
}
