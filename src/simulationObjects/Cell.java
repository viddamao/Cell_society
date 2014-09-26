package simulationObjects;

import java.util.List;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import controller.GridInfo;

/**
 * Parent Class of Cell Hierarchy TODO Change extends to Polygon?
 *
 * @author Everyone
 *
 */
public abstract class Cell extends Rectangle {

    // modified the order for processing 4 directions
    public enum relativePosition {
	NORTH, SOUTH, EAST, WEST, SOUTHEAST, NORTHEAST, SOUTHWEST, NORTHWEST
    }

    protected GridInfo infoSheet = new GridInfo();
    protected int myX;
    protected int myY;
    protected int myState;

    protected Patch myPatch;

    public Cell() {

    }

    public int getGridX() {
	return myX;
    }

    // REFACTOR SOME OF THESE METHODS...

    public int getGridY() {
	return myY;
    }

    public abstract List<Color> getInitialColors();

    public abstract int getNextState();

    public abstract int getState();

    public abstract List<String> getStateTypes();

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

    public abstract void prepareToUpdate(Patch currentPatch,
	    List<Patch> neighbors);

    public abstract void setState(int state);

    public void setX(int x) {
	myX = x;
    }

    public void setY(int y) {
	myY = y;
    }

    public abstract void update(Patch currentPatch, List<Patch> neighbors);
}
