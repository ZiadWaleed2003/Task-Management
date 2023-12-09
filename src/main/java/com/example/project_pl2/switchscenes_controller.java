package com.example.project_pl2;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class switchscenes_controller {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToAdmin(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Admin.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

//        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Admin.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//        stage.setScene(scene);
//        stage.setResizable(false);
//        stage.show();
    }

    public void switchToEmployee(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Employee.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

//        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Employee.fxml"));
//        Scene scene = new Scene(fxmlLoader.load());
//        stage.setScene(scene);
//        stage.setResizable(false);
//        stage.show();
    }
}