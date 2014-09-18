package controller;

import java.util.ArrayList;

/**
 * 
 * 
 * Stores the parameter and info for the grid
 * 
 * @author Viddamao
 *
 */
public class GridInfo {
    private static int myWidth;
    private static int myHeight;
    private static String myType;
    private static int myAdjacentType;
    private static double myParameter;
    

    public void setWidth(int width) {
	myWidth = width;
    }

    public void setHeight(int height) {
	myHeight = height;
    }

    public void setType(String type) {
	myType = type;

    }
    
   
  
    
    public void setAdjacent(int adjacent) {
	myAdjacentType = adjacent;

    }

    public void setParameter(double d) {
	myParameter = d;
    }

    public int getWidth() {
	return myWidth;
    }

    public int getHeight() {
	return myHeight;
    }

    public String getCellType() {
	return myType;
    }

    public double getParam() {
	return myParameter;
    }

    public int getAdjacentType() {
	return myAdjacentType;
    }

}
