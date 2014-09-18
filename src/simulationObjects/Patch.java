package simulationObjects;

import java.util.ArrayList;
import javafx.scene.Group;
import controller.GridManager;

public class Patch extends Group {
    protected Cell myCell;
    protected int xCoord;
    protected int yCoord;
    private GridManager manager;
    private ArrayList<Patch> myNeighbors;

    // protected relativePosition myNorth, mySouth, myWest, myEast,
    // myNorthWest, myNorthEast, mySouthWest, mySouthEast;

    public Patch(int x, int y, GridManager m) {
	super();
	xCoord = x;
	yCoord = y;
	manager = m;
    }

    public ArrayList<Patch> getNeighbors() {
        return myNeighbors;
    }
        
    public void loadNeighbors() {
	myNeighbors = manager.getNeighborsAround(xCoord, yCoord);
    }

    public Cell getCell() {
	return myCell;
    }

    public int getGridX() {
	return xCoord;
    }

    public int getGridY() {
	return yCoord;
    }

    public void update() {
        if (myCell != null){
            // Update this
            Patch patchToMoveTo = myCell.update(this, myNeighbors);
            if (patchToMoveTo == null) {
                this.removeCell();
            }
            else if(patchToMoveTo != this){
                patchToMoveTo.addCell(myCell);
                removeCell();
            }
        }
    }

    // private int[] xDelta = { -1, -1, -1, 0, 0, 1, 1, 1 };
    // private int[] yDelta = { -1, 0, 1, -1, 1, -1, 0, 1 };

    // Maybe make this an interface??
    protected enum relativePosition {
	NORTHWEST, WEST, SOUTHWEST, NORTH, SOUTH, NORTHEAST, EAST, SOUTHEAST
    }

    public boolean isEmpty() {
	return myCell == null;
    }

    public void addCell(Cell cell) {
        if (myCell != null){
            removeCell();
        }
        cell.setLayoutX(this.getLayoutX());
        cell.setLayoutY(this.getLayoutY());
        cell.setHeight(500/manager.getGridWidth());
        cell.setWidth(500/manager.getGridWidth());
        cell.setArcHeight(cell.getHeight());
        cell.setArcWidth(cell.getWidth());
        getChildren().add(cell);
	myCell = cell;
    }

    public void removeCell() {
        if (myCell != null){
            getChildren().remove(myCell);
            myCell = null;
        }
    }
    
    public Patch randomEmptyPatch(){
        //get an empty patch from grid manager
        return manager.findEmptyPatch();
    }

}
