package simulationObjects;

import java.util.ArrayList;
import javafx.scene.paint.Color;

/**
 * 
 * @author Will Chang
 *
 */
public class LifeCell extends Cell {

    public LifeCell() {
	super();
	setFill(infoSheet.getColor("CELL"));
    }

    public LifeCell(int x, int y) {
	super();
	myX = x;
	myY = y;
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

    @Override
    public void update (Patch currentPatch, ArrayList<Patch> neighbors) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void prepareToUpdate(Patch currentPatch, ArrayList<Patch> neighbors) {
	// TODO Auto-generated method stub

    }

    @Override
    public ArrayList<String> getStateTypes() {
	ArrayList<String> myStateType = new ArrayList<String>();
	myStateType.add("CELL");
	return myStateType;
    }

    @Override
    public ArrayList<Color> getInitialColors() {
	ArrayList<Color> myStateColors = new ArrayList<Color>();
	myStateColors.add(Color.BLACK);
	return myStateColors;
    }

}
