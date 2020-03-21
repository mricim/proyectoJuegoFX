package main.resources;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class BundleDemo extends Application {

    private Stage stage;

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        Button btnEN = new Button();
        btnEN.setText("English");
        btnEN.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                loadView(new Locale("en", "EN"));
            }
        });

        Button btnKG = new Button();
        btnKG.setText("Kyrgyz");
        btnKG.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                loadView(new Locale("kg", "KG"));
            }
        });

        VBox root = new VBox(20);
        root.getChildren().add(HBoxBuilder.create().spacing(10).style("-fx-background-color: gray").padding(new Insets(5)).children(btnEN, btnKG).build());
        root.getChildren().add(new StackPane());
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }

    private void loadView(Locale locale) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setResources(ResourceBundle.getBundle("main.resources.traductions.UIResources", locale));
            Pane pane = (BorderPane) fxmlLoader.load(this.getClass().getResource("MyView.fxml").openStream());
            // replace the content
            StackPane content = (StackPane) ((VBox) stage.getScene().getRoot()).getChildren().get(1);
            content.getChildren().clear();
            content.getChildren().add(pane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
