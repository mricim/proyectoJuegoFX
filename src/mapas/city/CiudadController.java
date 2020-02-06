package mapas.ciudad;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class CiudadController extends PrimaryStageControlador implements Initializable {
    @FXML
    ToolBar toolbar;
    @FXML
    GridPane gridPaneMap;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*
        final Image image = new Image(getClass().getResource("cross_red.jpg").toExternalForm(), 20, 20, true, true);
        MenuButton menuButton = new MenuButton("Don't touch this");
        menuButton.setGraphic(new ImageView(image));
        menuButton.getItems().addAll(new MenuItem("Really"), new MenuItem("Do not"));
        toolbar.getItems().addAll(menuButton);
         */
        Image image = new Image(getClass().getResource("../resourses.icons/example_casa.png").toExternalForm(), 100, 100, false, true);
        ImageView imageView = new ImageView(image);
        gridPaneMap.add(imageView, 7, 12);

        Image image2 = new Image(getClass().getResource("../resourses.icons/example_academy.png").toExternalForm(), 100, 100, false, true);
        ImageView imageView2 = new ImageView(image2);
        gridPaneMap.add(imageView2, 12, 7);

        Image image3 = new Image(getClass().getResource("../resourses.icons/example_empty.png").toExternalForm(), 100, 100, false, true);
        ImageView imageView3 = new ImageView(image3);
        imageView3.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            System.out.println("Imagen empty clicada");
            System.out.println("Segunda Funcion");
        });
        gridPaneMap.add(imageView3, 9, 5);
    }

    @FXML
    public void botton2(ActionEvent actionEvent) {
        cambiarNombreStage("El nombre que queramos");
        System.out.println("COSASA");
    }


}
