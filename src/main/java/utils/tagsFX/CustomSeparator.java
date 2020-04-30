package main.java.utils.tagsFX;

import javafx.geometry.Insets;
import javafx.scene.control.Separator;

public class CustomSeparator extends Separator {
    public CustomSeparator(int tamanoLargo, boolean visible) {
        this.setPrefWidth(tamanoLargo);
        this.setVisible(visible);
    }

    public CustomSeparator(int tamanoLargo, boolean visible, int space) {
        this.setPrefWidth(tamanoLargo);
        this.setVisible(visible);
        this.setPadding(new Insets(space));
    }

    public CustomSeparator(int tamanoLargo, boolean visible, int top, int right, int bottom, int left) {
        this.setPrefWidth(tamanoLargo);
        this.setVisible(visible);
        this.setPadding(new Insets(top, right, bottom, left));
    }
}
