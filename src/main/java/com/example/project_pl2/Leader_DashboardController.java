package com.example.project_pl2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class Leader_DashboardController {

        private Scene scene;
        private Stage stage;
        private Parent root;





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
            switchScenes(event,"Leader_ProjectsView.fxml");


        }
        public void switchToTasks(ActionEvent event) throws IOException {
            switchScenes(event,"Leader_TasksView.fxml");
        }
        public void switchToTeam(ActionEvent event) throws IOException {
            switchScenes(event,"Leader_TeamView.fxml");
        }
        public void switchToProfile(ActionEvent event) throws IOException {
            switchScenes(event,"Leader_Profile.fxml");
        }



    public void switchScenes(ActionEvent event , String fxml) throws IOException {

        root = FXMLLoader.load(getClass().getResource(fxml));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


}



