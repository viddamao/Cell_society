package simulationObjects;

import java.util.ArrayList;
import controller.GridInfo;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class Cell extends Rectangle {
    
    protected GridInfo infoSheet = new GridInfo();

    protected int myX;
    protected int myY;
    protected int myState;

    public Cell() {
	setFill(Color.BLUE);

    }

    // modified the order for processing 4 directions
    public enum relativePosition {
	NORTH, SOUTH, EAST, WEST, SOUTHEAST, NORTHEAST, SOUTHWEST, NORTHWEST
    }

    // REFACTOR SOME OF THESE METHODS...

    public abstract int getState();

    public abstract void setState(int state);

    public abstract Patch update(Patch currentPatch, ArrayList<Patch> neighbors);

    public abstract void prepareToUpdate(Patch currentPatch,
	    ArrayList<Patch> neighbors);

    

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
