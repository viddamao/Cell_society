package simulationObjects;

import java.awt.Point;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import controller.GridInfo;

/**
 * Abstract class for the graphical representation of the patch
 * 
 * @author Davis
 *
 */

public abstract class PatchBody extends Polygon {

    protected GridInfo infoSheet = new GridInfo();

    private int myPatchHeight;
    private int myPatchWidth;
    private Point myCenter;
    public int myX;

    public int myY;

    public PatchBody(int x, int y) {
	super();
	myX = x;
	myY = y;
    }

    /**
     * calculate the points needed for the specific polygon given our height,
     * width, grid x, and grid y
     */
    public abstract void buildBody();

    /**
     * create a polygon given an array of points
     * 
     * @param pts
     *            array of x and y points used to create the polygon
     */
    public void createPolyFromPoints(Double[] pts) {
	getPoints().addAll(pts);
	setFill(new Color(0f, 0f, 0f, .0f));
	setStrokeWidth(.5);
	setStroke(Color.BLACK);
    }

    public void setPatchHeight(int h) {
	myPatchHeight = h;
    }

    public void setPatchWidth(int w) {
	myPatchWidth = w;
    }

    public int getPatchHeight() {
	return myPatchHeight;
    }

    public int getPatchWidth() {
	return myPatchWidth;
    }

    public Point getCenter() {
	return myCenter;
    }

    public void setCenter(Point c) {
	myCenter = c;
    }

}
