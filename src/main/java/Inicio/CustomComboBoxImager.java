package main.java.Inicio;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.java.Main;

import java.io.File;
import java.util.HashMap;

public class CustomComboBoxImager extends ListCell<String> {

    Label label;
    static HashMap<String, Image> pictures = new HashMap<>();

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (item == null || empty) {
            setItem(null);
            setGraphic(null);
        } else {
            setText(item);
            ImageView image = getImageView(item);
            label = new Label("", image);
            setGraphic(label);
        }
    }

    private static ImageView getImageView(String imageName) {
        ImageView imageView;
        switch (imageName) {
            case "Castellano":
                imageName ="es";
                break;
            case "English":
                imageName ="en";
                break;
            case "Català":
                imageName ="cat";
                break;
            default:
                imageName = null;
                break;

        }
        File file = new File(Main.RUTEINTERNAL + "resources/images/" + "flags/" + imageName + ".png");
        if (!file.exists()) {
            System.err.println("FALLO" + imageName);
        }
        imageView = new ImageView(new Image(file.toURI().toString()));
        return imageView;
    }
}