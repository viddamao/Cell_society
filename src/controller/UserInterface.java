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
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
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
    public static final int GRID_WIDTH = 500;
    public static final int GRID_HEIGHT = 500;
    private static ResourceBundle ourMessages;
    private static final int PANEL_HEIGHT = 200;
    private MainController myMainController;
    private Grid myGrid;
    private Pane myRootPane;
    private Stage myStage;
    private SimulationChart myChart;

    public UserInterface (Stage s, MainController mainController) {
        ourMessages = ResourceBundle.getBundle("messages", Locale.US);
        myMainController = mainController;
        myStage = s;
        myStage.setTitle(ourMessages.getString("stage_title"));

        myRootPane = new Pane();
        Scene myScene = new Scene(myRootPane, GRID_WIDTH, GRID_HEIGHT
                                                          + PANEL_HEIGHT);
        myStage.setScene(myScene);
        myStage.setResizable(false);
        myStage.show();
        makeBottomPanel();
    }

    /**
     * create the bottom panel that the user interacts with all UI element's
     * coordinates are pulled from the resource file
     */
    private void makeBottomPanel () {
        // buttons

        myRootPane.getChildren().add(
                                     createButton(ourMessages.getString("start_button_title"),
                                                  intFromResource("start_button_x"),
                                                  intFromResource("start_button_y"),
                                                  new EventHandler<ActionEvent>() {
                                                      @Override
                                                      public void handle (ActionEvent event) {
                                                          myMainController.startSimulation();
                                                      }
                                                  }));
        myRootPane.getChildren().add(
                                     createButton(ourMessages.getString("stop_button_title"),
                                                  intFromResource("stop_button_x"),
                                                  intFromResource("stop_button_y"),
                                                  new EventHandler<ActionEvent>() {
                                                      @Override
                                                      public void handle (ActionEvent event) {
                                                          myMainController.stopSimulation();
                                                      }
                                                  }));
        myRootPane.getChildren().add(
                                     createButton(ourMessages.getString("step_button_title"),
                                                  intFromResource("step_button_x"),
                                                  intFromResource("step_button_y"),
                                                  new EventHandler<ActionEvent>() {
                                                      @Override
                                                      public void handle (ActionEvent event) {
                                                          myMainController.stepSimulation();
                                                      }
                                                  }));
        myRootPane.getChildren().add(
                                     createButton(ourMessages.getString("color_button_title"),
                                                  intFromResource("color_button_x"),
                                                  intFromResource("color_button_y"),
                                                  new EventHandler<ActionEvent>() {
                                                      @Override
                                                      public void handle (ActionEvent event) {
                                                          if (myGrid != null) {
                                                              ColorPicker colorPicker =
                                                                      new ColorPicker(myGrid);
                                                              colorPicker.showColorPicker();
                                                          }
                                                      }
                                                  }));
        myRootPane.getChildren().add(
                                     createButton(ourMessages.getString("file_button_title"),
                                                  intFromResource("file_button_x"),
                                                  intFromResource("file_button_y"),
                                                  new EventHandler<ActionEvent>() {
                                                      @Override
                                                      public void handle (ActionEvent event) {
                                                          // show file dialog
                                                          FileChooser fileChooser =
                                                                  new FileChooser();
                                                          fileChooser.setTitle(ourMessages
                                                                  .getString("file_dialog_title"));
                                                          final File XMLData = fileChooser
                                                                  .showOpenDialog(myStage);
                                                          if (XMLData != null) {
                                                              myMainController
                                                                      .initializeSimulationWithData(XMLData);
                                                          }
                                                      }
                                                  }));

        // radiobuttons for the edge type
        final ToggleGroup edgeGroup = new ToggleGroup();

        // TODO Either delete or implement Infinite Edge later
        /*
         * rootPane.getChildren().add(
         * createRadioButton(messages.getString("edge_radio_button3_title"),
         * intFromResource("edge_radio_button3_x"),
         * intFromResource("edge_radio_button3_y"), edgeGroup,
         * new EventHandler<ActionEvent>() {
         * 
         * @Override
         * public void handle(ActionEvent event) {
         * if (myGrid != null) {
         * myGrid.setEdgeRules(2);
         * }
         * }
         * }));
         */
        myRootPane.getChildren()
                .add(
                     createRadioButton(ourMessages.getString("edge_radio_button2_title"),
                                       intFromResource("edge_radio_button2_x"),
                                       intFromResource("edge_radio_button2_y"), edgeGroup,
                                       new EventHandler<ActionEvent>() {
                                           @Override
                                           public void handle (ActionEvent event) {
                                               if (myGrid != null) {
                                                   myGrid.changeRulesTo(new ToroidalEdgeRules(
                                                                                              myGrid.getGridWidth(),
                                                                                              myGrid.getGridHeight(),
                                                                                              myGrid));
                                               }
                                           }
                                       }));
        myRootPane.getChildren()
                .add(
                     createRadioButton(ourMessages.getString("edge_radio_button1_title"),
                                       intFromResource("edge_radio_button1_x"),
                                       intFromResource("edge_radio_button1_y"), edgeGroup,
                                       new EventHandler<ActionEvent>() {
                                           @Override
                                           public void handle (ActionEvent event) {
                                               if (myGrid != null) {
                                                   myGrid.changeRulesTo(new DefaultEdgeRules(
                                                                                             myGrid.getGridWidth(),
                                                                                             myGrid.getGridHeight(),
                                                                                             myGrid));
                                               }
                                           }
                                       }));

        // radiobuttons for the shape type
        final ToggleGroup shapeGroup = new ToggleGroup();
        myRootPane.getChildren()
                .add(
                     createRadioButton(ourMessages.getString("shape_radio_button3_title"),
                                       intFromResource("shape_radio_button3_x"),
                                       intFromResource("shape_radio_button3_y"), shapeGroup,
                                       new EventHandler<ActionEvent>() {
                                           @Override
                                           public void handle (ActionEvent event) {
                                               if (myGrid != null) {
                                                   myGrid.setPatchBody(2);
                                               }
                                           }
                                       }));
        myRootPane.getChildren()
                .add(
                     createRadioButton(ourMessages.getString("shape_radio_button2_title"),
                                       intFromResource("shape_radio_button2_x"),
                                       intFromResource("shape_radio_button2_y"), shapeGroup,
                                       new EventHandler<ActionEvent>() {
                                           @Override
                                           public void handle (ActionEvent event) {
                                               if (myGrid != null) {
                                                   myGrid.setPatchBody(1);
                                               }
                                           }
                                       }));
        myRootPane.getChildren()
                .add(
                     createRadioButton(ourMessages.getString("shape_radio_button1_title"),
                                       intFromResource("shape_radio_button1_x"),
                                       intFromResource("shape_radio_button1_y"), shapeGroup,
                                       new EventHandler<ActionEvent>() {
                                           @Override
                                           public void handle (ActionEvent event) {
                                               if (myGrid != null) {
                                                   myGrid.setPatchBody(0);
                                               }
                                           }
                                       }));

        // slider
        myRootPane.getChildren().add(
                                     createSlider(intFromResource("slider_min"),
                                                  intFromResource("slider_max"),
                                                  doubleFromResource("slider_start"),
                                                  intFromResource("slider_x"),
                                                  intFromResource("slider_y"),
                                                  new ChangeListener<Number>() {
                                                      @Override
                                                      public void changed (
                                                                           ObservableValue<? extends Number> ov,
                                                                           Number old_val,
                                                                           Number new_val) {
                                                          myMainController
                                                                  .setSimulationSpeed(
                                                                                      (double) new_val,
                                                                                      true);
                                                      }
                                                  }));
    }

    /**
     * returns an integer value from the resource file given a key
     *
     * @param s
     *        key to find in the resource file
     * @return integer from resource file
     */
    private Integer intFromResource (String s) {
        return (int) doubleFromResource(s);
    }

    /**
     * returns a double value from the resource file given a key
     *
     * @param s
     *        key to find in the resource file
     * @return double from resource file
     */
    private double doubleFromResource (String s) {
        return Double.parseDouble(ourMessages.getString(s));
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
     * create a radiobutton
     *
     * @param title
     *        title of the radiobutton
     * @param posX
     *        x position of radiobutton
     * @param posY
     *        y position of radiobutton
     * @param handler
     *        action handler
     * @return the radiobutton
     */
    private RadioButton createRadioButton (String title, int posX, int posY,
                                           ToggleGroup group, EventHandler<ActionEvent> handler) {
        RadioButton radioButton = new RadioButton(title);
        radioButton.setToggleGroup(group);
        radioButton.setSelected(true);
        radioButton.setLayoutX(posX);
        radioButton.setLayoutY(posY);
        radioButton.setOnAction(handler);
        return radioButton;
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

    /**
     * add a node to the pane
     *
     * @param n
     *        node to add
     */
    public void addNode (Node n) {
        myRootPane.getChildren().add(n);
    }

    public void setChart (SimulationChart chart) {
        myChart = chart;
        myRootPane.getChildren().add(myChart);
    }

    /**
     * remove a node from the pane
     *
     * @param n
     *        node to add
     */
    public void removeNode (Node n) {
        myRootPane.getChildren().remove(n);
    }

    /**
     * @return the rootPane for adding children
     */
    public Pane getRootPane () {
        return myRootPane;
    }

    public void reset () {
        myRootPane.getChildren().clear();
        makeBottomPanel();
    }

    public void setGrid (Grid grid) {
        myGrid = grid;
    }

}
