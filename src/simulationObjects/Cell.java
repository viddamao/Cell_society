package simulationObjects;

public abstract class Cell {

    public Cell ()
    {

    }
    
    protected int myState;  
    public abstract int getState();
    public abstract void setState(int state);

    public abstract void update ();

    public abstract void prepareToUpdate ();

}
