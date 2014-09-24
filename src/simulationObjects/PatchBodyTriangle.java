package simulationObjects;

import java.awt.Point;


public class PatchBodyTriangle extends PatchBody {

    public PatchBodyTriangle (int x, int y, double gridPixelHeight, double gridPixelWidth, int gridHeight, int gridWidth) {
        super(x, y);
        setPatchHeight((int)gridPixelHeight/gridHeight);
        setPatchWidth((int)gridPixelWidth/gridWidth);
        buildBody();
    }

    @Override
    public void buildBody () {
        double startX = getPatchWidth()*myX;
        double startY = getPatchHeight()*myY;
        boolean upsideDown = myX % 2 != myY % 2;
        Double[] myPts;
        Point center;
        if (upsideDown){
            double topCoord1 = startX-getPatchWidth()/2;
            double topCoord2 = startX+getPatchWidth()*3/2;
            double topY = startY;
            double bottomCoord = startX+(getPatchWidth()/2);
            double bottomY = startY+getPatchHeight();
            myPts = new Double[]{topCoord1,topY,topCoord2,topY,bottomCoord,bottomY};
            center = new Point((int)bottomCoord,(int)topY+getPatchHeight()/2);
        }
        else{
            double topCoord1 = startX+(getPatchWidth()/2);
            double topY = startY;
            double bottomCoord1 = startX-getPatchWidth()/2;
            double bottomCoord2 = startX+getPatchWidth()*3/2;
            double bottomY = startY+getPatchHeight();
            myPts = new Double[]{topCoord1,topY,bottomCoord1,bottomY,bottomCoord2,bottomY};
            center = new Point((int)topCoord1,(int)topY+getPatchHeight()/2);
        }
        createPolyFromPoints(myPts);
        setCenter(center);
    }
    
}
