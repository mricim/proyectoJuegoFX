package main.java.utils.tagsFX;

import javafx.scene.control.Separator;

public class CustomSeparator extends Separator {
    public CustomSeparator(int tamañoLargo, boolean visible) {
        this.setPrefWidth(tamañoLargo);
        this.setVisible(visible);
    }
}
