package com.example.project_pl2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Employee_Controller implements Initializable {
    //TODO implement a method that takes task id and changes its status
    //this is the employees dashboard when they login
    private Scene scene;
    private Stage stage;
    private Parent root;

    ObservableList<IndivTask> taskObservableList  = FXCollections.observableArrayList();

    @FXML
    private TableView<IndivTask> taskTableView;
    @FXML
    private TableColumn<IndivTask, Integer> ID;
    @FXML
    private TableColumn<IndivTask, String> Name;
    @FXML
    private TableColumn<IndivTask, String> Description;
    @FXML
    private TableColumn<IndivTask, String> Status;
    @FXML
    private TableColumn<IndivTask, Integer> Assigned_to;
    @FXML
    private TableColumn<IndivTask, String> Priority;
    @FXML
    private TableColumn<IndivTask, String> Start_date;
    @FXML
    private TableColumn<IndivTask, String> Due_date;
    @FXML
    private TableColumn<IndivTask, Integer> Project_id;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            taskTableView = new TableView<>();

            ID.setCellValueFactory(new PropertyValueFactory<IndivTask, Integer>("id"));
            Name.setCellValueFactory(new PropertyValueFactory<IndivTask, String>("name"));
            Description.setCellValueFactory(new PropertyValueFactory<IndivTask, String>("description"));
//        Status.setCellValueFactory(new PropertyValueFactory<IndivTask,String>("status"));
            Assigned_to.setCellValueFactory(new PropertyValueFactory<IndivTask, Integer>("assigned_to"));
            Priority.setCellValueFactory(new PropertyValueFactory<IndivTask, String>("priority"));
            Start_date.setCellValueFactory(new PropertyValueFactory<IndivTask, String>("start_date"));
            Due_date.setCellValueFactory(new PropertyValueFactory<IndivTask, String>("due_date"));
            Project_id.setCellValueFactory(new PropertyValueFactory<IndivTask, Integer>("project_id"));
        } catch(Exception e){

        }

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
        root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }




    public void switchToEmpRequests(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Employee_Requests.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    public void switchToEmp_Dasboard(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Employee_Dashboard.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    public void switchToModify_Emp_Profile(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Modify_Employee_Profile.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


}
