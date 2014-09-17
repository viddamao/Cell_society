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

    public Patch(int x, int y, GridManager m)
    {
        xCoord  = x;
        yCoord  = y;
        manager = m;
    }

    public void getNeighbors()
    {
        myNeighbors = manager.getNeighborsAround(xCoord, yCoord);
    }

    public Cell getCell()
    {
        return myCell;
    }

    public int getX()
    {
        return xCoord;
    }

    public int getY()
    {
        return yCoord;
    }

    public void update()
    {

        Patch destination = myCell.update(this, myNeighbors);
        if(destination!=null)
        {
            destination.addCell(myCell);
        }  
        else
        {
            this.removeCell();
        }
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
