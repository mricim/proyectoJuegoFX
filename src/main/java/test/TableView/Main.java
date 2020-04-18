package main.java.test.TableView;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.File;
import java.lang.reflect.ParameterizedType;

public class Main extends Application {
    public static final String PATH = System.getProperty("user.dir") + "/src/main/java/test/TableView/";
    private static final String RUTEIMAGES = "/";


    public static void main(String[] args) {
        Image image;
        File file = new File(PATH + "error.png");
        image = new Image(file.toURI().toString());

        System.out.println(PATH + "error.png");
        Persona persona0 =new Persona(new DatosPersona(image, "nombre0", 901));
        Persona persona1 =new Persona(new DatosPersona(image, "nombre1", 123));
        Persona persona2 =new Persona(new DatosPersona(image, "nombre2", 234));
        Persona persona3 =new Persona(new DatosPersona(image, "nombre3", 345));
        Persona persona4 =new Persona(new DatosPersona(image, "nombre4", 456));
        Persona persona5 =new Persona(new DatosPersona(image, "nombre5", 567));
        Thread thread = new Thread(){
            public void run(){
                try {
                    Thread.sleep(30000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread Running --> PANTALLA INICIAL CONTROLLER: REMOVE Comercio");
                persona3.remove();
                persona4.remove();
                Persona persona6 =new Persona(new DatosPersona(image, "nombre6", 678));
                Persona persona7 =new Persona(new DatosPersona(image, "nombre7", 789));
                Persona persona8 =new Persona(new DatosPersona(image, "nombre8", 890));
            }
        };
        thread.start();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello World!");
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        VBox vBox = new VBox(btn, generateTable(primaryStage));

        StackPane root = new StackPane();
        root.getChildren().add(vBox);
        //primaryStage.setScene(new Scene(root, 300, 300));//TODO CON ESTE NO DA EL ERROR
        primaryStage.setScene(new Scene(root, 300, 800));//TODO CON ESTE SI
        primaryStage.show();
    }

    private static ScrollPane generateTable(Stage primaryStage) {
        //TABLA
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPannable(true);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        TableView<Persona> personaTableView = new TableView<>();
        //personaTableView.setPrefHeight(primaryStage.heightProperty().multiply(0.80));
        personaTableView.prefHeightProperty().bind(primaryStage.heightProperty().multiply(0.80));
        personaTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
//columna
        TableColumn<Persona, Persona> recursoOferta = new TableColumn("peronas");
        recursoOferta.setMinWidth(100);

        recursoOferta.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Persona, Persona>, ObservableValue<Persona>>() {
            @Override
            public ObservableValue<Persona> call(TableColumn.CellDataFeatures<Persona, Persona> features) {
                return new ReadOnlyObjectWrapper(features.getValue());
//                return new SimpleObjectProperty<Recursos>((Recursos) features.getValue().getQueSeOfrece());
                //return features.getValue();
            }
        });
        recursoOferta.setCellFactory(new Callback<TableColumn<Persona, Persona>, TableCell<Persona, Persona>>() {
            @Override
            public TableCell<Persona, Persona> call(TableColumn<Persona, Persona> param) {
                return new TableCell<Persona, Persona>() {
                    @Override
                    protected void updateItem(Persona item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            //setText(null);
                            //setStyle(null);
                        } else {
                            //recuperar el recurso
                            DatosPersona recurso = (DatosPersona) item.getDatosDePersona();
                            //create objects
                            ImageView imageview = new ImageView();
                            imageview.setFitHeight(50);
                            imageview.setFitWidth(50);
                            imageview.setImage(recurso.getImage());
                            //create and add HBOX
                            HBox hBox = new HBox(imageview, new Label(String.valueOf(recurso.getDni())));
                            hBox.setSpacing(10);
                            hBox.setAlignment(Pos.CENTER);
                            //SETTING ALL THE GRAPHICS COMPONENT FOR CELL
                            setGraphic(hBox);
                            System.out.println("Tamaño Columnas TableView: " + personaTableView.getColumns().size());
                            System.out.println("Tamaño Contenido TableView: " + personaTableView.getItems().size());
                            System.out.println("Tamaño lista de ParaTablas: " + Persona.data.size());
                        }
                    }
                };
            }
        });

        TableColumn<Persona, Integer> idCol = new TableColumn("Id");
        idCol.setMinWidth(100);
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

//FIN COLUMNAS
        //ParaTablaTableView.getColumns().addAll(recursoOferta, recursoDemanda, idCol, botonComprar);
        personaTableView.getColumns().addAll(recursoOferta, idCol);
        personaTableView.setItems(Persona.data);

//FIN TABLA
        scrollPane.setContent(personaTableView);
        return scrollPane;
    }
}
