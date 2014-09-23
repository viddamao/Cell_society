package simulationObjects;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class PatchBody extends Polygon {
    
    private int myPatchHeight;
    private int myPatchWidth;
    private int startX;
    private int startY;

    public PatchBody(int x, int y, int patchHeight, int patchWidth) {
        super();
        myPatchHeight = patchHeight;
        myPatchWidth = patchWidth;
        startX = patchWidth*x;
        startY = patchHeight*y;
        double x1 = startX;
        double y1 = startY;
        double x2 = startX + patchWidth;
        double y2 = startY;
        double x3 = startX + patchWidth;
        double y3 = startY + patchHeight;
        double x4 = startX;
        double y4 = startY + patchHeight;
        Double[] myPts = new Double[]{x1,y1,x2,y2,x3,y3,x4,y4};
        getPoints().addAll(myPts);
        setFill(new Color(0f,0f,0f,.0f));
        setStrokeWidth(.5);
        setStroke(Color.BLACK);
    }
    
    public int getHeight(){
        return myPatchHeight;
    }
    
    public int getWidth(){
        return myPatchWidth;
    }
    
    public int getStartX(){
        return startX;
    }
    
    public int getStartY(){
        return startY;
    }

}
