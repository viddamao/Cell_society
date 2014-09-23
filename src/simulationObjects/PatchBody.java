package simulationObjects;

import javafx.scene.shape.Polygon;

public abstract class PatchBody extends Polygon {
    
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
