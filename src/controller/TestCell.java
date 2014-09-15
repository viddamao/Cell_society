package controller;

/*
 * 
 * Just for xml parser test
 */
public class TestCell {
    int id;
    int xLoc;
    int yLoc;
    int currentState;
    String cellType;

    @Override
    public String toString() {
	return ((Integer) xLoc).toString() + " " + ((Integer) yLoc).toString()
		+ " " + ((Integer) currentState).toString() + " " + cellType;

    }

}
