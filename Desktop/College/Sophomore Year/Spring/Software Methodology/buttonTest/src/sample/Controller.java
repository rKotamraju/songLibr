package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.awt.event.ActionEvent;

public class Controller {
    @FXML
    public Button button1;

    @FXML
    private void buttonCLicked(ActionEvent e){
        System.out.println("Hello");
    }
}
