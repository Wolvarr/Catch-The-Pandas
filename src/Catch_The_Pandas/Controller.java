package Catch_The_Pandas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Controller {
    @FXML
    public Menu exitMenu;
    @FXML
    public MenuItem exitMenuItem;

    @FXML
    public void exit(ActionEvent actionEvent) {
       System.exit(0);
    }
}
