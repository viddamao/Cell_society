package simulationObjects;

public class PredatorCell extends Cell {
    
    private enum State{
        EMPTY
    }

    public PredatorCell ()
    {

    }

    @Override
    public void update () {
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
