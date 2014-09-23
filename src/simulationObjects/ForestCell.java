package simulationObjects;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.paint.Color;

public class ForestCell extends Cell {

    final private int EMPTY = 0;
    final private int ONFIRE = 1;
    final private int TREE = 2;
    public static HashMap<Integer, Integer> cellTypeNumber = new HashMap<Integer, Integer>();

    public ForestCell() {
	super();
    }

    @Override
    public void update(Patch currentPatch, ArrayList<Patch> neighbors) {
	switch (myState) {
	case TREE:

	    if (willCatchFire(neighbors))
		catchFire(currentPatch);
	    else {
		subtractOne(cellTypeNumber,TREE);
		this.setState(TREE);
	    }
	    break;

	case ONFIRE:
	    burnDown(currentPatch);
	    break;
	}
    }

    private void burnDown(Patch currentPatch) {
	this.setState(EMPTY);
	subtractOne(cellTypeNumber,ONFIRE);

    }

    public void catchFire(Patch currentPatch) {
	this.setState(ONFIRE);
	subtractOne(cellTypeNumber,TREE);
    }

    @Override
    public int getState() {
	return myState;
    }

    @Override
    public void setState(int state) {
	if (state == ONFIRE) {
	    setFill(infoSheet.getColor("ONFIRE"));
	    addOne(cellTypeNumber,ONFIRE);
	} else if (state == TREE) {
	    setFill(infoSheet.getColor("TREE"));
	    addOne(cellTypeNumber,TREE);
	}
	
	System.out.print(cellTypeNumber.get(TREE));
	System.out.print(" ");
	System.out.print(cellTypeNumber.get(ONFIRE));
	System.out.println();
	myState = state;
    }

    public boolean willCatchFire(ArrayList<Patch> neighbors) {
	boolean haveNeighborOnFire = false;
	for (Patch neighborPatch : neighbors)
	    if (neighborPatch.getPreviousState() == ONFIRE) {
		haveNeighborOnFire = true;
		break;
	    }
	return ((haveNeighborOnFire) && (Math.random() <= infoSheet.getParam()));

    }

    @Override
    public void prepareToUpdate(Patch currentPatch, ArrayList<Patch> neighbors) {
	currentPatch.setPreviousState(this.myState);
    }

    @Override
    public ArrayList<String> getStateTypes() {
	ArrayList<String> myStateType = new ArrayList<String>();
	myStateType.add("ONFIRE");
	myStateType.add("TREE");
	return myStateType;
    }

    @Override
    public ArrayList<Color> getInitialColors() {
	ArrayList<Color> myStateColors = new ArrayList<Color>();
	myStateColors.add(Color.RED);
	myStateColors.add(Color.GREEN);
	return myStateColors;
    }

   
}
