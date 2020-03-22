package main.java.utils.tagsFXML;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.java.Main;
import main.java.utils.CallImages;

import java.io.File;
import java.util.ArrayList;

public class CustomComboBoxImager extends ListCell<String> {
    public static final String SEARCHER = "$##$";
    Label label;

    public static ComboBox<String> createCombox(ArrayList<String> stringArrayList) {
        ComboBox<String> comboBox2 = new ComboBox<>();
        comboBox2.getItems().addAll(stringArrayList);
        //Set the cellFactory property
        comboBox2.setCellFactory(listview -> new CustomComboBoxImager());
        // Set the buttonCell property
        comboBox2.setButtonCell(new CustomComboBoxImager());
        return comboBox2;
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (item == null || empty) {
            setItem(null);
            setGraphic(null);
        } else {
            String text = item.substring(0, item.indexOf(SEARCHER));
            String rute = item.substring(4 + item.indexOf(SEARCHER));
            setText(text);
            ImageView image = getImageView(rute);
            label = new Label("", image);
            setGraphic(label);
        }
    }

    private static ImageView getImageView(String imagePathName) {
        ImageView imageView;
        String rute=imagePathName.substring(0,1+imagePathName.lastIndexOf("/"));
        String name=imagePathName.substring(1+imagePathName.lastIndexOf("/"));

        imageView = new ImageView(CallImages.getImageNotSaveNOTHEMA(rute,name));
        /*
        File file = new File(Main.RUTEINTERNAL + "resources/images/" + imagePathName + ".png");
        if (!file.exists()) {
            System.err.println("FALLO" + imagePathName);
        }
        imageView = new ImageView(new Image(file.toURI().toString()));

         */
        return imageView;
    }
}