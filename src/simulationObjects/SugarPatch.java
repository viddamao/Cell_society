package simulationObjects;

import controller.Grid;

public class SugarPatch extends Patch {
    private int currentSugar = 0;
    protected int vision = 1;
    protected int colorGradient = 4;
    protected int sugarGrowBackRate = 1;

    public SugarPatch() {
	super();
    }

    public SugarPatch(int x, int y, Grid m) {
	super(x, y, m);
    }

    /**
     * creates a patch body for the patch
     *
     * @param bodyType
     *            what kind of shape it is
     */
    @Override
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
	myBody.setStrokeWidth(0);
	getChildren().add(myBody);
    }

    public int getCurrentSugar() {
	return currentSugar;
    }

    @Override
    public void getNeighbors() {
	getNeighbors(vision);
    }

    public void getNeighbors(int vision) {
	// TODO needs implementation for vision>1
	myNeighbors = grid.getNeighborsAround(xCoord, yCoord);

    }

    @Override
    public void initialize(int x, int y, Grid m) {

	super.initialize(x, y, m);

    }

    public void removeSugar() {
	currentSugar = 0;
    }

    public void setColorToBody() {
	int colorType = Math.round(currentSugar / colorGradient);
	if (colorType > 4) {
	    colorType = 4;
	}
	myBody.setFill(infoSheet.getColor("CONCENTRATION_" + colorType));

    }

    public void sugarGrowBack() {
	currentSugar += sugarGrowBackRate;

    }

    @Override
    public void update() {
	// Update this
	sugarGrowBack();
	if (myCell != null) {
	    myCell.update(this, myNeighbors);
	    if (myCell != null && myCell.getState() == 0) {
		removeCell();
	    }
	}
	this.setColorToBody();

    }
}
