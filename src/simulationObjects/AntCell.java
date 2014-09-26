package simulationObjects;

/**
 * @author Will Chang
 */
import java.util.ArrayList;
import javafx.scene.paint.Color;

public class AntCell extends Cell {

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
    public void update (Patch currentPatch, ArrayList<Patch> neighbors) {
        forage();

    }

    private void forage () {
        if(hasFoodItem())
        {
            returnToNest();
        }
        else
        {
            findFoodSource();
        }
    }

    private void findFoodSource () {
    
        //Null if it can't move because neighbor is full.
        if(isAtNest())
        {
         //set location to neighbor with max food
         //hormones
        }
        ArrayList<Patch> neighbors = null;
        //forward locations
        Patch destination = selectLocation(neighbors);
        if(destination == null)
        {
            //Other neighbor locations
            destination = selectLocation(neighbors);
        }
        else
        {
            dropHomePheromones();
            //setOrientationHomeward
            Patch homeward = null;
            setOrientation(homeward);
            moveTo(homeward);
            if(isAtFoodSource())
            {
                pickUpFoodItem();
                boolean hasFoodItem = true;
            }
        }
    }
    
    private void pickUpFoodItem () {
        // TODO Auto-generated method stub
        
    }

    private void dropHomePheromones () {
        if(isAtNest())
        {
            topOffPheromones();
        }
        else
        {
            //maxpheromones of neighbors
            int max = 0;
            //desired level of pheromones
            int desired = max -2;
            int pheromonesAtCurrent = 0;
            int difference = desired - pheromonesAtCurrent;
            if(difference >0)
            {
                //deposit D home pheromones at current location
            }
        }
        
    }

    private void topOffPheromones () {
        // TODO Auto-generated method stub
        
    }

    private Patch selectLocation (ArrayList<Patch> neighbors) {
        //remove obstacle neighbors
        neighbors = processNeighbors(neighbors);
        //removeFullNeighbors
        neighbors = processFullNeighbors(neighbors);
        //empty?
        if(neighbors ==null)
        {
            return null;
        }
        else
        {
            //each former neighbor location has weight
            //(K +FoodPheromonesAtLocation)^N
            selectLocation(neighbors);
        }
        return null;
    }

    private ArrayList<Patch> processFullNeighbors (ArrayList<Patch> neighbors) {
        // TODO Auto-generated method stub
        return null;
    }

    private ArrayList<Patch> processNeighbors (ArrayList<Patch> neighbors) {
        // TODO Auto-generated method stub
        return null;
    }

    private void returnToNest () {
        //IF it is a food source...
        if(isAtFoodSource())
        {
            //Shift direction to patch with max home pheromones
            shiftOrientation(myPatch.getState());
        }
        //set equal to patch in front of Patch
        //May need to set directions for each of the neighbors...
        Patch destination = new Patch();
        if(destination == null)
        {
            destination = findMaxPheromoneNeighbor();
        }
        else
        {
            dropFoodPheromones();  
            setOrientation(destination);
            this.moveTo(destination);
            //Check for nest
            if(isAtNest())
            {
                dropFoodItem();
                //hasfoodItem = false
            }
        }
        
    }

    private boolean isAtNest () {
        // TODO Auto-generated method stub
        return false;
    }

    private boolean isAtFoodSource () {
        // TODO Auto-generated method stub
        return false;
    }

    private void dropFoodItem () {
        // TODO Auto-generated method stub
        
    }

    private void moveTo (Patch destination) {
        // TODO Auto-generated method stub
        
    }

    private void setOrientation (Patch destination) {
        // TODO Auto-generated method stub
        
    }

    private void dropFoodPheromones () {
        if(isAtFoodSource())
        {
            topOffPheromones();
        }
        else
        {
            //maxpheromones of neighbors
            int max = 0;
            //desired level of pheromones
            int desired = max -2;
            int pheromonesAtCurrent = 0;
            int difference = desired - pheromonesAtCurrent;
            if(difference >0)
            {
                //deposit D home pheromones at current location
            }
        }
        
        
    }

    private Patch findMaxPheromoneNeighbor () {
        // TODO Auto-generated method stub
        return null;
    }

    private void shiftOrientation (int state) {
        // TODO Auto-generated method stub
        
    }

   

    private boolean hasFoodItem () {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void prepareToUpdate (Patch currentPatch, ArrayList<Patch> neighbors) {
        myPatch = currentPatch;
        
    }

    @Override
    public int getNextState () {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public ArrayList<String> getStateTypes () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ArrayList<Color> getInitialColors () {
        // TODO Auto-generated method stub
        return null;
    }

}
