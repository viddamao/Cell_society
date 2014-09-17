package simulationObjects;

import java.util.ArrayList;
import java.util.HashMap;

public class SharkCell extends PredatorCell {

    //Temp variable assignments
    private int vitality = 5;
    private int timeToBreed = 5;
    private final int maxVitality = 5;
    private int gestationPeriod;

    public SharkCell()
    {

    }



    @Override
    public Patch update(Patch current, ArrayList<Patch> neighbors) {
        // TODO Auto-generated method stub
        if(vitality>0)
        {         
            Patch destination = chooseMove(neighbors);
            this.updateStatesandMakeMoves(current, destination);    
            return current;
        }
        return null;
    }
    
    
    public void updateStatesandMakeMoves(Patch current, Patch destination)
    {
        vitality--;
        if(timeToBreed>0)
        {
            timeToBreed--;
        }
        if(destination!=null)
        {
            if(!destination.isEmpty())
            {
                this.feed(destination);
            }
            this.makeMove(current, destination);
            
            if(timeToBreed==0)
            {
                this.leaveEgg(current);
                timeToBreed = gestationPeriod;
            }
            
            
        }
    }
    
    public void leaveEgg(Patch current)
    {
        current.addCell(new SharkCell());
    }
    
    public void makeMove(Patch current, Patch destination)
    {
        destination.addCell(this);
        current.removeCell();
    }
    
    public void feed(Patch destination)
    {
        destination.removeCell();
    }


    /**
     * Finds possible patches to move and returns a destination. 
     * If can't move returns null
     * @param neighbors
     * @return Patch to move to (contains fish or empty patch)
     *         Null if nowhere to move.
     */
    public Patch chooseMove(ArrayList<Patch> neighbors)
    {
        ArrayList<Patch> destinations = this.processPossibleDestinations(neighbors);
        int range = destinations.size();
        if(range > 0)
            return destinations.get((int)(Math.random()*range));
        else
            return null;
    }


    public ArrayList<Patch> processPossibleDestinations(ArrayList<Patch> allNeighbors)
    {
        //1,3,4,6
        //Repeated code, need to make more efficient/remove dup code... enums? Maps? 
        ArrayList<Patch> myNeighbors = new ArrayList<>();
        myNeighbors.add(allNeighbors.get(1));
        myNeighbors.add(allNeighbors.get(3));
        myNeighbors.add(allNeighbors.get(4));
        myNeighbors.add(allNeighbors.get(6));

        ArrayList<Patch> emptyBuffer = new ArrayList<>();
        ArrayList<Patch> fishBuffer = new ArrayList<>();
        for(Patch loc : myNeighbors)
        {
            Cell occupant = loc.getCell();
            if(occupant==null)
            {
                emptyBuffer.add(loc); 
            }
            else if(occupant != this)
            {
                fishBuffer.add(loc);
            }
        }
        if(fishBuffer.size()>0)
        {
            return fishBuffer;
        }
        return emptyBuffer;
    }
   
}
