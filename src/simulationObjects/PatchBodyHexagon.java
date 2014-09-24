package simulationObjects;

import javafx.scene.paint.Color;

public class PatchBodyHexagon extends PatchBody {
    
    private double mySideLength;

    public PatchBodyHexagon (int x, int y, int hexWidth) {
        super(x, y, 0, 0);
        mySideLength = (double)hexWidth/2;
        buildBody();
    }

    @Override
    public void buildBody () {
        double myHeight = mySideLength * Math.sqrt(3.0);
        double offset = myY % 2 == 0 ? mySideLength*1.5 : 0;
        //6 coordinates
        double topx1 = myX*mySideLength*3 + offset;
        double topx2 = topx1 + mySideLength;
        double topy = myY*(myHeight/2);
        double midx1 = topx1 - mySideLength/2;
        double midx2 = topx2 + mySideLength/2;
        double midy = topy + myHeight/2;
        double bottomx1 = topx1;
        double bottomx2 = topx2;
        double bottomy = topy + myHeight;
        Double[] myPts = new Double[]{topx1,topy,topx2,topy,midx2,midy,bottomx2,bottomy,bottomx1,bottomy,midx1,midy};
        getPoints().addAll(myPts);
        setFill(new Color(0f,0f,0f,.0f));
        setStrokeWidth(.5);
        setStroke(Color.BLACK);
    }
    
}
