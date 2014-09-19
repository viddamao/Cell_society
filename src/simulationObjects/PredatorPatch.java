package simulationObjects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import controller.GridManager;
/**
 * 
 * @author Will Chang
 *
 */
public class PredatorPatch extends Patch {
    
    public PredatorPatch()
    {
        super();
    }
    public PredatorPatch (int x, int y, GridManager m) {
        super(x, y, m);
        /*  image = new Image(getClass().getResourceAsStream("/src/Images/WaTorPatch.png));
        myView = new ImageView();
        myView.setImage(image);
        getChildren().add(myView);*/
    }
    
    @Override
    public void initialize(int x, int y, GridManager m)
    {
        
        super.initialize(x, y, m);
        /*image = new Image(getClass().getResourceAsStream("/Images/WaTorPatch.png"));
        myView = new ImageView();
        myView.setImage(image);
        getChildren().add(myView);*/
        
    }
}
