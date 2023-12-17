package com.example.project_pl2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Employee_DataController implements Initializable {
    private Scene scene;
    private Stage stage;
    private Parent root;

    @FXML
    private Label Emp_Email;

    @FXML
    private Label Emp_Id;

    @FXML
    private Label Emp_Name;

    @FXML
    private Label Emp_Password;

    @FXML
    private Label Emp_Role;

    @FXML
    private Label Emp_TeamId;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Emp_Name.setText(Utility.UserSingle.getInstance().emp.getName());
        Emp_Email.setText(Utility.UserSingle.getInstance().emp.getEmail());
        Emp_Id.setText(Integer.toString(Utility.UserSingle.getInstance().emp.getId()));
        Emp_Password.setText(Utility.UserSingle.getInstance().emp.getPassword());
        Emp_TeamId.setText(Integer.toString(Utility.UserSingle.getInstance().emp.getTeam_Id()));
        Emp_Role.setText(Utility.UserSingle.getInstance().emp.getRole());

    }



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



    public void switchToEmpDashboard(ActionEvent event) throws IOException {
        switchScenes(event,"Employee_Dashboard.fxml");
    }

    public void switchToRequests(ActionEvent event) throws IOException {
        switchScenes(event,"Employee_Requests.fxml");
    }
    public void switchToTasks(ActionEvent event) throws IOException {
        switchScenes(event,"Employee_Dashboard.fxml");
    }




    public void switchScenes(ActionEvent event , String fxml) throws IOException {

        root = FXMLLoader.load(getClass().getResource(fxml));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}

