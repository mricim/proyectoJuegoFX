package main.java.test.tiempo;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.java.utils.Time;


import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;

import static main.java.test.TestTiempo.ZONE_GTM;

public class Reloj extends Application {
    public static final int CADA_X_MIN=5;
    // we are allowed to create UI objects on non-UI thread
    private final Text txtTime = new Text();

    private volatile boolean enough = false;


    private static final ZoneId ZONE_UTC = ZoneOffset.UTC;


    // this is timer thread which will update out time view every second
    Thread timer = new Thread(() -> {
        SimpleDateFormat dt = new SimpleDateFormat("hh:mm:ss");
        while (!enough) {
            try {
                // running "long" operation not on UI thread
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
            }
            //final String time = dt.format(new Date());
            Platform.runLater(() -> {
                // updating live UI object requires JavaFX App Thread
                //txtTime.setText(time);
                txtTime.setText(LocalDateTime.now(ZONE_UTC).toString());

                int preMin_MenosUno=CADA_X_MIN-1;

                int minutos = preMin_MenosUno-(LocalDateTime.now(ZONE_UTC).getMinute()%CADA_X_MIN) ;
                int segundos = 59-LocalDateTime.now(ZONE_UTC).getSecond();
                System.out.println(minutos + " " + segundos);
                if (minutos == preMin_MenosUno&&segundos==59) {
                    System.out.println("XXX "+LocalDateTime.now(ZONE_UTC).toString());
                    //throw new Exception("ERROR");
                    //break;
                    //timer.interrupt();
                }
                //System.out.println(changeZone(LocalDateTime.now(ZONE_UTC)).toString());
            });
        }
    });


    @Override
    public void start(Stage stage) {
        // Layout Manager
        BorderPane root = new BorderPane();
        root.setCenter(txtTime);

        // creating a scene and configuring the stage
        Scene scene = new Scene(root, 200, 150);
        stage.initStyle(StageStyle.UTILITY);
        stage.setScene(scene);

        timer.start();
        stage.show();
    }

    // stop() method of the Application API
    @Override
    public void stop() {
        // we need to stop our working thread after closing a window
        // or our program will not exit
        enough = true;
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static LocalDateTime changeZone(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZONE_UTC).withZoneSameInstant(ZONE_GTM).toLocalDateTime();
    }
}