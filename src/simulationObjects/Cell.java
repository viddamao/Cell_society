package simulationObjects;

import java.util.ArrayList;

public abstract class Cell {
    protected int myX;
    protected int myY;

    public Cell ()
    {
        
    }
    
    protected int myState;  
    public abstract int getState();
    public abstract void setState(int state);

    public abstract void update(ArrayList<Cell> neighbors);

    public abstract void prepareToUpdate ();
    
    public int getX()
    {
        return myX;
    }
    
    public int getY()
    {
        return myY;
    }
    
    public void setX(int x)
    {
        myX = x;
    }
    
    public void setY(int y)
    {
        myY = y;
    }
    
}
