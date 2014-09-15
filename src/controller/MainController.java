package controller;

import java.io.File;
import java.util.List;
import javafx.application.Application;
import javafx.stage.Stage;


public class MainController extends Application {
    private UserInterface userInterface;

    public static void main (String[] args) throws Exception {

        Parser.parserXml("gridInput.xml");

        launch(args);
    }

    @Override
    public void start (Stage s) throws Exception {
        // create our UI
        userInterface = new UserInterface(s, this);
    }

    public void initializeSimulationWithData (File XMLData) {
        try {
            List<cell> cellList = Parser.parserXml(XMLData.getAbsolutePath());
            
        }
        catch (Exception e) {
            System.out.println("Error processing file, make sure it's XML.");
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
