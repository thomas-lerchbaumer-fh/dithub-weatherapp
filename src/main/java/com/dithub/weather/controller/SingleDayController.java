package com.dithub.weather.controller;

import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.scene.control.Label;
import javafx.collections.ObservableList;

public class SingleDayController {

    @FXML
    private Label lblTest;

    @FXML
    private void initialize() {
        lblTest.setText("I'm a Label.");
    }

    @FXML
    ObservableList observableList = FXCollections.observableArrayList();
}
