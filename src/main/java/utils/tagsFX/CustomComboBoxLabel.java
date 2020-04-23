package main.java.utils.tagsFX;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class CustomComboBoxLabel extends ListCell<TextImage> {
    final ImageView graphicNode = new ImageView();

    public static ComboBox<TextImage> createCombox(ObservableList<TextImage> recursosObservableList) {
        ComboBox<TextImage> combo = new ComboBox<>();
        combo.getItems().addAll(recursosObservableList);
        combo.setButtonCell(new CustomComboBoxLabel());
        combo.setCellFactory(listView -> new CustomComboBoxLabel());
        //combo.getSelectionModel().select(1);
        combo.getSelectionModel().selectFirst();
        return combo;
    }

    @Override
    protected void updateItem(TextImage item, boolean empty) {
        super.updateItem(item, empty);

        if (item == null || empty) {
            setText(null);
            setGraphic(null);
            graphicNode.setImage(null);
        } else {
            setText(item.getString());
            setTextFill(Color.TRANSPARENT);
            graphicNode.setImage(item.getImage());
            graphicNode.setFitWidth(item.getWidth());
            graphicNode.setFitHeight(item.getHeight());
            setGraphic(graphicNode);
        }
    }
}