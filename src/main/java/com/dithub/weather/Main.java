package com.dithub.weather;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        //creating preloader task
        Task<Void> task = createLongStartTask();

        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource("ui.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setTitle("Weather forecast powered by Dithub");
        primaryStage.setScene(new Scene(root, 1000, 700));
        primaryStage.setResizable(false);

        //creating preloader object
        Preloader preloader = new Preloader();
        preloader.progressProperty().bind(task.progressProperty());

        //if preloader task finished --> show main theme, needed for loading the API in background (Should rather be done with prom await
        task.setOnSucceeded(e -> {
            primaryStage.show();
            preloader.hide();
        });
        preloader.show();
        new Thread(task).start();

    }

    private Task<Void> createLongStartTask() {
        // simulating the api "loading"
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                int max = 10;
                for (int i = 1; i <= max; i++) {
                    Thread.sleep(200);
                    updateProgress(i, max);
                }
                return null;
            }
        };
        return task ;
    }
}
