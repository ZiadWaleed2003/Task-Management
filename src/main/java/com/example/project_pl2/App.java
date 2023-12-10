
package com.example.project_pl2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

//<<<<<<< HEAD
import java.io.IOException;
//=======
import java.io.*;
import java.sql.*;

import  javafx.fxml.FXMLLoader.*;
import javafx.util.Pair;
//>>>>>>> main

public class App extends Application{
    @Override
    public void start(Stage stage) throws IOException {

//        Parent root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("MainPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("WELCOME TO ELZOZAT TASK MANAGER");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();



//        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("MainPage.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//        stage.setTitle("WELCOME TO ELZOZAT TASK MANAGER");
//        stage.setScene(scene);
//        stage.setResizable(false);
//        stage.show();



    }
//<<<<<<< HEAD
    public static void main(String[] args) {

         launch();


    }

//=======
//    public static void main(String[] args) throws SQLException {
//
//        Employee emp = new Employee();
//        boolean x = emp.login("ZiadWaleed@gmail.com","2003");
////>>>>>>> main
//    }
}
//}


