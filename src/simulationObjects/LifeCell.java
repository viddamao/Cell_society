package simulationObjects;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;


/**
 * Cell for Game of Life Simulation
 * @author Will Chang
 *
 */
public class LifeCell extends Cell {

    public LifeCell () {
        super();
        setFill(infoSheet.getColor("CELL"));
    }

    public LifeCell (int x, int y) {
        super();
        myX = x;
        myY = y;
        myState = 1;
        setFill(infoSheet.getColor("CELL"));

    }

    @Override
    public int getState () {
        return myState;
    }

    @Override
    public void setState (int state) {
        myState = state;
    }

    /**
     * Update was not necessary for this simulation
     */
    @Override
    public void update (Patch currentPatch, List<Patch> neighbors) {
    }

    /**
     * PrepareToUpdate was not necessary for this simulation
     */
    @Override
    public void prepareToUpdate (Patch currentPatch, List<Patch> neighbors) {

    }

    @Override
    public List<String> getStateTypes () {
        List<String> myStateType = new ArrayList<String>();
        myStateType.add("BACKGROUND");
        myStateType.add("CELL");
        return myStateType;
    }

    @Override
    public List<Color> getInitialColors () {
        List<Color> myStateColors = new ArrayList<Color>();
        myStateColors.add(Color.WHITE);
        myStateColors.add(Color.BLACK);
        return myStateColors;
    }

    @Override
    public int getNextState() {
	// TODO return the next cell state
	return -1;
    }

}
