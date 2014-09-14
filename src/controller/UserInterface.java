package controller;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class UserInterface {
	public UserInterface(Stage s){
    	Stage myStage = s;
        myStage.setTitle("Cell Society Team 4");

        Pane rootPane = new Pane();
        Scene myScene = new Scene(rootPane,300,300);
        myStage.setScene(myScene);
        myStage.show();
	}
}
