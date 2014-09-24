package simulationObjects;

import javafx.scene.paint.Color;

public class PatchBodyTriangle extends PatchBody {

    public PatchBodyTriangle (int x, int y, int patchHeight, int patchWidth) {
        super(x, y, patchHeight, patchWidth);
    }

    @Override
    public void buildBody () {
        boolean upsideDown = myX % 2 != myY % 2;
        Double[] myPts;
        if (upsideDown){
            double topCoord1 = getStartX()-getWidth()/2;
            double topCoord2 = getStartX()+getWidth()*3/2;
            double topY = getStartY();
            double bottomCoord = getStartX()+(getWidth()/2);
            double bottomY = getStartY()+getHeight();
            myPts = new Double[]{topCoord1,topY,topCoord2,topY,bottomCoord,bottomY};
        }
        else{
            double topCoord1 = getStartX()+(getWidth()/2);
            double topY = getStartY();
            double bottomCoord1 = getStartX()-getWidth()/2;
            double bottomCoord2 = getStartX()+getWidth()*3/2;
            double bottomY = getStartY()+getHeight();
            myPts = new Double[]{topCoord1,topY,bottomCoord1,bottomY,bottomCoord2,bottomY};
        }
        getPoints().addAll(myPts);
        setFill(infoSheet.getColor("BACKGROUND"));
        setStrokeWidth(.5);
        setStroke(Color.BLACK);
    }
    
}
