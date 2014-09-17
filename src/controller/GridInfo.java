package controller;

public class GridInfo {
    private static int myWidth;
    private static int myHeight;
    private static String myType;
    private static int myAdjacentType;

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

    public int getWidth() {
	return myWidth;
    }

    public int getHeight() {
	return myHeight;
    }

    public String getCellType() {
	return myType;
    }

}
