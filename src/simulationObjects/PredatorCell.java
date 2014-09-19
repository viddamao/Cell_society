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
    protected int timeToBreed = 20;
    protected final int maxVitality = 5;
    protected int gestationPeriod = 20;
    private final int FISH = 1;
    private final int SHARK = 2;
    
    protected ArrayList<Patch> myNeighbors = new ArrayList<>();
    

    public PredatorCell() {
        super();
    }


    public PredatorCell(int x, int y, int state)
    {
        super();      
        myX = x;
        myY = y;
        myState = state;

        if(state == SHARK)
            setFill(Color.YELLOW);
        else if(state == FISH)
            setFill(Color.GREEN);

    }
    public void feed(Patch destination) {
        destination.removeCell();
        this.vitality += 3;
    }

    public void prepareToUpdate(Patch currentPatch,
                                ArrayList<Patch> neighbors)
    {

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
        if(myState == SHARK)
        {
            current.addCell(new PredatorCell(current.getGridX(),current.getGridY(),SHARK));
        }
        else
        {
            current.addCell(new PredatorCell(current.getGridX(),current.getGridY(),FISH));
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

    // Some Duplicated code in subclasses...
    public  ArrayList<Patch> processPossibleDestinations(ArrayList<Patch> allNeighbors)
    {
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
        if(myState == SHARK)
        {
            if (fishBuffer.size() > 0) {
                return fishBuffer;
            }
        }
        return emptyBuffer;
    }



    // slightly duplicated method
    public void updateStatesandMakeMoves(Patch current, Patch destination) {
        vitality--;
        if (timeToBreed > 0) {
            timeToBreed--;
        }
        if (destination != null) {
            if(myState==SHARK && !destination.isEmpty()) {
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
        if(state == SHARK)
        {
            setFill(Color.YELLOW);
        }
        else
        {
            setFill(Color.GREEN);
        }
        myState = state;
    }

}
