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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ProjectPanelController implements Initializable {

    // Use the ObservableList to store data
    ObservableList<Project> projectObservableList = FXCollections.observableArrayList();

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

    public ProjectPanelController() {

        projectTableView = new TableView<Project>();
        ID              = new TableColumn<Project , Integer>("project_id");
        Title           = new TableColumn<Project , String>("project_title");
        status          = new TableColumn<Project , String>("current_status");
        Descreption     = new TableColumn<Project , String>("project_description");
        Assigned_to     = new TableColumn<Project , Integer>("team_id");
        // ... other initialization code
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {




        try {
            ArrayList<Project> proj = Utility.UserSingle.getInstance().admin.showProject();

            for (Project project : proj) {
                projectObservableList.add(project);

            }




            ID.setCellValueFactory(new PropertyValueFactory<Project, Integer>("getProjectID()"));
            Title.setCellValueFactory(new PropertyValueFactory<Project, String>("project_title"));
            status.setCellValueFactory(new PropertyValueFactory<Project, String>("current_status"));
            Descreption.setCellValueFactory(new PropertyValueFactory<Project, String>("project_description"));
            Assigned_to.setCellValueFactory(new PropertyValueFactory<Project, Integer>("team_id"));

            // Set the items in the table
            projectTableView.setItems(projectObservableList);

            projectTableView.getColumns().addAll(ID, Title, status, Descreption, Assigned_to);


            // Refresh the table view
            projectTableView.refresh();

        } catch (Exception e) {
//            e.printStackTrace(); // Handle the exception appropriately
        }

    }



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
        switchScenes(event,"Projects.fxml");
    }

    public void switchScenes(ActionEvent event , String fxml) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}

