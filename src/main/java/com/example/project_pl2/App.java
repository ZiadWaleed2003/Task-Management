
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
<<<<<<< HEAD
        launch();
//        Object[] arrgs = {5, "Test query for dynamic DML func", "Something"};
//        Pair<Boolean, Integer> res = CRUD2.updateDbDynamic("INSERT INTO request (Request_Id, Request_Desc," +
//                "Request_type) VALUES (?, ?, ?)",arrgs);
=======
        //launch();
        Object[] arrgs = {12, "Test query for dynamic DML func", "Something"};
        Pair<Boolean, Integer> res = CRUD2.updateDbDynamic("INSERT INTO request (Request_Id, Request_Desc," +
                "Request_type) VALUES (?, ?, ?)",arrgs);
        if(res.getKey())
            System.out.print("result is : ");
        else
            System.out.print("Fasle");

>>>>>>> 3f37d5115c500ccc11d60f14ea85078edfc981f8
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


