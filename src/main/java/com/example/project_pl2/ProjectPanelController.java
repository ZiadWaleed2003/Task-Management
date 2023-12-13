package com.example.project_pl2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.sql.*;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;




//    @FXML
//    private TableView<Project> projectTableView;
//    @FXML
//    private TableColumn<Project, Integer> ID;
//    @FXML
//    private TableColumn<Project, String> Title;
//    @FXML
//    private TableColumn<Project, String> status;
//    @FXML
//    private TableColumn<Project, String> Descreption;
//    @FXML
//    private TableColumn<Project, Integer> Assigned_to;



//    ObservableList<Project> projectObservableList = FXCollections.observableArrayList();

    public class ProjectPanelController {
        public Button show;

        @FXML
        private TableView<Project> projectTableView;
        @FXML
        private TableColumn<Project, Integer> ID;
        @FXML
        private TableColumn<Project, String> Title;
        @FXML
        private TableColumn<Project, String> status;
        @FXML
        private TableColumn<Project, String> Descreption;
        @FXML
        private TableColumn<Project, Integer> Assigned_to;


        public void initialize() throws SQLException {
            loadData(); // Automatically load data on FXML load
            setCellValueFactories(); // Set cell value factories
        }

        public void loadData() throws SQLException {
            ArrayList<Project> projects = Utility.UserSingle.getInstance().admin.showProject(); // Your existing method to fetch data
            ObservableList<Project> projectObservableList = FXCollections.observableArrayList(projects);
            projectTableView.setItems(projectObservableList);
        }

        public void showDataButton() throws SQLException { // Button click handler
            loadData(); // Reload data when button is clicked
        }

        private void setCellValueFactories() {
            ID.setCellValueFactory(new PropertyValueFactory<>("project_id"));
            Title.setCellValueFactory(new PropertyValueFactory<>("project_title"));
            status.setCellValueFactory(new PropertyValueFactory<>("current_status"));
            Descreption.setCellValueFactory(new PropertyValueFactory<>("project_description"));
            Assigned_to.setCellValueFactory(new PropertyValueFactory<>("team_id"));
        }

    //}


//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//
//
//
//        try {
//            ArrayList<Project> proj = Utility.UserSingle.getInstance().admin.showProject();
//
//            for (Project project : proj) {
//                projectObservableList.add(project);
//
//
//            }
//
//            ID.setCellValueFactory(new PropertyValueFactory<Project , Integer>("project_id"));
//            Title.setCellValueFactory(new PropertyValueFactory<Project , String>("project_title"));
//            status.setCellValueFactory(new PropertyValueFactory<Project , String>("current_status"));
//            Descreption.setCellValueFactory(new PropertyValueFactory<Project , String>("project_description"));
//            Assigned_to.setCellValueFactory(new PropertyValueFactory<Project , Integer>("getTeamID()"));
//
//
//            projectTableView.setItems(projectObservableList);
//
//            // Set the items in the table
//
//
//            projectTableView.getColumns().addAll(ID, Title, status, Descreption, Assigned_to);
//
//
//            // Refresh the table view
//
////            projectTableView.refresh();
//
//        } catch (Exception e) {
//            e.printStackTrace(); // Handle the exception appropriately
//        }
//
//    }



    public void Logout(ActionEvent event)throws IOException{
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

    public void switchToRequests(ActionEvent event) throws IOException {
        switchScenes(event,"Requests.fxml");
    }

    public void switchToEmployees(ActionEvent event) throws IOException {
        switchScenes(event,"EmployeesView.fxml");
    }

    public void switchToAddProjects(ActionEvent event) throws IOException {
        switchScenes(event,"AddProjects.fxml");
    }

    public void switchScenes(ActionEvent event , String fxml) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }



    }

