package edu.fct.wholesalemanagemetsystem.controller;


import edu.fct.wholesalemanagemetsystem.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class SearchCustomerController {

    @FXML
    private AnchorPane customerPane;

    @FXML
    void loadBack(MouseEvent event) throws IOException {
        Pane newLoadedPane =  FXMLLoader.load(Main.class.getResource("customerView.fxml"));
        customerPane.getChildren().add(newLoadedPane);
    }

}
