package simulationObjects;

import java.awt.Point;

/**
 * hexagon implementation of patch body
 *
 * @author Davis
 *
 */

public class PatchBodyHexagon extends PatchBody {

    private double mySideLength;

    public PatchBodyHexagon(int x, int y, double gridPixelHeight,
	    double gridPixelWidth, int gridHeight, int gridWidth) {
	super(x, y);
	setPatchWidth((int) (gridPixelWidth / (gridWidth * 1.5)));
	mySideLength = (double) getPatchWidth() / 2;
	setPatchHeight((int) (mySideLength * Math.sqrt(3.0)));
	buildBody();
    }

    @Override
    public void buildBody() {
	double offset = myY % 2 == 0 ? mySideLength * 1.5 : 0;
	// 6 coordinates
	// 2 on the top with same y
	double topx1 = myX * mySideLength * 3 + offset;
	double topx2 = topx1 + mySideLength;
	double topy = myY * (getPatchHeight() / 2);
	// 2 in the middle with same y
	double midx1 = topx1 - mySideLength / 2;
	double midx2 = topx2 + mySideLength / 2;
	double midy = topy + getPatchHeight() / 2;
	// 2 on bottom with same y
	double bottomx1 = topx1;
	double bottomx2 = topx2;
	double bottomy = topy + getPatchHeight();
	// create array of points, moving clockwise around the hexagon
	Double[] myPts = new Double[] { topx1, topy, topx2, topy, midx2, midy,
		bottomx2, bottomy, bottomx1, bottomy, midx1, midy };
	createPolyFromPoints(myPts);
	// what's our center
	setCenter(new Point((int) (topx1 + mySideLength / 2), (int) midy));
    }

}
