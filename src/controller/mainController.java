package controller;

import javafx.application.Application;
import javafx.stage.Stage;


public class mainController extends Application {

	public static void main (String[] args) throws Exception{
	
	    parser.parserXml("gridInput.xml");
	
	    launch(args);
	}
	
	@Override
	public void start(Stage s) throws Exception {
		//create our UI
		new UserInterface(s);
	}
    
    
}
