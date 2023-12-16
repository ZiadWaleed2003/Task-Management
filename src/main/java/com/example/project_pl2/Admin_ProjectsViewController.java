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
import java.util.UnknownFormatConversionException;

public class Admin_ProjectsViewController implements Initializable {

    public Button LogoutButton;
    // Use the ObservableList to store data
    ObservableList<Project> projectObservableList = FXCollections.observableArrayList();
    @FXML
    private ChoiceBox<Utility.CompletionStatus> statusChoiceBox;

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


//    public Admin_ProjectsViewController() {
//
//        projectTableView = new TableView<Project>();
//        ID              = new TableColumn<Project , Integer>("project_id");
//        Title           = new TableColumn<Project , String>("project_title");
//        status          = new TableColumn<Project , String>("current_status");
//        Description = new TableColumn<Project , String>("project_description");
//        Assigned_to     = new TableColumn<Project , Integer>("team_id");
//        // ... other initialization code
//    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        statusChoiceBox.setItems(FXCollections.observableArrayList(Utility.CompletionStatus.values()));



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

    public void deleteProject(ActionEvent event) throws IOException {

        String text = delete_project_id.getText();

        int project_id = Integer.parseInt(text);

        try{

            if(Utility.UserSingle.getInstance().admin.deleteProject(project_id) && Utility.UserSingle.getInstance().admin.deleteProjectFile(project_id)){

                switchScenes(event , "Admin_ProjectsView.fxml");

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Successful Operation");
                alert.setHeaderText("Project with ID : " + project_id + " has been Deleted");
                System.out.println(" Hell yeah !");





            }else{
                throw new Exception("Operation failed");
            }


        }catch (Exception e){

//            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//            alert.setTitle("Failed Operation");
//            alert.setHeaderText(e + " please try again !");
//            alert.setContentText("You Will Be Redirected To the Main Page");
//            if (alert.showAndWait().get()== ButtonType.OK)


            System.out.println("Failed tany ahhhhhhhh");
            switchScenes(event , "Admin_ProjectsView.fxml");

        }


    }

    public void reAssignProject(ActionEvent event){

        int  proj_id = Integer.parseInt(update_project_id.getText());
        int  team_id = Integer.parseInt(update_team_id.getText());

        try{

            if(Utility.UserSingle.getInstance().admin.reassignProject(proj_id , team_id)){
                System.out.println("ReAssigned Successfully");

                    switchScenes(event , "Admin_ProjectsView.fxml");

//                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//                alert.setTitle("Successful Operation");
//                alert.setHeaderText("Project with ID : " + proj_id + " has been Deleted");
//                alert.setContentText("You Will Be Redirected To the Main Page");
//                if (alert.showAndWait().get()== ButtonType.OK)


            }else{
                throw new Exception("Error Happpened");
            }

        } catch (Exception e) {
            System.out.println(e + " مصيييييبة ألحقووونا ");
        }
    }

    @FXML
    private TextField DescTextField;

    @FXML
    private TextField IdTextField;

//    @FXML
//    private TextField StatusTextField;

    @FXML
    private TextField TeamIdTextField;

    @FXML
    private TextField TitleIdTextField;


    public void addProject(ActionEvent event) throws IOException {

        int project_id = Integer.parseInt(IdTextField.getText());
        int team_id    = Integer.parseInt(TeamIdTextField.getText());
        String desc    = DescTextField.getText();
        String title   = TitleIdTextField.getText();
//        Utility.CompletionStatus status  = Utility.CompletionStatus.valueOf(StatusTextField.getText());
        Utility.CompletionStatus selectedStatus = statusChoiceBox.getSelectionModel().getSelectedItem();

        String status = selectedStatus.name();



        try{
            if(Utility.UserSingle.getInstance().admin.createProject(project_id , team_id , desc , title ,
                    status) && Utility.UserSingle.getInstance().admin.addProjectFile(project_id)){

                switchScenes(event , "Admin_ProjectsView.fxml");

//                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//                alert.setTitle("Successful Operation");
//                alert.setHeaderText("Project with ID : " + project_id + " has been Deleted");
//                System.out.println(" Hell yeah !");





            }else{
                throw new Exception("Operation failed");
            }


        }catch (Exception e){

//            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//            alert.setTitle("Failed Operation");
//            alert.setHeaderText(e + " please try again !");
//            alert.setContentText("You Will Be Redirected To the Main Page");
//            if (alert.showAndWait().get()== ButtonType.OK)


            System.out.println("Failed tany ahhhhhhhh");
            switchScenes(event , "Admin_ProjectsView.fxml");

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
        switchScenes(event,"Admin_RequestsView.fxml");
    }

    public void switchToEmployees(ActionEvent event) throws IOException {
        switchScenes(event,"Admin_EmployeesView.fxml");
    }


    public void switchScenes(ActionEvent event , String fxml) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}

