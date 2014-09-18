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

    // protected relativePosition myNorth, mySouth, myWest, myEast,
    // myNorthWest, myNorthEast, mySouthWest, mySouthEast;

    public Patch(int x, int y, GridManager m) {
	super();
	xCoord = x;
	yCoord = y;
	manager = m;
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

    public void update() {
	// Update this
	Patch state = myCell.update(this, myNeighbors);
	if (state == null) {
	    this.removeCell();
	}
	/**
	 * if(destination!=null) { destination.addCell(myCell); } else {
	 * this.removeCell(); }
	 */
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
        getChildren().remove(myCell);
	myCell = null;
    }

}
