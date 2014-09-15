package simulationObjects;

public class SegCell extends Cell {
    
    private enum State{
        EMPTY,X,O
    }

    public SegCell ()
    {

    }

    @Override
    public void update () {
        // TODO Auto-generated method stub

    }

    @Override
    public void prepareToUpdate () {

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
