package simulationObjects;

import java.util.ArrayList;

public class SharkCell extends PredatorCell {

    @Override
    public Patch update(ArrayList<Patch> neighbors) {
	// TODO Auto-generated method stub
        return null;
    }

    public Patch findPreyOrMove(ArrayList<Patch> neighbors)
    {
        for(Patch loc : neighbors)
        {
            Cell prey = loc.getCell();
            if(prey==null || prey!=this)
            {
                return loc;
            }
                
        }
        return null;
      
    }
    
    public ArrayList<Patch> processPossibleDestinations(ArrayList<Patch> neighbors)
    {
        //ArrayList<Patch> buffer = 
        for(Patch loc : neighbors)
        {
            Cell prey = loc.getCell();
            if(prey==null || prey!=this)
            {
                
            }
                
        }
        return null;
    }
    @Override
    public void prepareToUpdate(ArrayList<Cell> neighbors) {

    }

}
