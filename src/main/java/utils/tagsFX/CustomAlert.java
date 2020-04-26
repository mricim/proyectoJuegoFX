package main.java.utils.tagsFX;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static main.java.utils.PrimaryStageControler.getStagePrimaryStageController;

public class CustomAlert extends Alert {
    public CustomAlert(AlertType alertType) {
        super(alertType);
        constructor(this);
    }

    public CustomAlert(AlertType alertType, String contentText, ButtonType... buttons) {
        super(alertType, contentText, buttons);
        constructor(this);
    }
    private void constructor(Alert alert){
        Stage stage = (Stage) getStagePrimaryStageController().getScene().getWindow();
        stage.setAlwaysOnTop(true);
        stage.toFront();
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(stage);
    }
}
