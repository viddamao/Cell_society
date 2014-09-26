package simulationPatches;

import java.util.ArrayList;
import simulationCells.Cell;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import controller.Grid;
import controller.GridInfo;

/**
 *
 * @author Will Chang
 *
 */
public class Patch extends Group {
    protected Cell myCell;
    protected int xCoord;
    protected int yCoord;
    protected Grid grid;
    protected ArrayList<Patch> myNeighbors;
    protected Image image;
    protected ImageView myView;
    protected State myState;
    protected PatchBody myBody;
    protected GridInfo infoSheet;
    
    private Class<?> myCellClass;

    private int myPreviousCellState;

    private int REMOVE_ME = 0;

    /**
     * Constructors
     */
    public Patch() {
	super();
    }

    public Patch(int x, int y, Grid m) {
	super();
	xCoord = x;
	yCoord = y;
	grid = m;
	myState = State.EMPTY;
	infoSheet = new GridInfo();

    }
    
    public void createBody(){
        myBody = new PatchBodyRectangle(xCoord,yCoord,grid.getMinHeight(),grid.getMinWidth(),grid.getGridHeight(),grid.getGridWidth());
        getChildren().add(myBody);
    }

    protected enum State {
	EMPTY, GENERATING, OCCUPIED, EMPTYING
    }

    /**
     * For Special patch simulations.
     *
     * @param x
     *            loc
     * @param y
     *            loc
     * @param m
     *            manager
     */
    public void initialize(int x, int y, Grid m) {
	xCoord = x;
	yCoord = y;
	grid = m;
	myState = State.EMPTY;
    }

    /**
     * Returns surrounding Patches
     */
    public void getNeighbors() {
	myNeighbors = grid.getNeighborsAround(xCoord, yCoord);
    }

    /**
     * Gets the cell
     *
     * @return myCell
     */
    public Cell getCell() {
	return myCell;
    }

    public int getGridX() {
	return xCoord;
    }

    public int getGridY() {
	return yCoord;
    }

    /**
     * Sets up the update
     */
    public void prepareToUpdate() {
	if (myCell != null) {
	    myCell.prepareToUpdate(this, myNeighbors);
	}
    }

    /**
     * Updates patch and the cell
     */
    public void update() {
	// Update this
	if (myCell != null) {
	    myCell.update(this, myNeighbors);
	    // TODO put 0 into the GridInfo/GameInfo
	    if (myCell != null && myCell.getState() == 0) {
		removeCell();
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

    /**
     * Adds cell along with GUI info.
     *
     * @param cell
     *            to occupy patch
     */
    public void addCell(Cell cell) {
	if (myCell != null) {
	    removeCell();
	}
	cell.setHeight(myBody.getPatchWidth()/2);
	cell.setWidth(myBody.getPatchWidth()/2);
	cell.setLayoutX(myBody.getCenter().x-myBody.getPatchWidth()/4);
	cell.setLayoutY(myBody.getCenter().y-myBody.getPatchHeight()/4);
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
	return grid.findEmptyPatch();
    }

    public int getPreviousState() {
	return myPreviousCellState;
    }

    public void setPreviousState(int myState) {
	myPreviousCellState = myState;
    }
    
    //TODO for new simulations
    public int getState()
    {
        return 0;
    }
    public void setCellClass(Class<?> c){
        myCellClass = c;
    }

    public void toggleCellState () {
        if (myCell != null){
            int nextState = myCell.getNextState();
            if (nextState == -1){
                removeCell();
            }
            else{
                myCell.setState(nextState);
            }
        }
        else{
            try {
                addCell((Cell) myCellClass.newInstance());
                myCell.setState(1);
            }
            catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public void setColorToBody(Color myColor) {
	myBody.setFill(myColor);
    }

}