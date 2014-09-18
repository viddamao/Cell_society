package controller;

import java.io.File;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


/**
 * The UI of the application, handles buttons and handlers as well as the scene
 * 
 * @author Davis
 *
 */

public class UserInterface {
    private MainController myMainController;
    private Pane rootPane;
    private Stage myStage;
    private static ResourceBundle messages;
    public final int GRID_WIDTH = 500;
    public final int GRID_HEIGHT = 500;
    private final int PANEL_HEIGHT = 40;

    public UserInterface (Stage s, MainController mainController) {
        messages = ResourceBundle.getBundle("messages", Locale.US);
        myMainController = mainController;
        Stage myStage = s;
        myStage.setTitle(messages.getString("stage_title"));

        rootPane = new Pane();
        Scene myScene = new Scene(rootPane, GRID_WIDTH, GRID_HEIGHT
                                                        + PANEL_HEIGHT);
        myStage.setScene(myScene);
        myStage.show();
        makeBottomPanel();
    }

    /**
     * create the bottom panel that the user interacts with
     */
    private void makeBottomPanel () {
        // buttons
        rootPane.getChildren().add(
                                   createButton(messages.getString("start_button_title"),
                                                intFromResource("start_button_x"),
                                                intFromResource("start_button_y"),
                                                new EventHandler<ActionEvent>() {
                                                    @Override
                                                    public void handle (ActionEvent event) {
                                                        myMainController.startSimulation();
                                                    }
                                                }));
        rootPane.getChildren().add(
                                   createButton(messages.getString("stop_button_title"),
                                                intFromResource("stop_button_x"),
                                                intFromResource("stop_button_y"),
                                                new EventHandler<ActionEvent>() {
                                                    @Override
                                                    public void handle (ActionEvent event) {
                                                        myMainController.stopSimulation();
                                                    }
                                                }));
        rootPane.getChildren().add(
                                   createButton(messages.getString("step_button_title"),
                                                intFromResource("step_button_x"),
                                                intFromResource("step_button_y"),
                                                new EventHandler<ActionEvent>() {
                                                    @Override
                                                    public void handle (ActionEvent event) {
                                                        myMainController.stepSimulation();
                                                    }
                                                }));
        rootPane.getChildren().add(
                                   createButton(messages.getString("file_button_title"),
                                                intFromResource("file_button_x"),
                                                intFromResource("file_button_y"),
                                                new EventHandler<ActionEvent>() {
                                                    @Override
                                                    public void handle (ActionEvent event) {
                                                        // show file dialog
                                                        FileChooser fileChooser = new FileChooser();
                                                        fileChooser.setTitle(messages
                                                                .getString("file_dialog_title"));
                                                        final File XMLData = fileChooser
                                                                .showOpenDialog(myStage);
                                                        if (XMLData!=null)
                                                        myMainController
                                                                .initializeSimulationWithData(XMLData);
                                                    }
                                                }));
        // slider
        rootPane.getChildren().add(
                                   createSlider(intFromResource("slider_min"),
                                                intFromResource("slider_max"),
                                                doubleFromResource("slider_start"),
                                                intFromResource("slider_x"),
                                                intFromResource("slider_y"),
                                                new ChangeListener<Number>() {
                                                    public void changed (
                                                                         ObservableValue<? extends Number> ov,
                                                                         Number old_val,
                                                                         Number new_val) {
                                                        myMainController
                                                                .setSimulationSpeed((double) new_val);
                                                    }
                                                }));
    }

    private Integer intFromResource (String s) {
        return (int) doubleFromResource(s);
    }

    private double doubleFromResource (String s) {
        return Double.parseDouble(messages.getString(s));
    }

    /**
     * create a button
     * 
     * @param title
     *        title of the button
     * @param posX
     *        x position of button
     * @param posY
     *        y position of button
     * @param handler
     *        action handler
     * @return the button
     */
    private Button createButton (String title, int posX, int posY,
                                 EventHandler<ActionEvent> handler) {
        Button button = new Button(title);
        button.setLayoutX(posX);
        button.setLayoutY(posY);
        button.setOnAction(handler);
        return button;
    }

    /**
     * create a slider
     * 
     * @param start
     *        minimum value of the slider
     * @param stop
     *        maximum value of the slider
     * @param startVal
     *        initial value of the slider
     * @param posX
     *        x position of the slider
     * @param posY
     *        y position of the slider
     * @param listener
     *        listener for when the slider value changes
     * @return the slider
     */
    private Slider createSlider (int start, int stop, double startVal, int posX,
                                 int posY, ChangeListener<Number> listener) {
        Slider slider = new Slider(start, stop, startVal);
        slider.setLayoutX(posX);
        slider.setLayoutY(posY);
        slider.valueProperty().addListener(listener);
        return slider;
    }

    public void addNode (Node n) {
        rootPane.getChildren().add(n);
    }

    public void removeNode (Node n) {
        rootPane.getChildren().remove(n);
    }

    /**
     * @return the rootPane for adding children
     */
    public Pane getRootPane () {
        return rootPane;
    }

}
