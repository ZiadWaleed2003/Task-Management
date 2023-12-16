package com.example.project_pl2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class Employee_DashboardController implements Initializable {
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
    private TableColumn<IndivTask, Date> Start_date;
    @FXML
    private TableColumn<IndivTask, Date> Due_date;
    @FXML
    private TableColumn<IndivTask, Integer> Project_id;
    @FXML
    private TextField tasktextfield;
    @FXML
    private TextField statustextfield;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            ArrayList<IndivTask> tasks = Utility.UserSingle.getInstance().emp.constructTasksList();
            taskObservableList.addAll(tasks);




            ID.setCellValueFactory(new PropertyValueFactory<IndivTask, Integer>("id"));
            Name.setCellValueFactory(new PropertyValueFactory<IndivTask, String>("name"));
            Description.setCellValueFactory(new PropertyValueFactory<IndivTask, String>("description"));
            Status.setCellValueFactory(new PropertyValueFactory<IndivTask, String>("status"));
            Assigned_to.setCellValueFactory(new PropertyValueFactory<IndivTask, Integer>("assigned_to"));
            Priority.setCellValueFactory(new PropertyValueFactory<IndivTask, String>("priority"));
            Start_date.setCellValueFactory(new PropertyValueFactory<IndivTask, Date>("start_date"));
            Due_date.setCellValueFactory(new PropertyValueFactory<IndivTask, Date>("due_date"));
            Project_id.setCellValueFactory(new PropertyValueFactory<IndivTask, Integer>("project"));

            taskTableView.setItems(taskObservableList);

        } catch(Exception e){
           //alert
        }

    }

    public void changeStatus(ActionEvent event){
        int task_id = Integer.parseInt(tasktextfield.getText());
        int status = Utility.CompletionStatus.valueOf(statustextfield.getText().toUpperCase()).ordinal();


        try {

            if(Utility.UserSingle.getInstance().emp.updateTaskStatus(task_id,status)){

                System.out.println("Changed Status Successfully");

                switchScenes(event , "Employee_Dashboard.fxml");
            }else{
                throw new Exception("fe 5ara 7sl fe change status");
            }


        }catch (Exception e){

            System.out.println(e);
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
        switchScenes(event,"MainPage.fxml");

    }




    public void switchToEmpRequests(ActionEvent event) throws IOException {
        switchScenes(event,"Employee_Requests.fxml");

    }
    public void switchToEmp_Dasboard(ActionEvent event) throws IOException {
        switchScenes(event,"Employee_Dashboard.fxml");

    }
    public void switchToModify_Emp_Profile(ActionEvent event) throws IOException {
        switchScenes(event,"Employee_Data.fxml");

    }
    public void switchScenes(ActionEvent event , String fxml) throws IOException {

        root = FXMLLoader.load(getClass().getResource(fxml));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


}
