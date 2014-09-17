package simulationObjects;

import java.util.ArrayList;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class Cell extends Rectangle {

    protected int myX;
    protected int myY;
    protected int myState;

    public Cell() {
        setFill(Color.BLUE);
    }

    public enum relativePosition {
	NORTHWEST, WEST, SOUTHWEST, NORTH, SOUTH, NORTHEAST, EAST, SOUTHEAST
    }

    // REFACTOR SOME OF THESE METHODS...

    public abstract int getState();

    public abstract void setState(int state);

    public abstract Patch update(Patch currentPatch, ArrayList<Patch> neighbors);

    public abstract void prepareToUpdate(ArrayList<Cell> neighbors);

    public abstract boolean needUpdate(ArrayList<Cell> neighbors);

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

}
