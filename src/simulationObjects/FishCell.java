package simulationObjects;

import java.util.ArrayList;

public class FishCell extends PredatorCell {

    public FishCell() {

    }

    public ArrayList<Patch> processPossibleDestinations(
	    ArrayList<Patch> allNeighbors) {
	this.siftNeighbors(allNeighbors);
	ArrayList<Patch> emptyBuffer = new ArrayList<>();
	for (Patch loc : myNeighbors) {
	    if (loc.isEmpty()) {
		emptyBuffer.add(loc);
	    }
	}
	return emptyBuffer;
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
	    this.makeMove(current, destination);

	    if (timeToBreed == 0) {
		this.leaveEgg(current);
		timeToBreed = gestationPeriod;
	    }
	}
    }

    @Override
    public void prepareToUpdate (Patch currentPatch, ArrayList<Patch> neighbors) {
        // TODO Auto-generated method stub
        
    }





}
