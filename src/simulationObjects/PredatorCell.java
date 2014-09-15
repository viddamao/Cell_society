package simulationObjects;

import java.util.ArrayList;

public class PredatorCell extends Cell {
    
    public enum State{
        EMPTY
    }

    public PredatorCell ()
    {

    }

    @Override
    public void update(ArrayList<Cell> neighbors){
        // TODO Auto-generated method stub

    }

    @Override
    public void prepareToUpdate () {
        // TODO Auto-generated method stub

    }

    @Override
    public int getState () {
        return myState;
    }

    @Override
    public void setState (int state) {
        myState = state;
    }
    

}
