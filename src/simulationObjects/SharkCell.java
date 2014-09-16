package simulationObjects;

import java.util.ArrayList;

public class SharkCell extends PredatorCell {

    private int vitality = 5;
    
    
    public SharkCell()
    {
        
    }
    
    @Override
    public Patch update(Patch currentPatch, ArrayList<Patch> neighbors) {
	// TODO Auto-generated method stub
        vitality--;
        if(vitality==0)
            return null;
        return null;
    }

    public Patch findPreyOrMove(ArrayList<Patch> neighbors)
    {
        ArrayList<Patch> destinations = this.processPossibleDestinations(neighbors);
        int range = destinations.size();
        if(range > 0)
            return destinations.get((int)(Math.random()*range));
        else
            return null;
    }
    
    
    public ArrayList<Patch> processPossibleDestinations(ArrayList<Patch> neighbors)
    {
        ArrayList<Patch> buffer = new ArrayList<>();
        for(Patch loc : neighbors)
        {
            Cell occupant = loc.getCell();
            if(occupant==null || occupant!=this)
            {
                buffer.add(loc);
            }  
        }
        return buffer;
    }
    @Override
    public void prepareToUpdate(ArrayList<Cell> neighbors) {

    }

}
