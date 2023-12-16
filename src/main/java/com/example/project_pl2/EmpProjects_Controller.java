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

public class EmpProjects_Controller implements Initializable {

    @FXML
        public Button LogoutButton;
        public Button TasksView;
        public Button ModifyProfile;
        public Button ChangeStatus;

    @FXML
            public TextField ProjectIDTextField;
            public TextField StatusTextField;

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

    public EmpProjects_Controller() {

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
            ArrayList<Project> proj = Utility.UserSingle.getInstance().emp.retrieveAllProjects();

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
        }}



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



    public void switchToEmpDashboard(ActionEvent event) throws IOException {
        switchScenes(event,"Employee_Dashboard.fxml");
    }

    public void switchToRequests(ActionEvent event) throws IOException {
        switchScenes(event,"EmployeeRequests.fxml");
    }


    public void changeStatus(ActionEvent event){
        int proj_id = Integer.parseInt(ProjectIDTextField.getText());
        int status = Integer.parseInt(StatusTextField.getText());

        try {

                if(Utility.UserSingle.getInstance().emp.updateProjStatus(proj_id, status)){

                    System.out.println("Changed Status Successfully");

                    switchScenes(event , "EmpProjects.fxml");
                }else{
                    throw new Exception("fe 5ara 7sl fe change status");
                }


        }catch (Exception e){

                System.out.println(e);
        }
    }




    public void switchScenes(ActionEvent event , String fxml) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}

