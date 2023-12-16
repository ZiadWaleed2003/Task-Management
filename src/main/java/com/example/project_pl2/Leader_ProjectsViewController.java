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

public class Leader_ProjectsViewController implements Initializable {

    @FXML
        public Button LogoutButton;
        public Button TasksView;
        public Button Ass;
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
    @FXML
    private  ChoiceBox<Utility.CompletionStatus> statusChoiceBox;

//    public Leader_ProjectsViewController() {
//
//        projectTableView = new TableView<Project>();
//        ID              = new TableColumn<Project , Integer>("project_id");
//        Title           = new TableColumn<Project , String>("project_title");
//        status          = new TableColumn<Project , String>("current_status");
//        Description = new TableColumn<Project , String>("project_description");
//        Assigned_to     = new TableColumn<Project , Integer>("team_id");
//
//    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        statusChoiceBox.setItems(FXCollections.observableArrayList(Utility.CompletionStatus.values()));


        try {
            ArrayList<Project> proj = Utility.UserSingle.getInstance().emp.retrieveAllProjects();

            projectObservableList.addAll(proj);

            ID.setCellValueFactory(new PropertyValueFactory<Project , Integer>("project_id"));
            Title.setCellValueFactory(new PropertyValueFactory<Project , String>("project_title"));
            status.setCellValueFactory(new PropertyValueFactory<Project , String>("current_status"));
            Description.setCellValueFactory(new PropertyValueFactory<Project , String>("project_description"));
            Assigned_to.setCellValueFactory(new PropertyValueFactory<Project , Integer>("team_id"));


            projectTableView.setItems(projectObservableList);



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

    public void switchToTeam(ActionEvent event) throws IOException {
        switchScenes(event,"Leader_TeamView.fxml");
    }



    public void switchToTasks(ActionEvent event) throws IOException {
        switchScenes(event,"Leader_TasksView.fxml");
    }


    public void changeStatus(ActionEvent event){
        int proj_id = Integer.parseInt(ProjectIDTextField.getText());
//        int status = Utility.CompletionStatus.valueOf(StatusTextField.getText().toUpperCase()).ordinal();
        Utility.CompletionStatus selectedStatus = statusChoiceBox.getSelectionModel().getSelectedItem();
        int status = selectedStatus.ordinal();



        try {

                if(Utility.UserSingle.getInstance().emp.updateProjStatus(proj_id, status)){

                    System.out.println("Changed Status Successfully");

                    switchScenes(event , "Leader_ProjectsView.fxml");
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

