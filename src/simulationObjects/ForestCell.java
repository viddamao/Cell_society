package simulationObjects;

public class ForestCell extends Cell {
    
    public enum State{
        EMPTY,TREE,ONFIRE
    }

    public ForestCell ()
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
