package com.example.project_pl2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class AddEmployee_Controller {


        @FXML
        private Button LogoutButton;

        private Scene scene;
        private Parent root;
        private Stage stage;
        @FXML
        private Label Selection;


        public void Logout(ActionEvent event)throws IOException {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Logout");
            alert.setHeaderText("You Are About To Logout");
            alert.setContentText("You Will Be Redirected To the Main Page");
            if (alert.showAndWait().get()== ButtonType.OK)
                switchToMainPage(event);
        }

        public void switchToMainPage(ActionEvent event) throws IOException {
            switchScenes(event,"MainPage.fxml");
        }



        public void switchToProjects(ActionEvent event) throws IOException {
            switchScenes(event,"Projects.fxml");
        }

        public void switchToRequests(ActionEvent event) throws IOException {
            switchScenes(event,"Requests.fxml");
        }




        public void switchScenes(ActionEvent event , String fxml) throws IOException {

            root = FXMLLoader.load(getClass().getResource(fxml));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }

    }




