package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class UserInterface {
	private mainController myMainController;
	private Pane rootPane;
	
	public UserInterface(Stage s, mainController mainController){
		myMainController = mainController;
    	Stage myStage = s;
        myStage.setTitle("Cell Society Team 4");

        rootPane = new Pane();
        Scene myScene = new Scene(rootPane,500,540);
        myStage.setScene(myScene);
        myStage.show();
        createAndHandleButtons();
	}
	
	private void createAndHandleButtons(){
		UserInterfaceButton startButton = new UserInterfaceButton("Start",0,510);
        rootPane.getChildren().add(startButton);
        UserInterfaceButton stopButton = new UserInterfaceButton("Stop",60,510);
        rootPane.getChildren().add(stopButton);
        UserInterfaceButton stepButton = new UserInterfaceButton("Step",120,510);
        rootPane.getChildren().add(stepButton);
        UserInterfaceButton fileButton = new UserInterfaceButton("Choose File",200,510);
        rootPane.getChildren().add(fileButton);
        //this isn't the best place for these event handlers
        //should each button be a subclass of UserInterfaceButton and have a custom handler?
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                myMainController.startSimulation();
                startButton.setDisable(true);
                stopButton.setDisable(false);
            }
        });
        stopButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	myMainController.stopSimulation();
                stopButton.setDisable(true);
                startButton.setDisable(false);
            }
        });
        stepButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	myMainController.stepSimulation();
            }
        });
        fileButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                //show file dialog
            }
        });
	}
	
	public Pane getRootPane(){
		return rootPane;
	}
}
