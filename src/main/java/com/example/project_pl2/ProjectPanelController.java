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

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProjectPanelController implements Initializable {

    public Button LogoutButton;
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
    private TableColumn<Project, String> Description;
    @FXML
    private TableColumn<Project, Integer> Assigned_to;

    @FXML
    private TextField update_project_id;

    @FXML
    private TextField update_team_id;

    @FXML
    private TextField delete_project_id;


    public ProjectPanelController() {

        projectTableView = new TableView<Project>();
        ID              = new TableColumn<Project , Integer>("project_id");
        Title           = new TableColumn<Project , String>("project_title");
        status          = new TableColumn<Project , String>("current_status");
        Description = new TableColumn<Project , String>("project_description");
        Assigned_to     = new TableColumn<Project , Integer>("team_id");
        // ... other initialization code
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



        try {
            ArrayList<Project> proj = Utility.UserSingle.getInstance().admin.showProject();

            projectObservableList.addAll(proj);

            ID.setCellValueFactory(new PropertyValueFactory<Project , Integer>("project_id"));
            Title.setCellValueFactory(new PropertyValueFactory<Project , String>("project_title"));
            status.setCellValueFactory(new PropertyValueFactory<Project , String>("current_status"));
            Description.setCellValueFactory(new PropertyValueFactory<Project , String>("project_description"));
            Assigned_to.setCellValueFactory(new PropertyValueFactory<Project , Integer>("team_id"));


            projectTableView.setItems(projectObservableList);

            // Set the items in the table


//            projectTableView.getColumns().addAll(ID, Title, status, Descreption, Assigned_to);


            // Refresh the table view

//            projectTableView.refresh();

        } catch (Exception e) {
            System.out.print("مصييييبة"); // Handle the exception appropriately
        }

    }

    public void deleteProject(ActionEvent event){

        String text = delete_project_id.getText();

        int project_id = Integer.parseInt(text);

        try{

            if(Utility.UserSingle.getInstance().admin.deleteProject(project_id)){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Successful Operation");
                alert.setHeaderText("Project with ID : " + project_id + " has been Deleted");

                System.out.println(" Hell yeah !");
            }else{
                throw new Exception("Operation failed");
            }


        }catch (Exception e){

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Failed Operation");
            alert.setHeaderText(e + " please try again !");

            System.out.println("Failed tany ahhhhhhhh");

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
        switchScenes(event,"AddProject.fxml");
    }

    public void switchScenes(ActionEvent event , String fxml) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}

