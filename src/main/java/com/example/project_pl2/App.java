package com.example.project_pl2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;
import java.io.*;
import java.sql.*;
import  javafx.fxml.FXMLLoader.*;
import javafx.util.Pair;


public class App extends Application{
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("MainPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("WELCOME TO ELZOZAT TASK MANAGER");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();



    }


    public static void main(String[] args) throws SQLException {

         launch();


    }

}

