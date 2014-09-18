package simulationObjects;

import java.util.ArrayList;
import javafx.scene.paint.Color;
/**
 * 
 * @author Will Chang
 *
 */
public class LifeCell extends Cell {

    public LifeCell()
    {
        super();
        setFill(Color.BLACK);
    }
    
    public LifeCell(int x, int y)
    {
        super();      
        myX = x;
        myY = y;
        setFill(Color.BLACK);

    }
    @Override
    public int getState () {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setState (int state) {
        // TODO Auto-generated method stub

    }

    @Override
    public Patch update (Patch currentPatch, ArrayList<Patch> neighbors) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void prepareToUpdate (Patch currentPatch, ArrayList<Patch> neighbors) {
        // TODO Auto-generated method stub

    }

   

}
