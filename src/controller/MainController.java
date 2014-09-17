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

    public static void main (String[] args) throws Exception {

        messages = ResourceBundle.getBundle("messages", Locale.US);
        launch(args);
    }

    @Override
    public void start (Stage s) throws Exception {
        // create our UI
        userInterface = new UserInterface(s, this);
        setSimulationSpeed(.1);
    }

    /**
     * Function to do each game frame
     */
    private EventHandler<ActionEvent> oneFrame = new EventHandler<ActionEvent>() {
        @Override
        public void handle (ActionEvent evt) {
            stepSimulation();
        }
    };

    /**
     * 
     * change the simulation speed
     * 
     * @param speed
     *        speed of the simulation
     */
    public void setSimulationSpeed (double speed) {
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
     *        data to parse
     */
    public void initializeSimulationWithData (File XMLData) {
        try {
            @SuppressWarnings("unchecked")
            List<TestCell> cellList = Parser.parserXml(XMLData.getAbsolutePath());
            // initializeSimulationObjects(cellList);
        }
        catch (Exception e) {
            System.out.println(messages.getString("process_file_error"));
        }
    }

    /**
     * take the data array given by the parser and create patches and cell objects
     * 
     * @param cellList
     *        list given by the parser
     */
    private void initializeSimulationObjects (List<TestCell> cellList) {
        for (TestCell c : cellList) {
            try {
                // create a patch object at the x and y location
                // Patch patch = new Patch();
                // create a cell object
                String classPathAndName = messages.getString("cell_bundle") + ".";// +c.cellType;
                Class<?> cellClass = Class.forName(classPathAndName);
                System.out.println(cellClass);
                Cell cell = (Cell) cellClass.newInstance();
                // assign the cell to the patch

                // add the patch to grid manager

            }
            catch (ClassNotFoundException e) {
                System.out.println(messages.getString("class_not_found_error"));
            }
            catch (InstantiationException e) {
                System.out.println(messages.getString("instantiation_error"));
            }
            catch (IllegalAccessException e) {
                System.out.println(messages.getString("illegal_access_error"));
            }
        }
    }

    /**
     * starts the simulation
     */
    public void startSimulation () {
        // start the animation, assuming there is one
        if (animation != null) {
            animation.play();
        }
    }

    /**
     * stops the simulation
     */
    public void stopSimulation () {
        // stop the animation, assuming there is one
        if (animation != null) {
            animation.stop();
        }
    }

    /**
     * increment the simulation by one frame
     */
    public void stepSimulation () {
        // tell the grid manager to process cell updates
        System.out.println("new frame");
    }

}
