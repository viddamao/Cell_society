package simulationObjects;

import controller.GridManager;


public class LifePatch extends Patch {


    public LifePatch (int x, int y, GridManager m) {
        super(x, y, m);
        // TODO Auto-generated constructor stub
    }
    @Override
    public void prepareToUpdate()
    {
        processNeighborsAndUpdateState();
    }

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
                myCell = new LifeCell();
                myState = State.OCCUPIED;
                break;
            case EMPTYING:
                this.removeCell();
                myState = State.EMPTY;
                break;
            default: 
                break;
        }
    }
}
