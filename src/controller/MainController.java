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
import simulationObjects.Cell;
import simulationObjects.Patch;


public class MainController extends Application {
    private UserInterface userInterface;
    private static ResourceBundle messages;
    private Timeline animation;
    private GridManager gridManager;

    public static void main (String[] args) throws Exception {

        //Parser.parserXml("gridInput_Modified.xml");
        System.out.println(Locale.US);
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
            System.out.println("new frame");
        }
    };
    
    /**
     * 
     * change the simulation speed
     * @param speed
     * speed of the simulation
     */
    public void setSimulationSpeed(double speed){
        //set game loop
        KeyFrame frame = new KeyFrame(Duration.millis(100/speed), oneFrame);
        if (animation == null){
            animation = new Timeline();
        }
        animation.stop();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().clear();
        animation.getKeyFrames().add(frame);
        animation.play();
    }

    public void initializeSimulationWithData (File XMLData) {
        try {
            @SuppressWarnings("unchecked")
            List<TestCell> cellList = Parser.parserXml(XMLData.getAbsolutePath());
            //initializeSimulationObjects(cellList);
        }
        catch (Exception e) {
            System.out.println(messages.getString("process_file_error"));
        }
    }

    private void initializeSimulationObjects (List<TestCell> cellList) {
        for (TestCell c : cellList) {
            try {
                // create a patch object at the x and y location
                //Patch patch = new Patch();
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

    public void startSimulation () {
        // TODO Auto-generated method stub

    }

    public void stopSimulation () {
        // TODO Auto-generated method stub

    }

    public void stepSimulation () {
        // TODO Auto-generated method stub

    }

}
