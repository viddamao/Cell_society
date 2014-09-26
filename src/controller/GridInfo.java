package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    private static int myWidth = -1;
    private static int myHeight = -1;
    private static String myType;
    private static String myPatchType = "Default";
    private static int myAdjacentType = 8;
    private static double myParameter = 0.7;
    private static HashMap<String, Color> stateColors = new HashMap<String, Color>();
    private static List<String> myStateTypes;
    private static int maxCellState;
    private static List<Integer> paramList = new ArrayList<>();
    public boolean useGivenGrid = true;

    public int getAdjacentType() {
	return myAdjacentType;
    }

    public String getCellType() {
	return myType;
    }

    public Color getColor(String stateType) {
	return stateColors.get(stateType);
    }

    public int getHeight() {
	return myHeight;
    }

    public int getMaxCellState() {
	return maxCellState;
    }

    public double getParam() {
	return myParameter;
    }

    public String getPatchType() {
	return myPatchType;

    }

    public List<String> getStateTypes() {
	return myStateTypes;
    }

    public int getWidth() {
	return myWidth;
    }
    
    public List<Integer> getParamList()
    {
        return paramList;
    }
    
    public void setAdjacent(int adjacent) {
	myAdjacentType = adjacent;

    }

    public void setColor(String stateType, Color myColor) {
	stateColors.put(stateType, myColor);
    }

    public void setHeight(int height) {
	myHeight = height;
    }

    public void setMaxCellState(int state) {
	maxCellState = state;
    }

    public void setParameter(double d) {
	myParameter = d;
    }

    public void setPatchType(String type) {
	myPatchType = type;

    }

    public void setStateTypes(List<String> stateTypes) {
	myStateTypes = stateTypes;
    }

    public void setType(String type) {
	myType = type;

    }

    public void setWidth(int width) {
	myWidth = width;
    }

    public void setParamList (String content) {
        String[] buffer = content.split(" ");
        for(int i = 0; i < buffer.length; i++)
        {
            paramList.add((Integer)Integer.valueOf(buffer[i]));
        }
    }

}
