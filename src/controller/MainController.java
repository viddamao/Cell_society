package controller;

import java.io.File;
import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.*;
import simulationObjects.*;

public class MainController extends Application {
    private UserInterface userInterface;
    private static ResourceBundle messages;
    private Timeline animation;
    private GridManager gridManager;
    private GridInfo object = new GridInfo();

    public static void main(String[] args) throws Exception {

	messages = ResourceBundle.getBundle("messages", Locale.US);
	launch(args);
    }

    @Override
    public void start(Stage s) throws Exception {
	// create our UI
	userInterface = new UserInterface(s, this);
	setSimulationSpeed(.1);
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
     */
    public void setSimulationSpeed(double speed) {
	// set game loop
	KeyFrame frame = new KeyFrame(Duration.millis(100 / speed), oneFrame);
	if (animation == null) {
	    animation = new Timeline();
	}
	animation.stop();
	animation.setCycleCount(Timeline.INDEFINITE);
	animation.getKeyFrames().clear();
	animation.getKeyFrames().add(frame);
	animation.play();
    }

    /**
     * parse the XMLData and check for exceptions
     * 
     * @param XMLData
     *            data to parse
     */
    public void initializeSimulationWithData(File XMLData) {
	try {
	    @SuppressWarnings("unchecked")
	    List<TestCell> gridRows = Parser.parserXml(XMLData
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
    private void initializeSimulationObjects(List<TestCell> gridRows) {
	try {
	    ArrayList<Patch> patchList = new ArrayList<Patch>();
	    int width = object.getWidth();
	    int height = object.getHeight();
	    gridManager = new GridManager(width, height);
	    for (int j = 0; j < height; j++) {
		String[] currentRow = gridRows.get(j).states.split(" ");

		for (int i = 0; i < width; i++) {
		    // create a patch object at the x and y location
		    // create a cell object
		    String classPathAndName = messages.getString("cell_bundle")
			    + "." + object.getCellType();
		    Class<?> cellClass = Class.forName(classPathAndName);
		    Cell cell = (Cell) cellClass.newInstance();
		    cell.setX(i);
		    cell.setY(j);
		    cell.setState(Integer.parseInt(currentRow[i]));
		    Patch currentPatch = new Patch(i, j, gridManager);
		    // add the patch to the UI
		    addPatchToPane(currentPatch);
		    // assign the cell to the patch

		    currentPatch.addCell(cell);
		    // add the patch to grid manager
		    gridManager.addPatchAtPoint(currentPatch);
		    // add patch for later
		    patchList.add(currentPatch);
		}

	    }
	    // now that we have all the patches, assign neighbors to each one
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

    private void createGridManager(int width, int height){
        gridManager = new GridManager(width,height);
        gridManager.setGridLinesVisible(true);
        gridManager.setLayoutX(0);
        gridManager.setLayoutY(0);
        gridManager.setMinHeight(userInterface.GRID_HEIGHT);
        gridManager.setMinWidth(userInterface.GRID_WIDTH);
        gridManager.setPrefSize(width, height);
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
	// tell the grid manager to process cell updates
	// System.out.println("new frame");
	if (gridManager != null) {
	    gridManager.step();
	}
    }

}
