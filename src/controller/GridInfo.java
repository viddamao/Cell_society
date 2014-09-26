package controller;

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
    public boolean useGivenGrid = true;

    public void setWidth (int width) {
        myWidth = width;
    }

    public void setHeight (int height) {
        myHeight = height;
    }

    public void setType (String type) {
        myType = type;

    }

    public void setPatchType (String type) {
        myPatchType = type;

    }

    public void setAdjacent (int adjacent) {
        myAdjacentType = adjacent;

    }

    public void setParameter (double d) {
        myParameter = d;
    }

    public int getWidth () {
        return myWidth;
    }

    public int getHeight () {
        return myHeight;
    }

    public String getCellType () {
        return myType;
    }

    public String getPatchType () {
        return myPatchType;

    }

    public int getMaxCellState ()
    {
        return maxCellState;
    }

    public double getParam () {
        return myParameter;
    }

    public int getAdjacentType () {
        return myAdjacentType;
    }

    public void setColor (String stateType, Color myColor) {
        stateColors.put(stateType, myColor);
    }

    public Color getColor (String stateType) {
        return stateColors.get(stateType);
    }

    public void setStateTypes (List<String> stateTypes) {
        myStateTypes = stateTypes;
    }

    public List<String> getStateTypes () {
        return myStateTypes;
    }

    public void setMaxCellState (int state)
    {
        maxCellState = state;
    }

}
