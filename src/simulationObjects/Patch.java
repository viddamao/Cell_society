package simulationObjects;

import java.util.ArrayList;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import controller.GridManager;

public class Patch extends Group {
    protected Cell myCell;
    protected int xCoord;
    protected int yCoord;
    private GridManager manager;
    private ArrayList<Patch> myNeighbors;

    private State myState;

    private int myPreviousCellState;


    // protected relativePosition myNorth, mySouth, myWest, myEast,
    // myNorthWest, myNorthEast, mySouthWest, mySouthEast;

    public Patch(int x, int y, GridManager m) {
        super();
        xCoord = x;
        yCoord = y;
        manager = m;
        myState = State.EMPTY;
    }

    private enum State{
        EMPTY, GENERATING, OCCUPIED
    }
    
    public void getNeighbors() {
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

    public void prepareToUpdate()
    {
        if(this.myState = State.GENERATING)
        {
            
        }
    }

    public void update() {
        // Update this
        if(myCell!=null)
        {
            Patch state = myCell.update(this, myNeighbors);
            if (state == null) {
                this.removeCell();
            }
        }

    }

    // modified the order for processing 4 directions
    protected enum relativePosition {
<<<<<<< HEAD
        NORTHWEST, WEST, SOUTHWEST, NORTH, SOUTH, NORTHEAST, EAST, SOUTHEAST
=======
	NORTH, SOUTH, EAST, WEST, SOUTHEAST, NORTHEAST, SOUTHWEST, NORTHWEST
>>>>>>> master
    }

    public boolean isEmpty() {
        return myCell == null;
    }

    public void addCell(Cell cell) {
<<<<<<< HEAD
        myCell = cell;
    }

    public void removeCell() {
        myCell = null;
=======
	cell.setLayoutX(this.getLayoutX());
	cell.setLayoutY(this.getLayoutY());
	cell.setHeight(this.getMaxHeight());
	cell.setWidth(this.getMaxWidth());
	cell.setArcHeight(cell.getHeight());
	cell.setArcWidth(cell.getWidth());
	getChildren().add(cell);

	myCell = cell;
    }

    public void removeCell() {
	getChildren().remove(myCell);
	myCell = null;
>>>>>>> master
    }

    public int getPreviousState() {
	return myPreviousCellState;
    }

    public void setPreviousState(int myState) {
	myPreviousCellState = myState;
    }

}
