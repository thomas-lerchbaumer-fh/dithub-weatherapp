package com.dithub.weather;

import javafx.scene.Scene;
import javafx.beans.property.DoubleProperty ;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Preloader {
    private ProgressBar bar;

    private Stage stage;

    public Preloader() {
        this.stage = new Stage();
        stage.setScene(createPreloaderScene());
    }

    private Scene createPreloaderScene() {
        bar = new ProgressBar(0);
        BorderPane p = new BorderPane();
        this.stage.setTitle("Loading...");
        p.setCenter(bar);

        //some css to change the bg color of the borderPane and the progress bar could also be done in separate fxml file
        p.setStyle("-fx-background-color: #212121;");
        bar.setStyle("-fx-border-color: #212121");
        bar.setStyle("-fx-border-color: #212121 !important;");
        bar.setStyle("-fx-text-box-border: #212121;");
        bar.setStyle("-fx-control-inner-background: #212121;");
        bar.setStyle("-fx-accent: #cc6600;");

        //function returning the new scene which is called @Main
        return new Scene(p, 300, 150);
    }

    public void show() {
        stage.show();
    }

    public void hide() {
        stage.hide();
    }

    public DoubleProperty progressProperty() {
        return bar.progressProperty();
    }
}