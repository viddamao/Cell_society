package simulationObjects;

import javafx.scene.paint.Color;

public class PatchBodyRectangle extends PatchBody {

    public PatchBodyRectangle (int x, int y, int patchHeight, int patchWidth) {
        super(x, y, patchHeight, patchWidth);
    }

    @Override
    public void buildBody() {
        double x1 = getStartX();
        double y1 = getStartY();
        double x2 = getStartX() + getWidth();
        double y2 = getStartY();
        double x3 = getStartX() + getWidth();
        double y3 = getStartY() + getHeight();
        double x4 = getStartX();
        double y4 = getStartY() + getHeight();
        Double[] myPts = new Double[]{x1,y1,x2,y2,x3,y3,x4,y4};
        getPoints().addAll(myPts);
        setFill(infoSheet.getColor("BACKGROUND"));
        setStrokeWidth(.5);
        setStroke(Color.BLACK);
    }

}
