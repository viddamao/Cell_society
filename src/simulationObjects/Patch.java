package simulationObjects;

import java.util.ArrayList;
import com.sun.javafx.geom.Rectangle;
import controller.GridManager;

public class Patch extends Rectangle {
    protected Cell myCell;
    protected int xCoord;
    protected int yCoord;
    private GridManager manager;
    private ArrayList<Patch> myNeighbors;
    
    public Patch(int x, int y, Cell cell, GridManager m)
    {
        xCoord  = x;
        yCoord  = y;
        myCell  = cell;
        manager = m;
    }
    public void getNeighbors()
    {
        myNeighbors = manager.getNeighborsAround(xCoord, yCoord);
    }
    
    public void update()
    {
       Patch receiver = myCell.update(myNeighbors);
    }
    
    public void addCell(Cell cell)
    {
        myCell = cell;
    }
    
    public void removeCell()
    {
        myCell = null;
    }
    
    

    
}
