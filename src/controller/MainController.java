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
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
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
    private UserInterface userInterface;
    private static ResourceBundle messages;
    private Timeline animation;
    static Grid grid;
    private GridInfo infoSheet = new GridInfo();
  // private SimulationChart myChart;

    public static void main(String[] args) throws Exception {

	messages = ResourceBundle.getBundle("messages", Locale.US);
	launch(args);
    }

    @Override
    public void start(Stage s) throws Exception {
	// create our UI
	userInterface = new UserInterface(s, this);
	setSimulationSpeed(.1, false);
    }

    /**
     * Function to do each game frame
     */
    private EventHandler<ActionEvent> oneFrame = new EventHandler<ActionEvent>() {
	@Override
	public void handle(ActionEvent evt) {
	    stepSimulation();
	}
    };

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
	if (animation == null) {
	    animation = new Timeline();
	}
	animation.stop();
	animation.setCycleCount(Animation.INDEFINITE);
	animation.getKeyFrames().clear();
	animation.getKeyFrames().add(frame);
	if (play) {
	    animation.play();
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
	userInterface.reset();
	stopSimulation();

	try {
	    @SuppressWarnings("unchecked")
	    List<GridRows> gridRows = Parser.parserXml(XMLData
		    .getAbsolutePath());
	    initializeSimulationObjects(gridRows);

	} catch (Exception e) {
	    e.printStackTrace();
	    System.out.println(messages.getString("process_file_error"));
	}
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
	    ArrayList<Patch> patchList = new ArrayList<Patch>();
	    int width = infoSheet.getWidth();
	    int height = infoSheet.getHeight();
	    int gridWidth = (gridRows.get(0).states.length() + 1) / 2;
	    int gridHeight = gridRows.size();
	    if (width < 0) {
		infoSheet.setWidth(10);
		width = 10;
		infoSheet.useGivenGrid = false;
	    }
	    if (height < 0) {
		infoSheet.setHeight(10);
		height = 10;
		infoSheet.useGivenGrid = false;
	    }
	    if (width > gridWidth) {
		infoSheet.setWidth(gridWidth);
		width = gridWidth;
	    }
	    if (height > gridHeight) {
		infoSheet.setHeight(gridHeight);
		height = gridHeight;
	    }

	    if (!infoSheet.useGivenGrid) {
		gridRows = randomizeGrid();
	    }
	    createGrid(width, height);
	   //Refactor
	    myChart = new SimulationChart(grid);
	    userInterface.setGrid(grid);
	    userInterface.setChart(myChart);
	    //Refactor
	    myChart.setPrefHeight(height);
	    myChart.setPrefWidth(width);
	    for (int j = 0; j < height; j++) {
		String[] currentRow = gridRows.get(j).states.split(" ");

		for (int i = 0; i < width; i++) {
		    // create a patch object at the x and y location
		    // create a cell object
		    String classPathAndName = messages.getString("cell_bundle")
			    + "." + infoSheet.getCellType();
		    Class<?> cellClass = Class.forName(classPathAndName);
		    Cell cell = (Cell) cellClass.newInstance();
		    cell.setX(i);
		    cell.setY(j);
		    int state = Integer.parseInt(currentRow[i]);
		    cell.setState(state);
		    Patch currentPatch;
		    if (infoSheet.getPatchType().equals("Default")) {
			currentPatch = new Patch(i, j, grid);
		    } else {
			String patchPathAndName = messages
				.getString("cell_bundle")
				+ "."
				+ infoSheet.getPatchType();
			Class<?> patchClass = Class.forName(patchPathAndName);
			currentPatch = (Patch) patchClass.newInstance();
			currentPatch.initialize(i, j, grid);
		    }
		    currentPatch.setCellClass(cellClass);

		    // add the patch to grid manager
		    grid.addPatchAtPoint(currentPatch);
		    // assign the cell to the patch
		    if (state > 0) {
			currentPatch.addCell(cell);
		    }
		    // add patch for later
		    patchList.add(currentPatch);
		}

	    }
	    //TODO
	    
	    grid.initializeCellCounts();
	    // now that we have all the patches, assign neighbors to each one
	    // TODO Grid has a method for this...
	    for (Patch p : patchList) {
		p.getNeighbors();
	    }

	} catch (ClassNotFoundException e) {
	    System.out.println(messages.getString("class_not_found_error"));
	} catch (InstantiationException e) {
	    System.out.println(messages.getString("instantiation_error"));
	} catch (IllegalAccessException e) {
	    System.out.println(messages.getString("illegal_access_error"));
	}

    }

    private List<GridRows> randomizeGrid() {
	List<GridRows> randomizedRows = new ArrayList<GridRows>();
	String currentRow = "";
	GridRows currentGridRow = new GridRows();
	for (int i = 0; i < infoSheet.getHeight(); i++) {
	    currentRow = "";
	    currentGridRow = new GridRows();
	    currentGridRow.id = i;
	    for (int j = 0; j < infoSheet.getWidth(); j++) {
		currentRow += Long.toString(((Math.round(Math.random() * 2))))
			+ " ";
	    }
	    currentGridRow.states = currentRow;
	    randomizedRows.add(currentGridRow);
	}
	return randomizedRows;
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
	if (grid != null) {
	    userInterface.removeNode(grid);
	}
	grid = new Grid(width, height);
	grid.setLayoutX(0);
	grid.setLayoutY(120);
	grid.setMinHeight(userInterface.GRID_HEIGHT);
	grid.setMinWidth(userInterface.GRID_WIDTH);
	userInterface.addNode(grid);
	grid.setOnMouseClicked(new EventHandler<MouseEvent>() {
	    @Override
	    public void handle(MouseEvent mouseEvent) {
		toggleCellStateForMouseEvent(mouseEvent);
	    }
	});
    }

    // TODO Implementation, clicks for empty patches as well?
    /**
     * Clicking empty cell creates a new cell there. Clicking on a cell
     * decreases its state value by 1.
     */
    private void toggleCellStateForMouseEvent(MouseEvent mouseEvent) {
	Patch selectedPatch = grid.getPatchAtCoordinate(
		(int) mouseEvent.getSceneX(), (int) mouseEvent.getSceneY());
	if (selectedPatch != null) {
	    selectedPatch.toggleCellState();
	}
    }

    /**
     * starts the simulation
     */
    public void startSimulation() {
	// start the animation, assuming there is one
	if (animation != null) {
	    animation.play();
	}
    }

    /**
     * stops the simulation
     */
    public void stopSimulation() {
	// stop the animation, assuming there is one
	if (animation != null) {
	    animation.stop();
	}
    }

    /**
     * increment the simulation by one frame
     */
    public void stepSimulation() {
	// tell the grid to process cell updates
	// System.out.println("new frame");
	if (grid != null) {
	    grid.updateGrid();
	}
	if(myChart!=null)
	{
	    myChart.updateDisplay();
	}
    }

}
