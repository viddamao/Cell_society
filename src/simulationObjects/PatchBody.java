package simulationObjects;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public abstract class PatchBody extends Polygon {
    
    private int myPatchHeight;
    private int myPatchWidth;
    public int myX;
    public int myY;

    public PatchBody(int x, int y) {
        super();
        myX = x;
        myY = y;
    }
    
    public abstract void buildBody();
    
    public void createPolyFromPoints(Double[] pts){
        getPoints().addAll(pts);
        setFill(new Color(0f,0f,0f,.0f));
        setStrokeWidth(.5);
        setStroke(Color.BLACK);
    }
    
    public void setPatchHeight(int h){
        myPatchHeight = h;
    }
    
    public void setPatchWidth(int w){
        myPatchWidth = w;
    }
    
    public int getPatchHeight(){
        return myPatchHeight;
    }
    
    public int getPatchWidth(){
        return myPatchWidth;
    }

}
