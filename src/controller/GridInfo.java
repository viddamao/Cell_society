package controller;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.paint.Color;

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
    private static String myPatchType="Default";
    private static int myAdjacentType=8;
    private static double myParameter=0.7;
    private static HashMap<String, Color> stateColors = new HashMap<String, Color>();
    private static ArrayList<String> myStateTypes;

    public void setWidth(int width) {
	myWidth = width;
    }

    public void setHeight(int height) {
	myHeight = height;
    }

    public void setType(String type) {
	myType = type;

    }

    public void setPatchType(String type) {
	myPatchType = type;

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

    public String getPatchType() {
	return myPatchType;

    }

    public double getParam() {
	return myParameter;
    }

    public int getAdjacentType() {
	return myAdjacentType;
    }

    public void setColor(String stateType, Color myColor) {
	stateColors.put(stateType, myColor);
    }

    public Color getColor(String stateType) {
	return stateColors.get(stateType);
    }

    public void setStateTypes(ArrayList<String> stateTypes) {
	myStateTypes = stateTypes;
    }

    public ArrayList<String> getStateTypes() {
	return myStateTypes;
    }

}
