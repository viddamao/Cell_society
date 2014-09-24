package simulationObjects;

import controller.GridInfo;
import javafx.scene.shape.Polygon;

public abstract class PatchBody extends Polygon {
    
    protected GridInfo infoSheet = new GridInfo();

    private int myPatchHeight;
    private int myPatchWidth;
    private int startX;
    private int startY;
    public int myX;
    public int myY;

    public PatchBody(int x, int y, int patchHeight, int patchWidth) {
        super();
        myPatchHeight = patchHeight;
        myPatchWidth = patchWidth;
        startX = patchWidth*x;
        startY = patchHeight*y;
        myX = x;
        myY = y;
        buildBody();
    }
    
    public abstract void buildBody();
    
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
