package simulationObjects;

import org.junit.Assert;
import org.junit.Test;

public class PatchBodyTest {

    @Test
    public void testRectangleCreation () {
        PatchBodyRectangle patch = new PatchBodyRectangle(0,0,50,50,5,5);
        //make sure our points match the basic rectangle shape
        Assert.assertEquals("Rectangle shape should have the same x coordinate at point 2 and 3", patch.getPoints().get(2), patch.getPoints().get(4));
    }
    
    @Test
    public void testTriangleCreation () {
        PatchBodyTriangle patch = new PatchBodyTriangle(0,0,50,50,5,5);
        //make sure our points match the basic triangle shape
        Assert.assertEquals("Triangle shape should have the same y coordinate at point 2 and 3", patch.getPoints().get(3), patch.getPoints().get(5));
    }
    
    @Test
    public void testHexagonCreation () {
        PatchBodyHexagon patch = new PatchBodyHexagon(0,0,50,50,5,5);
        //make sure our points match the basic triangle shape
        Assert.assertEquals("Triangle shape should have the same x coordinate at point 2 and 4", patch.getPoints().get(2), patch.getPoints().get(6));
    }

}
