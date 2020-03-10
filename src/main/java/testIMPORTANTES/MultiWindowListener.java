package main.java.testIMPORTANTES;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MultiWindowListener extends Application
{
    // Create the counter
    protected static int counter = 0;
    // Create the Stage
    protected Stage lastOpenStage;
    // Create the TextArea
    protected TextArea area = new TextArea();

    public static void main(String[] args)
    {
        Application.launch(args);
    }

    public void start(Stage stage) {
        // Create the VBox
        VBox root = new VBox();
        // Create the Button
        Button openButton = new Button("Open1");
        // Add the EventHandler to the button
        openButton.setOnAction(new EventHandler <ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
                open(++counter);
            }
        });

        // Add the children to the VBox
        root.getChildren().addAll(openButton,area);

        // Create the Scene
        Scene scene = new Scene(root, 400, 400);
        // Add the Scene to the Stage
        stage.setScene(scene);
        // Set the Title of the Stage
        stage.setTitle("The Primary Stage");
        // Display the Stage
        stage.show();
        // Set the Stage as the last opened Stage
        this.lastOpenStage = stage;
    }

    private void open(final int stageNumber)
    {
        // Create a new Stage
        Stage stage = new Stage();
        // Set the Title of the Stage
        stage.setTitle("#" + stageNumber);
        // Create a Say-Hello-Button
        Button sayHelloButton = new Button("Say Hello");
        // Add the EventHandler to the Button
        sayHelloButton.setOnAction(new EventHandler <ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
                area.appendText("Hello from #" + stageNumber + "\n");
            }
        });

        // Create an Open Button
        Button openButton = new Button("Open");
        // Add the EventHandler to the Button
        openButton.setOnAction(new EventHandler <ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
                open(++counter);
            }
        });

        // Create the VBox
        VBox root = new VBox();
        // Add the children to the VBox
        root.getChildren().addAll(sayHelloButton, openButton);

        // Create the Scene
        Scene scene = new Scene(root, 200, 200);
        // Add the Scene to the Stage
        stage.setScene(scene);
        // Set the Position of the Stage
        stage.setX(this.lastOpenStage.getX() + 50);
        stage.setY(this.lastOpenStage.getY() + 50);
        // Set the current Stage as the last opened Stage
        this.lastOpenStage = stage;
        area.appendText("Before stage.showAndWait(): " + stageNumber + "\n");
        // Show the Stage and wait for it to close
        stage.showAndWait();
        area.appendText("After stage.showAndWait(): " + stageNumber + "\n");
    }
}

