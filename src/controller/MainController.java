package controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import simulationObjects.Cell;
import simulationObjects.Patch;

/**
 *
 * The main controller responsible for the gameloop, as well as creating
 * instances of the user interface and grid
 *
 * @author Davis Gossage
 *
 */

public class MainController extends Application {
    public static void main(String[] args) throws Exception {

	ourProperties = ResourceBundle.getBundle("messages", Locale.US);
	launch(args);
    }

    private static ResourceBundle ourProperties;
    private static final double DEFAULT_SPEED = .1;
    private static final int DEFAULT_WIDTH = 10;
    private static final int DEFAULT_HEIGHT = 10;
    private UserInterface myUserInterface;
    private Timeline myAnimation;
    private Grid myGrid;
    private GridInfo myInfoSheet = new GridInfo();
    private SimulationChart myChart;
    private int myGridWidth;

    private int myGridHeight;

    /**
     * Function to do each game frame
     */
    private EventHandler<ActionEvent> oneFrame = new EventHandler<ActionEvent>() {
	@Override
	public void handle(ActionEvent evt) {
	    stepSimulation();
	}
    };

    protected Cell createCell(int i, int j, int state, Class<?> cellClass)
	    throws InstantiationException, IllegalAccessException {
	Cell cell = (Cell) cellClass.newInstance();
	cell.setX(i);
	cell.setY(j);
	cell.setState(state);
	return cell;
    }

    /**
     * create an instance of the grid set column and row constraints and add to
     * the scene
     *
     * @param width
     *            width of the grid
     * @param height
     *            height of the grid
     */

    private void createGrid(int width, int height) {
	if (myGrid != null) {
	    myUserInterface.removeNode(myGrid);
	}
	myGrid = new Grid(width, height);
	myGrid.setLayoutX(0);
	myGrid.setLayoutY(120);
	myGrid.setMinHeight(UserInterface.GRID_HEIGHT);
	myGrid.setMinWidth(UserInterface.GRID_WIDTH);
	myUserInterface.addNode(myGrid);
	myGrid.setOnMouseClicked(new EventHandler<MouseEvent>() {
	    @Override
	    public void handle(MouseEvent mouseEvent) {
		toggleCellStateForMouseEvent(mouseEvent);
	    }
	});
    }

    protected void createPopulationChart(int width, int height) {
	myChart = new SimulationChart(myGrid);
	myChart.setPrefHeight(height);
	myChart.setPrefWidth(width);
    }

    /**
     * take the data array given by the parser and create patches and cell
     * objects
     *
     * @param gridRows
     *            list given by the parser
     */
    private void initializeSimulationObjects(List<GridRows> gridRows) {
	try {
	    List<Patch> patchList = new ArrayList<Patch>();
	    setupGridConstraints(gridRows);
	    createGrid(myGridWidth, myGridHeight);
	    // Refactor
	    myChart = new SimulationChart(myGrid);
	    myUserInterface.setGrid(myGrid);
	    myUserInterface.setChart(myChart);

	    for (int j = 0; j < myGridHeight; j++) {
		String[] currentRow = gridRows.get(j).states.split(" ");

		for (int i = 0; i < myGridWidth; i++) {
		    // create a patch object at the x and y location
		    // create a cell object
		    int state = Integer.parseInt(currentRow[i]);
		    String classPathAndName = ourProperties
			    .getString("cell_bundle")
			    + "."
			    + myInfoSheet.getCellType();
		    Class<?> cellClass = Class.forName(classPathAndName);
		    Cell cell = createCell(i, j, state, cellClass);

		    PatchFactory myPatchFactory = new PatchFactory(myInfoSheet);
		    Patch currentPatch = myPatchFactory.createPatch(myGrid, i,
			    j);

		    currentPatch.setCellClass(cellClass);

		    // add the patch to grid manager
		    myGrid.addPatchAtPoint(currentPatch);
		    // assign the cell to the patch
		    if (state > 0) {
			currentPatch.addCell(cell);
		    }
		    // add patch for later
		    patchList.add(currentPatch);
		}

	    }
	    // Finishes initializations
	    myGrid.updateAllNeighborhoods();
	    myGrid.initializeCellCounts();
	} catch (ClassNotFoundException e) {
	    System.out
		    .println(ourProperties.getString("class_not_found_error"));
	} catch (InstantiationException e) {
	    System.out.println(ourProperties.getString("instantiation_error"));
	} catch (IllegalAccessException e) {
	    System.out.println(ourProperties.getString("illegal_access_error"));
	}

    }

    /**
     * parse the XMLData and check for exceptions
     *
     * @param XMLData
     *            data to parse
     */
    public void initializeSimulationWithData(File XMLData) {

	// reinitializes everything
	myUserInterface.reset();
	stopSimulation();

	try {
	    @SuppressWarnings("unchecked")
	    List<GridRows> gridRows = Parser.parserXml(XMLData
		    .getAbsolutePath());
	    initializeSimulationObjects(gridRows);

	} catch (Exception e) {
	    e.printStackTrace();
	    System.out.println(ourProperties.getString("process_file_error"));
	}
    }

    private List<GridRows> randomizeGrid() {
	List<GridRows> randomizedRows = new ArrayList<GridRows>();
	String currentRow = "";
	GridRows currentGridRow = new GridRows();
	for (int i = 0; i < myInfoSheet.getHeight(); i++) {
	    currentRow = "";
	    currentGridRow = new GridRows();
	    currentGridRow.id = i;
	    for (int j = 0; j < myInfoSheet.getWidth(); j++) {
		currentRow += Long.toString(Math.round(Math.random() * 2))
			+ " ";
	    }
	    currentGridRow.states = currentRow;
	    randomizedRows.add(currentGridRow);
	}
	return randomizedRows;
    }

    /**
     *
     * change the simulation speed
     *
     * @param speed
     *            speed of the simulation
     * @param play
     *            whether the animation should play after the speed change
     */
    public void setSimulationSpeed(double speed, boolean play) {
	// set game loop
	KeyFrame frame = new KeyFrame(Duration.millis(100 / speed), oneFrame);
	if (myAnimation == null) {
	    myAnimation = new Timeline();
	}
	myAnimation.stop();
	myAnimation.setCycleCount(Animation.INDEFINITE);
	myAnimation.getKeyFrames().clear();
	myAnimation.getKeyFrames().add(frame);
	if (play) {
	    myAnimation.play();
	}
    }

    private void setupGridConstraints(List<GridRows> gridRows) {
	myGridWidth = myInfoSheet.getWidth();
	myGridHeight = myInfoSheet.getHeight();
	int gridWidth = (gridRows.get(0).states.length() + 1) / 2;
	int gridHeight = gridRows.size();
	if (myGridWidth < 0) {
	    myInfoSheet.setWidth(DEFAULT_WIDTH);
	    myGridWidth = DEFAULT_WIDTH;
	    myInfoSheet.useGivenGrid = false;
	}
	if (gridHeight < 0) {
	    myInfoSheet.setHeight(DEFAULT_HEIGHT);
	    gridHeight = DEFAULT_HEIGHT;
	    myInfoSheet.useGivenGrid = false;
	}
	if (myGridWidth > gridWidth) {
	    myInfoSheet.setWidth(gridWidth);
	    myGridWidth = gridWidth;
	}
	if (gridHeight > gridHeight) {
	    myInfoSheet.setHeight(gridHeight);
	    gridHeight = gridHeight;
	}
	if (!myInfoSheet.useGivenGrid) {
	    gridRows = randomizeGrid();
	}
    }

    @Override
    public void start(Stage s) throws Exception {
	// create our UI
	myUserInterface = new UserInterface(s, this);
	setSimulationSpeed(DEFAULT_SPEED, false);
    }

    /**
     * starts the simulation
     */
    public void startSimulation() {
	// start the animation, assuming there is one
	if (myAnimation != null) {
	    myAnimation.play();
	}
    }

    /**
     * increment the simulation by one frame
     */
    public void stepSimulation() {
	// tell the grid to process cell updates
	// System.out.println("new frame");
	if (myGrid != null) {
	    myGrid.updateGrid();
	}
	if (myChart != null) {
	    myChart.updateDisplay();
	}
    }

    /**
     * stops the simulation
     */
    public void stopSimulation() {
	// stop the animation, assuming there is one
	if (myAnimation != null) {
	    myAnimation.stop();
	}
    }

    /**
     * Clicking empty cell creates a new cell there. Clicking on a cell
     * decreases its state value by 1.
     */
    private void toggleCellStateForMouseEvent(MouseEvent mouseEvent) {
	Patch selectedPatch = myGrid.getPatchAtCoordinate(
		(int) mouseEvent.getX(), (int) mouseEvent.getY());
	if (selectedPatch != null) {
	    selectedPatch.toggleCellState();
	}
    }

}
