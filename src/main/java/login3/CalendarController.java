package main.java.login3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class CalendarController extends Application {
    // https://developers.google.com/identity/sign-in/web/sign-in

    //https://o7planning.org/en/10339/using-scribe-oauth-java-api-with-google-oauth2#a351107
    //https://gist.github.com/yincrash/2465453
    //https://github.com/mstahv/jpa-invoicer/
    //https://vaadin.com/blog/implementing-sign-in-with-google-s-oauth-2-services
    //https://stackoverflow.com/questions/51481315/what-should-i-do-to-get-an-oauth2-access-token-to-my-javafx-spring-boot-desktop





    //oauth2-useragent
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        WebView browser = new WebView();

        WebEngine webEngine = browser.getEngine();

        com.sun.javafx.webkit.WebConsoleListener.setDefaultListener(
                (webView, message, lineNumber, sourceId)-> System.out.println("Console: [" + sourceId + ":" + lineNumber + "] " + message)
        );

        //webEngine.load("http://calendar.google.com");
        webEngine.load("https://accounts.google.com");

        StackPane root = new StackPane();
        root.getChildren().add(browser);

        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        System.setProperty("sun.net.http.allowRestrictedHeaders", "true");
        launch(args);
    }
}