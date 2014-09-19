package simulationObjects;

import controller.GridManager;
/**
 * Patch that Simulates GameOfLife
 * @author Will Chang
 *
 */
public class LifePatch extends Patch {

    public LifePatch()
    {
        super();
    }
    
    public LifePatch (int x, int y, GridManager m) {
        super(x, y, m);
        
    }
    @Override
    public void prepareToUpdate()
    {
        processNeighborsAndUpdateState();
    }           
    
    
    /**
     * Special rules for Game of Life
     */
    public void processNeighborsAndUpdateState()
    {
        int adjacentCells = 0;
        for(Patch p : myNeighbors)
        {
            if(!p.isEmpty())
            {
                adjacentCells++;
            }
        }
        if(adjacentCells == 3)
        {
            if(this.isEmpty())
            {
                myState = State.GENERATING;
            }
        }
        else if(adjacentCells>3||adjacentCells<2)
        {
            if(!this.isEmpty())
            {
                myState = State.EMPTYING;
            }
        }

    }

    @Override
    public void update() {
        // Update this
        switch(myState)
        {
            case GENERATING:
                Cell generated = new LifeCell(xCoord, yCoord);
                this.addCell(generated);
                myState = State.OCCUPIED;
                break;
            case EMPTYING:
                this.removeCell();                
                break;
            default: 
                break;
        }
    }
}
