package main.resources;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class MyController implements Initializable {

    @FXML private Label lblTextByController;
    private ResourceBundle bundle;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bundle = resources;
        lblTextByController.setText(bundle.getString("key1"));
    }
}
