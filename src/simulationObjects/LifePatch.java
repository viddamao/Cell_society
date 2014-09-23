package simulationObjects;

import controller.GridManager;
/**
 * Patch that Simulates GameOfLife
 * @author Will Chang
 *
 */
public class LifePatch extends Patch {

    //TODO Comment everything
    //Need to load this through the XML...
    //Edit from Constructor
    private int comfortableAmount = 3;
    private int baselineAmount = 2;
    
    public LifePatch()
    {
        super();
    }
    
    public LifePatch (int x, int y, GridManager m) {
        super(x, y, m);
        
    }
    @Override
    public void prepareToUpdate(){
        processNeighborsAndUpdateState();
    }           
    
    /**
     * Special rules for Game of Life
     */
    public void processNeighborsAndUpdateState(){
        int occupiedNeighbors = this.countNeighbors();
        
        if(occupiedNeighbors == comfortableAmount && this.isEmpty()){
            myState = State.GENERATING;
        }
        else if((occupiedNeighbors>comfortableAmount
                ||occupiedNeighbors<baselineAmount)
                && !this.isEmpty()){
                myState = State.EMPTYING;
        }
    }
    
    public int countNeighbors(){
        int occupiedNeighbors = 0;
        for(Patch p : myNeighbors){
            if(!p.isEmpty()){
                occupiedNeighbors++;
            }
        }
        return occupiedNeighbors;
    }

    // TODO Update this
    //talk to Kevin about this switch 
    //some duplicated code.
    @Override
    public void update() {
        switch(myState){
            case GENERATING:
                this.addCell(new LifeCell(xCoord, yCoord));
                break;
            case EMPTYING:
                this.removeCell();   
                break;
            default: 
                break;
        }
    }
}
