package simulationObjects;

import com.sun.javafx.geom.Rectangle;
import controller.GridManager;

public class Patch extends Rectangle {
    protected Cell myCell;
    protected int xCoord;
    protected int yCoord;
    private GridManager manager;
    
    public Patch(int x, int y, Cell cell, GridManager m)
    {
        xCoord  = x;
        yCoord  = y;
        myCell  = cell;
        manager = m;
    }
    public void update()
    {
        
    }

    
}
