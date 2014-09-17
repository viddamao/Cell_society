package simulationObjects;

import java.util.ArrayList;

public abstract class PredatorCell extends Cell {
    // This is it's pregnancylevel

    protected int vitality = 5;
    protected int timeToBreed = 5;
    protected final int maxVitality = 5;
    protected int gestationPeriod;

    // Put in constructor?
    protected ArrayList<Patch> myNeighbors = new ArrayList<>();
    // Birthmark
    private int maxState = 9;

    public enum State {
	EMPTY
    }

    public PredatorCell() {

    }

    /**
     * Updates the Predator Cell
     * 
     * returns the current location if the Predator Cell is still alive returns
     * null if the shark cell's vitality = 0
     */
    @Override
    public Patch update(Patch current, ArrayList<Patch> neighbors) {
	if (vitality > 0) {
	    Patch destination = chooseMove(neighbors);
	    this.updateStatesandMakeMoves(current, destination);
	    return current;
	}
	return null;
    }

    public void makeMove(Patch current, Patch destination) {
	destination.addCell(this);
	current.removeCell();
    }

    public void leaveEgg(Patch current) {
	current.addCell(new SharkCell());
    }

    /**
     * Finds possible patches to move and returns a destination. If can't move
     * returns null
     * 
     * @param neighbors
     * @return Patch to move to (contains fish or empty patch) Null if nowhere
     *         to move.
     */
    public Patch chooseMove(ArrayList<Patch> neighbors) {
	ArrayList<Patch> destinations = this
		.processPossibleDestinations(neighbors);
	int range = destinations.size();
	if (range > 0)
	    return destinations.get((int) (Math.random() * range));
	else
	    return null;
    }

    // Some Duplicated code in subclasses...
    public abstract ArrayList<Patch> processPossibleDestinations(
	    ArrayList<Patch> allNeighbors);

    // For implementation of processPossibleDestinations... only called by
    // subclasses...
    // 1,3,4,6 Cardinal directions
    // Repeated code, need to make more efficient/remove dup code... enums?
    // Maps?
    public void siftNeighbors(ArrayList<Patch> allNeighbors) {
	myNeighbors.add(allNeighbors.get(1));
	myNeighbors.add(allNeighbors.get(3));
	myNeighbors.add(allNeighbors.get(4));
	myNeighbors.add(allNeighbors.get(6));
    }

    // slightly duplicated method
    public abstract void updateStatesandMakeMoves(Patch current,
	    Patch destination);

    @Override
    public int getState() {
	return myState;
    }

    @Override
    public void setState(int state) {
	myState = state;
    }

}
