package simulationObjects;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import controller.GridInfo;

/**
 * Parent Class of Cell Hierarchy
 *
 * @author Everyone
 *
 */
public abstract class Cell extends Rectangle {

    protected GridInfo infoSheet = new GridInfo();

    protected int myX;
    protected int myY;
    protected int myState;

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

    public abstract void toggleState();

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

    public void addOne(HashMap<Integer, Integer> cellTypeNumber, int index) {
	int temp = 1;
	if (!(cellTypeNumber.get(index) == null)) {
	    temp = cellTypeNumber.get(index).intValue() + 1;
	}
	cellTypeNumber.put(index, temp);

    }

    public void subtractOne(HashMap<Integer, Integer> cellTypeNumber, int index) {
	int temp = 1;
	if (!(cellTypeNumber.get(index) == null)) {
	    temp = cellTypeNumber.get(index).intValue() - 1;
	}
	cellTypeNumber.put(index, temp);

    }

}
