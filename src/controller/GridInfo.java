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
    private ArrayList<String> subTypeList = new ArrayList<>();
    private static String mySub1;
    private static String mySub2;

    public void setWidth(int width) {
	myWidth = width;
    }

    public void setHeight(int height) {
	myHeight = height;
    }

    public void setType(String type) {
	myType = type;

    }
    
    public void addSubType(String subtype)
    {
        subTypeList.add(subtype);
    }
    
    public void addSubType1(String subtype)
    {
        mySub1 = subtype;
    }

    public void addSubType2(String subtype)
    {
        mySub2 = subtype;
    }
    
    public void setAdjacent(int adjacent) {
	myAdjacentType = adjacent;

    }

    public void setParameter(double d) {
	myParameter = d;
    }
    public ArrayList<String> getSubTypeList()
    {
        return subTypeList;
    }
    
    public String getSubType(int index) {
        return subTypeList.get(index);
    }

    public String getSub1() {
        return mySub1;
    }
    public String getSub2() {
        return mySub2;
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
