package simulationObjects;

import java.util.List;

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
    // modified the order for processing 4 directions
    protected enum relativePosition {
	NORTH, SOUTH, EAST, WEST, SOUTHEAST, NORTHEAST, SOUTHWEST, NORTHWEST
    }

    protected enum State {
	EMPTY, GENERATING, OCCUPIED, EMPTYING
    }

    protected Cell myCell;
    protected int xCoord;
    protected int yCoord;
    protected Grid grid;
    protected List<Patch> myNeighbors;
    protected Image image;
    protected ImageView myView;
    protected State myState;

    protected PatchBody myBody;

    protected GridInfo infoSheet;

    private Class<?> myCellClass;

    private int myPreviousCellState;

    /**
     * Constructors
     */
    public Patch() {
	super();
	infoSheet = new GridInfo();
    }

    public Patch(int x, int y, Grid m) {
	super();
	initialize(x, y, m);
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
	myState = State.OCCUPIED;
	myCell = cell;
	updateCellConstraints(myCell);
	getChildren().add(myCell);
    }

    /**
     * creates a patch body for the patch
     *
     * @param bodyType
     *            what kind of shape it is
     */
    public void createBody(int bodyType) {
	if (myBody != null) {
	    getChildren().remove(myBody);
	}
	// TODO: use reflection
	switch (bodyType) {
	case 0:
	    myBody = new PatchBodyRectangle(xCoord, yCoord,
		    grid.getMinHeight(), grid.getMinWidth(),
		    grid.getGridHeight(), grid.getGridWidth());
	    break;
	case 1:
	    myBody = new PatchBodyTriangle(xCoord, yCoord, grid.getMinHeight(),
		    grid.getMinWidth(), grid.getGridHeight(),
		    grid.getGridWidth());
	    break;
	case 2:
	    myBody = new PatchBodyHexagon(xCoord, yCoord, grid.getMinHeight(),
		    grid.getMinWidth(), grid.getGridHeight(),
		    grid.getGridWidth());
	    break;
	}
	if (myCell != null) {
	    updateCellConstraints(myCell);
	}
	getChildren().add(myBody);
    }

    /**
     * create a new cell and assign it to this patch new cell comes from the
     * cell class stored in this patch
     */
    private void createNewCell() {
	try {
	    addCell((Cell) myCellClass.newInstance());
	    myCell.setState(1);
	    myCell.setX(xCoord);
	    myCell.setY(yCoord);
	} catch (InstantiationException | IllegalAccessException e) {
	    e.printStackTrace();
	}
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
     * Returns surrounding Patches
     */
    public void getNeighbors() {
	myNeighbors = grid.getNeighborsAround(xCoord, yCoord);
    }

    public int getPreviousState() {
	return myPreviousCellState;
    }

    // TODO for new simulations
    public int getState() {
	return 0;
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
	infoSheet = new GridInfo();
	myState = State.EMPTY;
    }

    public boolean isEmpty() {
	return myCell == null;
    }

    /**
     * Sets up the update
     */
    public void prepareToUpdate() {
	if (myCell != null) {
	    myCell.prepareToUpdate(this, myNeighbors);
	}
    }

    public Patch randomEmptyPatch() {
	// get an empty patch from grid manager
	return grid.findEmptyPatch();
    }

    public void removeCell() {
	if (myCell != null) {
	    getChildren().remove(myCell);
	    myCell = null;
	    myState = State.EMPTY;
	}
    }

    public void setCellClass(Class<?> c) {
	myCellClass = c;
    }

    public void setColorToBody(Color myColor) {
	myBody.setFill(myColor);
    }

    public void setPreviousState(int myState) {
	myPreviousCellState = myState;
    }

    public void toggleCellState() {
	if (myCell != null) {
	    int nextState = myCell.getNextState();
	    if (nextState == -1) {
		removeCell();
	    } else {
		myCell.setState(nextState);
	    }
	} else {
	    createNewCell();
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

    public void updateCellConstraints(Cell cell) {
	cell.setHeight(myBody.getPatchWidth() / 2);
	cell.setWidth(myBody.getPatchWidth() / 2);
	cell.setLayoutX(myBody.getCenter().x - myBody.getPatchWidth() / 4);
	cell.setLayoutY(myBody.getCenter().y - myBody.getPatchHeight() / 4);
	cell.setArcHeight(cell.getHeight());
	cell.setArcWidth(cell.getWidth());
    }

}
