package simulationCells;

import java.util.ArrayList;
import simulationPatches.Patch;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import controller.GridInfo;

/**
 * Parent Class of Cell Hierarchy
 * TODO Change extends to Polygon?
 * @author Everyone
 *
 */
public abstract class Cell extends Rectangle {

    protected GridInfo infoSheet = new GridInfo();

    protected int myX;
    protected int myY;
    protected int myState;
    protected Patch myPatch;

    public Cell() {

    }

    /**
     * Special constructor for XML reads
     *
     * @param x
     * @param y
     * @param state
     */
    public void initialize(int x, int y, int state) {
	this.setX(x);
	this.setY(y);
	setState(state);
    }

    // modified the order for processing 4 directions
    public enum relativePosition {
	NORTH, SOUTH, EAST, WEST, SOUTHEAST, NORTHEAST, SOUTHWEST, NORTHWEST
    }

    // REFACTOR SOME OF THESE METHODS...

    public abstract int getState();

    public abstract void setState(int state);

    public abstract void update(Patch currentPatch, ArrayList<Patch> neighbors);

    public abstract void prepareToUpdate(Patch currentPatch,
	    ArrayList<Patch> neighbors);
    
    public abstract int getNextState();

    public int getGridX() {
	return myX;
    }

    public int getGridY() {
	return myY;
    }

    public void setX(int x) {
	myX = x;
    }

    public void setY(int y) {
	myY = y;
    }

    public abstract ArrayList<String> getStateTypes();

    public abstract ArrayList<Color> getInitialColors();
}