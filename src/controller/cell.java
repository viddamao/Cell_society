package controller;

/*
 * 
 * Just for xml parser test
 */
public class cell {
    int id;
    int xLoc;
    int yLoc;
    int property;

    @Override
    public String toString () {
        return ((Integer) xLoc).toString() + " " + ((Integer) yLoc).toString() + " " +
               ((Integer) property).toString();

    }

}
