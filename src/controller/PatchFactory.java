package controller;

import java.util.Locale;
import java.util.ResourceBundle;

import simulationObjects.Patch;

/**
 * create a patch on the grid
 *
 * @author Davis
 *
 */

public class PatchFactory {

    private GridInfo myInfoSheet;
    private static ResourceBundle ourProperties;

    public PatchFactory(GridInfo infoSheet) {
	myInfoSheet = infoSheet;
	ourProperties = ResourceBundle.getBundle("messages", Locale.US);
    }

    /**
     * create the patch
     *
     * @param myGrid
     *            the grid to add to
     * @param i
     *            the x coordinate
     * @param j
     *            the y coordinate
     * @return the created patch
     */
    public Patch createPatch(Grid myGrid, int i, int j) {
	Patch patch = null;
	if (myInfoSheet.getPatchType().equals("Default")) {
	    patch = new Patch(i, j, myGrid);
	} else {
	    try {
		String patchPathAndName = ourProperties
			.getString("cell_bundle")
			+ "."
			+ myInfoSheet.getPatchType();
		Class<?> patchClass = Class.forName(patchPathAndName);
		patch = (Patch) patchClass.newInstance();
		patch.initialize(i, j, myGrid);
	    } catch (ClassNotFoundException e) {
		System.out.println(ourProperties
			.getString("class_not_found_error"));
	    } catch (InstantiationException e) {
		System.out.println(ourProperties
			.getString("instantiation_error"));
	    } catch (IllegalAccessException e) {
		System.out.println(ourProperties
			.getString("illegal_access_error"));
	    }
	}
	return patch;
    }

}
