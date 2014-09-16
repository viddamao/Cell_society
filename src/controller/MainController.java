package controller;

import java.io.File;
import java.util.List;
import javafx.application.Application;
import javafx.stage.Stage;
import java.util.*;

public class MainController extends Application {
    private UserInterface userInterface;
    private static ResourceBundle messages;

    public static void main(String[] args) throws Exception {

	Parser.parserXml("gridInput_Modified.xml");
	Locale currentLocale = new Locale("en", "US");
	messages = ResourceBundle.getBundle("messages", currentLocale);
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
	    System.out.println(messages.getString("process_file_error"));
	}
    }
    
    private void initializeSimulationObjects(List<TestCell> cellList){
        for (TestCell c : cellList){
            try {
                //create a patch object at the x and y location
                
                //create a cell object
                String classPathAndName = messages.getString("cell_bundle")+".";//+c.cellType;
                Class<?> cellClass = Class.forName(classPathAndName);
                System.out.println(cellClass);
                Object cell = cellClass.newInstance();
                //assign the cell to the patch
                //add the patch to grid manager
                
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
