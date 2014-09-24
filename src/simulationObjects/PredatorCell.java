package simulationObjects;

import java.util.ArrayList;

import javafx.scene.paint.Color;

/**
 * 
 * @author Will Chang
 *
 */
public class PredatorCell extends Cell {
    // This is it's pregnancylevel

    protected int vitality = 5;
    protected int timeToBreed = 4;
    protected final int sharkVitality = 3;
    protected final int fishVitality = 8;
    protected int gestationPeriod = 4;
    private final int FISH = 1;
    private final int SHARK = 2;
    
    private final int DYING = 0;
    
    private Phase myPhase;
    
    protected ArrayList<Patch> myNeighbors = new ArrayList<>();
    
    private enum Phase{
        UPDATING, STASIS
    }

    public PredatorCell() {
	super();
    }
    /**
     * Constructor
     * 
     * @param x
     * @param y
     * @param state
     */
    public PredatorCell(int x, int y, int state) {
	super();
	myX = x;
	myY = y;
	myState = state;

	if (state == SHARK) {
	    setFill(infoSheet.getColor("SHARK"));
	    vitality = sharkVitality;
	} else if (state == FISH) {
	    setFill(infoSheet.getColor("FISH"));
	    vitality = fishVitality;
	}
    }

    /**
     * Special constructor for XML
     */
    @Override
    public void initialize(int x, int y, int state) {
	super.initialize(x, y, state);
	myPhase = Phase.STASIS;
    }

    /**
     * Eats another fish
     * 
     * @param destination
     */
    public void feed(Patch destination) {
	destination.removeCell();
	this.vitality += 3;
    }

    /**
     * Sets state for updating
     */
    public void prepareToUpdate(Patch currentPatch, ArrayList<Patch> neighbors) {
	myPhase = Phase.UPDATING;
    }

    /**
     * Updates the Predator Cell
     * 
     * returns the current location if the Predator Cell is still alive returns
     * null if the shark cell's vitality = 0
     */
    @Override
    public void update(Patch current, ArrayList<Patch> neighbors) {
        if(myPhase == Phase.UPDATING){
            if (vitality > 0) {
                Patch destination = chooseMove(neighbors);
                this.updateStatesandMakeMoves(current, destination);
                myPhase = Phase.STASIS;
            }
            else
            {
                this.setState(DYING);
            }
        }
    }

    /**
     * Makes the move
     * 
     * @param current
     *            patch
     * @param destination
     *            patch
     */
    public void makeMove(Patch current, Patch destination) {
	destination.addCell(this);
	current.removeCell();
    }

    /**
     * Breeds
     * 
     * @param current
     *            leaves new PredatorCell on old location
     */
    public void leaveEgg(Patch current) {
	if (myState == SHARK) {
	    current.addCell(new PredatorCell(current.getGridX(), current
		    .getGridY(), SHARK));
	} else {
	    current.addCell(new PredatorCell(current.getGridX(), current
		    .getGridY(), FISH));
	}

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

    /**
     * Processes locations to move to
     * 
     * @param allNeighbors
     *            it can move to
     * @return list of Patches to move to
     */
    public ArrayList<Patch> processPossibleDestinations(
	    ArrayList<Patch> allNeighbors) {
	myNeighbors = allNeighbors;

	ArrayList<Patch> emptyBuffer = new ArrayList<>();
	ArrayList<Patch> fishBuffer = new ArrayList<>();
	for (Patch loc : myNeighbors) {
	    Cell occupant = loc.getCell();
	    if (occupant == null) {
		emptyBuffer.add(loc);
	    } else if (occupant.getState() == FISH) {
		fishBuffer.add(loc);
	    }
	}
	if (myState == SHARK) {
	    if (fishBuffer.size() > 0) {
		return fishBuffer;
	    }
	}
	return emptyBuffer;
    }

    /**
     * Updates the state and moves based on Shark vs Fish
     * 
     * @param current
     *            patch
     * @param destination
     *            patch
     */
    public void updateStatesandMakeMoves(Patch current, Patch destination) {
	vitality--;
	if (timeToBreed > 0) {
	    timeToBreed--;
	}
	if (destination != null) {
	    if (myState == SHARK && !destination.isEmpty()) {
		this.feed(destination);
	    }
	    this.makeMove(current, destination);

	    if (timeToBreed == 0) {
		this.leaveEgg(current);
		timeToBreed = gestationPeriod;
	    }
	}
    }

    @Override
    public int getState() {
	return myState;
    }

    @Override
    public void setState(int state) {
	if (state == SHARK) {
	    setFill(infoSheet.getColor("SHARK"));
	} else {
	    setFill(infoSheet.getColor("FISH"));
	}
	myState = state;
    }

    @Override
    public ArrayList<String> getStateTypes() {
	ArrayList<String> myStateType = new ArrayList<String>();
	myStateType.add("BACKGROUND");
	myStateType.add("SHARK");
	myStateType.add("FISH");
	return myStateType;
    }

    @Override
    public ArrayList<Color> getInitialColors() {
	ArrayList<Color> myStateColors = new ArrayList<Color>();
	myStateColors.add(Color.AQUA);
	myStateColors.add(Color.YELLOW);
	myStateColors.add(Color.GREEN);
	return myStateColors;
    }
    @Override
    public void toggleState () {
        // TODO toggle to the next cell state
    }

}
