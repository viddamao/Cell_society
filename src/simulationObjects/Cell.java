package simulationObjects;

import java.util.List;
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
    protected Patch myPatch;

    public Cell () {
    }

    /**
     * Special constructor for XML reads
     *
     * @param x
     * @param y
     * @param state
     */
    public void initialize (int x, int y, int state) {
        this.setX(x);
        this.setY(y);
        setState(state);
    }

    /**
     * Relative Positions of the cells, useful for future simulation
     * @author Will
     *
     */
    public enum relativePosition {
        NORTH, SOUTH, EAST, WEST, SOUTHEAST, NORTHEAST, SOUTHWEST, NORTHWEST
    }


    /**
     * Returns the state of the cell.
     * @return state of the cell
     */
    public abstract int getState ();

    /**
     * Sets the state of the cell
     * @param state of the cell
     */
    public abstract void setState (int state);

    /**
     * Updates the cell, applies the rules of the simulation
     * @param currentPatch the cell is on
     * @param neighbors    patches around the cell's currentPatch
     */
    public abstract void update (Patch currentPatch, List<Patch> neighbors);

    /**
     * Prepares the cell for updating
     * @param currentPatch
     * @param neighbors
     */
    public abstract void prepareToUpdate (Patch currentPatch,
                                          List<Patch> neighbors);

    /**
     * Gets the next state of the Cell
     * @return
     */
    public abstract int getNextState ();

    /**
     * Returns the x location in the grid
     * @return myX
     */
    public int getGridX () {
        return myX;
    }

    /**
     * Returns the y location in the grid
     * @returns myY
     */
    public int getGridY () {
        return myY;
    }

    /**
     * Sets x location
     * @param x 
     */
    public void setX (int x) {
        myX = x;
    }

    /**
     * Sets y location
     * @param y
     */
    public void setY (int y) {
        myY = y;
    }

    /**
     * Gets the different states of the cell
     * @return
     */
    public abstract List<String> getStateTypes ();

    /**
     * Gets the initial colors of the cell
     * @return
     */
    public abstract List<Color> getInitialColors ();
}
