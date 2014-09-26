package controller;

import java.util.Locale;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.JOptionPane;

/**
 *
 * Allows user to set colors for simulation cells
 *
 * @author Viddamao
 *
 */
public class ColorPicker extends javafx.scene.control.ColorPicker {
    private GridInfo infoSheet = new GridInfo();
    private Grid grid;
    
    public ColorPicker(Grid g)
    {
        grid = g;
    }

    public void showColorPicker() {
	Stage myStage = new Stage();
	myStage.setResizable(false);
	myStage.setTitle("Choose colors for the simulation!");
	GridPane gridpane = new GridPane();
	gridpane.getColumnConstraints().add(new ColumnConstraints(80));
	gridpane.getColumnConstraints().add(new ColumnConstraints(100));
	gridpane.getRowConstraints().add(new RowConstraints(35));
	gridpane.getRowConstraints().add(new RowConstraints(55));
	gridpane.getRowConstraints().add(new RowConstraints(40));

	if (infoSheet.getStateTypes() == null) {
	    sendPickFileAlert();
	    return;
	}
	Scene scene = new Scene(gridpane, 350, 120);
	for (int i = 0; i < infoSheet.getStateTypes().size(); i++) {
	    gridpane = buildColorPickerMenu(i, gridpane);

	}

	myStage.setScene(scene);
	myStage.show();
    }

    private void sendPickFileAlert() {
	ResourceBundle messages = ResourceBundle.getBundle("messages",
		Locale.US);
	JOptionPane.showMessageDialog(null,
		messages.getString("missing_sim_type"));
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private GridPane buildColorPickerMenu(int index, GridPane gridpane) {
	final ColorPicker colorPicker = new ColorPicker(grid);
	final Text text = new Text(infoSheet.getStateTypes().get(index));
	text.setFont(Font.font("Verdana", 20));
	String stateType = text.getText();
	Color myColor = infoSheet.getColor(stateType);
	colorPicker.setValue(myColor);
	text.setFill(myColor);

	colorPicker.setOnAction(new EventHandler() {

	    @Override
	    public void handle(Event t) {

		Color myColor = colorPicker.getValue();
		text.setFill(myColor);
		infoSheet.setColor(stateType, myColor);

		if (text.getText() == "BACKGROUND") {
		    grid.updateBackgroundColor(myColor);
		}
	    }
	});

	gridpane.add(colorPicker, 1, index);
	gridpane.add(text, 2, index);

	return gridpane;

    }
}