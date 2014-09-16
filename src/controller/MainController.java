package controller;

import java.io.File;
import java.util.List;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainController extends Application {
    private UserInterface userInterface;

    public static void main(String[] args) throws Exception {

	Parser.parserXml("gridInput_Modified.xml");

	launch(args);
    }

    @Override
    public void start(Stage s) throws Exception {
	// create our UI
	userInterface = new UserInterface(s, this);
    }

    public void initializeSimulationWithData(File XMLData) {
	try {
	    @SuppressWarnings("unchecked")
        List<TestCell> cellList = Parser.parserXml(XMLData
		    .getAbsolutePath());
	    initializeSimulationObjects(cellList);
	} catch (Exception e) {
	    System.out.println("Error processing file, make sure it's XML.");
	}
    }
    
    private void initializeSimulationObjects(List<TestCell> cellList){
        for (TestCell c : cellList){
            try {
                //create a patch object at the x and y location
                
                //create a cell object
                String classPathAndName = "simulationObjects."+c.cellType;
                Class<?> cellClass = Class.forName(classPathAndName);
                System.out.println(cellClass);
                Object cell = cellClass.newInstance();
                //assign the cell to the patch
                //add the patch to grid manager
                
            }
            catch (ClassNotFoundException e) {
                System.out.println("One or more cell classes from your XML file could not be found.");
            }
            catch (InstantiationException e) {
                System.out.println("One or more paramaters could not be applied to the simulation.");
            }
            catch (IllegalAccessException e) {
                System.out.println("Couldn't create a class from the XML file: illegal access.");
            }
        }
    }

    public void startSimulation() {
	// TODO Auto-generated method stub

    }

    public void stopSimulation() {
	// TODO Auto-generated method stub

    }

    public void stepSimulation() {
	// TODO Auto-generated method stub

    }

}
