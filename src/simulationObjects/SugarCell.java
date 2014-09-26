package simulationObjects;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;

public class SugarCell extends Cell {

    protected int currentSugar = 25;
    protected int sugarMetabolism = 4;
    protected List<Patch> myNeighbors;
    private final int OCCUPIED = 1;
    private final int DYING = 0;

    private Phase myPhase;

    private enum Phase {
	UPDATING, STASIS
    }

    public SugarCell() {
	super();
	myNeighbors = new ArrayList<>();
    }

    public SugarCell(int x, int y, int state) {
	super();
	myX = x;
	myY = y;
	myState = state;

	if (state == OCCUPIED) {
	    setFill(infoSheet.getColor("AGENT"));
	}
    }
    
    @Override
    public void initialize(int x, int y, int state) {
	super.initialize(x, y, state);
	myPhase = Phase.STASIS;
    }

    @Override
    public int getState() {
	return myState;
    }

    @Override
    public void setState(int state) {
	if (state == OCCUPIED) {
	    setFill(infoSheet.getColor("AGENT"));
	}
	myState = state;
    }

    @Override
    public void update(Patch current, List<Patch> neighbors) {
	this.setState(myState);
	if (myPhase == Phase.UPDATING) {
	    if (currentSugar > 0) {
		Patch destination = chooseMove(neighbors);
		updateStatesandMakeMoves(current, destination);
		myPhase = Phase.STASIS;
	    } else {
		setState(DYING);
	    }
	    
	}
    }

    private void updateStatesandMakeMoves(Patch current, Patch destination) {
	currentSugar-=sugarMetabolism;
	if (destination != null) {
	    if (myState == OCCUPIED && !destination.isEmpty()) {
		currentSugar += ((SugarPatch) destination).getCurrentSugar();
		((SugarPatch)destination).removeSugar();

		System.out.print("REMOVE");
	    }
	    makeMove(current, destination);
	}
    }

    private Patch chooseMove(List<Patch> neighbors) {
	List<Patch> destinations = processPossibleDestinations(neighbors);
	int range = destinations.size();
	if (range > 0)
	    return pickClosestMove(destinations);

	else
	    return null;
    }

    private Patch pickClosestMove(List<Patch> destinations) {
	Patch cloestDestination = null;
	int currentMinDist = 9999;
	for (Patch currentDestination : destinations) {
	    int currentDist = 0;
	    currentDist = Math.abs(this.myX - currentDestination.xCoord)
		    + Math.abs(this.myY - currentDestination.yCoord);
	    if (currentDist <= currentMinDist) {
		currentMinDist = currentDist;
		cloestDestination = currentDestination;
	    }
	}

	return cloestDestination;
    }

    private List<Patch> processPossibleDestinations(List<Patch> neighbors) {
	myNeighbors = neighbors;
	int maximumSugarAmount = -1;

	List<Patch> possibleMoves = new ArrayList<>();
	for (Patch nextNeighbor : myNeighbors) {
	    Cell occupant = nextNeighbor.getCell();
	    int currentSugarAmount = ((SugarPatch) nextNeighbor)
		    .getCurrentSugar();
	    if ((occupant == null)
		    && (currentSugarAmount >= maximumSugarAmount)) {
		if (currentSugarAmount > maximumSugarAmount)
		    possibleMoves.clear();
		possibleMoves.add(nextNeighbor);
		maximumSugarAmount = currentSugarAmount;
	    }
	}

	return possibleMoves;
    }

    private void makeMove(Patch currentPatch, Patch destination) {
	destination.addCell(this);
	currentPatch.removeCell();
    }

    @Override
    public void prepareToUpdate(Patch currentPatch, List<Patch> neighbors) {
	myPatch = currentPatch;
	myPhase = Phase.UPDATING;
    }

    @Override
    public int getNextState() {
	return myState == OCCUPIED ? -1 : OCCUPIED;
    }

    @Override
    public List<String> getStateTypes() {
	ArrayList<String> myStateType = new ArrayList<String>();
	myStateType.add("CONCENTRATION_0");
	myStateType.add("CONCENTRATION_1");
	myStateType.add("CONCENTRATION_2");
	myStateType.add("CONCENTRATION_3");
	myStateType.add("CONCENTRATION_4");
	myStateType.add("AGENT");
	return myStateType;
    }

    @Override
    public List<Color> getInitialColors() {
	ArrayList<Color> myStateColors = new ArrayList<Color>();
	myStateColors.add(Color.WHITE);
	myStateColors.add(Color.MOCCASIN);
	myStateColors.add(Color.LIGHTSALMON);
	myStateColors.add(Color.SANDYBROWN);
	myStateColors.add(Color.ORANGE);
	myStateColors.add(Color.BROWN);
	return myStateColors;
    }

}
