package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;


public class UserInterfaceButton extends Button {
    public UserInterfaceButton (String title, int posX, int posY) {
        super(title);
        setLayoutX(posX);
        setLayoutY(posY);
    }
}
