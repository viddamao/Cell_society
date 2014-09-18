package simulationObjects;

import java.util.ArrayList;
import java.util.HashMap;

public class SharkCell extends PredatorCell {

    // Temp variable assignments... needs constructor to set actual.
    private int vitality = 5;
    private int timeToBreed = 5;
    private final int maxVitality = 5;
    private int gestationPeriod;

    // TBD based on design of controllers/parsers
    public SharkCell() {

    }

    /**
     * Updates the states of the shark cell
     * 
     * @param current
     *            location of cell
     * @param destination
     *            location of the cell
     */
    @Override
    public void updateStatesandMakeMoves(Patch current, Patch destination) {
	vitality--;
	if (timeToBreed > 0) {
	    timeToBreed--;
	}
	if (destination != null) {
	    if (!destination.isEmpty()) {
		this.feed(destination);
	    }
	    this.makeMove(current, destination);

	    if (timeToBreed == 0) {
		this.leaveEgg(current);
		timeToBreed = gestationPeriod;
	    }
	}
    }

    // Need to specify how much energy it gets
    // from eating fish...
    public void feed(Patch destination) {
	destination.removeCell();
	this.vitality += 10;
    }

    // Refactor...
    public ArrayList<Patch> processPossibleDestinations(
	    ArrayList<Patch> allNeighbors) {

	this.siftNeighbors(allNeighbors);

	ArrayList<Patch> emptyBuffer = new ArrayList<>();
	ArrayList<Patch> fishBuffer = new ArrayList<>();
	for (Patch loc : myNeighbors) {
	    Cell occupant = loc.getCell();
	    if (occupant == null) {
		emptyBuffer.add(loc);
	    } else if (occupant != this) {
		fishBuffer.add(loc);
	    }
	}
	if (fishBuffer.size() > 0) {
	    return fishBuffer;
	}
	return emptyBuffer;
    }

    /**
     * REFACTOR DESIGN OF CELL CLASS
     */
    @Override
    public void prepareToUpdate(Patch currentPatch, ArrayList<Patch> neighbors) {
	// TODO Auto-generated method stub

    }

 
}
