package simulationObjects;

import java.util.ArrayList;
import javax.media.j3d.View;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import controller.GridManager;
/**
 * 
 * @author Will Chang
 *
 */
public class Patch extends Group {
    protected Cell myCell;
    protected int xCoord;
    protected int yCoord;
    protected GridManager manager;
    protected ArrayList<Patch> myNeighbors;
    protected Image image;
    protected ImageView myView;
    protected State myState;

    private int myPreviousCellState;

    // protected relativePosition myNorth, mySouth, myWest, myEast,
    // myNorthWest, myNorthEast, mySouthWest, mySouthEast;

    public Patch() 
    {
        super();
    }
    
    public Patch(int x, int y, GridManager m) {
        super();
        xCoord = x;
        yCoord = y;
        manager = m;
        myState = State.EMPTY;

    }

    
    protected enum State {
        EMPTY, GENERATING, OCCUPIED, EMPTYING
    }
    /**
     * For Special patch simulations.
     * @param x
     * @param y
     * @param m
     */
    public void initialize(int x, int y, GridManager m)
    {
        xCoord = x;
        yCoord = y;
        manager = m;
        myState = State.EMPTY;
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
        if (myCell != null){
            myCell.prepareToUpdate(this, myNeighbors);
        }
    }

    public void update() {
        // Update this
        if (myCell != null) {
            Patch status = myCell.update(this, myNeighbors);
            if (status == null) {
                this.removeCell();
            }
        }

    }

    // modified the order for processing 4 directions
    protected enum relativePosition {
        NORTH, SOUTH, EAST, WEST, SOUTHEAST, NORTHEAST, SOUTHWEST, NORTHWEST
    }

    public boolean isEmpty() {
        return myCell == null;
    }

    public void addCell(Cell cell) {
        if (myCell != null){
            removeCell();
        }
        cell.setHeight(manager.getRowConstraints().get(0).getPrefHeight());
        cell.setWidth(manager.getColumnConstraints().get(0).getPrefWidth());
        cell.setArcHeight(cell.getHeight());
        cell.setArcWidth(cell.getWidth());
        getChildren().add(cell);
        myState = State.OCCUPIED;
        myCell = cell;
    }

    public void removeCell() {
        if (myCell != null) {
            getChildren().remove(myCell);
            myCell = null;
            myState = State.EMPTY;
        }
    }

    public Patch randomEmptyPatch() {
        // get an empty patch from grid manager
        return manager.findEmptyPatch();
    }

    public int getPreviousState() {
        return myPreviousCellState;
    }

    public void setPreviousState(int myState) {
        myPreviousCellState = myState;
    }

}
