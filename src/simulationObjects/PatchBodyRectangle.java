package simulationObjects;


public class PatchBodyRectangle extends PatchBody {

    public PatchBodyRectangle (int x, int y, double gridPixelHeight, double gridPixelWidth, int gridHeight, int gridWidth) {
        super(x, y);
        setPatchHeight((int)gridPixelHeight/gridHeight);
        setPatchWidth((int)gridPixelWidth/gridWidth);
        buildBody();
    }

    @Override
    public void buildBody() {
        double startX = getPatchWidth()*myX;
        double startY = getPatchHeight()*myY;
        
        double x1 = startX;
        double y1 = startY;
        double x2 = startX + getPatchWidth();
        double y2 = startY;
        double x3 = startX + getPatchWidth();
        double y3 = startY + getPatchHeight();
        double x4 = startX;
        double y4 = startY + getPatchHeight();
        Double[] myPts = new Double[]{x1,y1,x2,y2,x3,y3,x4,y4};
        createPolyFromPoints(myPts);
    }

}
