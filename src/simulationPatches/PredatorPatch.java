package simulationPatches;

import controller.Grid;

/**
 *
 * @author Will Chang
 *
 */
public class PredatorPatch extends Patch {

    // TODO Update with graphical changes.
    // Do we need this?... - Will
    public PredatorPatch() {
	super();
    }

    public PredatorPatch(int x, int y, Grid m) {
	super(x, y, m);
    }

    @Override
    public void initialize(int x, int y, Grid m) {

	super.initialize(x, y, m);

    }
}
