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

public class TeamLeaderTaskAssigment_Controller implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField EmpIdTextField;

    @FXML
    private TextField TaskIdTextField;

    ObservableList<IndivTask> TasksObservableList = FXCollections.observableArrayList();

    @FXML
    private TableView<IndivTask> TaskTableView;
    @FXML
    private TableColumn<IndivTask, Integer> Task_ID;
    @FXML
    private TableColumn<IndivTask, String> Task_Name;
    @FXML
    private TableColumn<IndivTask, String> Task_Desc;
    @FXML
    private TableColumn<IndivTask, Integer> Assigned_To;
    @FXML
    private TableColumn<IndivTask, String> Priority;
    @FXML
    private TableColumn<IndivTask, String> Task_Status;
    @FXML
    private TableColumn<IndivTask, Integer> Project_Id;






    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



        try{

            ArrayList<IndivTask> tasks = Utility.UserSingle.getInstance().emp.constructTasksList();

            if(!tasks.isEmpty()){

                TasksObservableList.addAll(tasks);

                Task_ID.setCellValueFactory(new PropertyValueFactory<IndivTask , Integer>("id"));
                Project_Id.setCellValueFactory(new PropertyValueFactory<IndivTask , Integer>("project"));
                Task_Name.setCellValueFactory(new PropertyValueFactory<IndivTask , String>("name"));
                Task_Desc.setCellValueFactory(new PropertyValueFactory<IndivTask , String>("description"));
                Task_Status.setCellValueFactory(new PropertyValueFactory<IndivTask , String>("status"));

                TaskTableView.setItems(TasksObservableList);

            }else{
                throw new Exception("A777aaa meeen");
            }



        }catch (Exception e){
                System.out.println(e);
        }
    }





    public void reAssignTask(ActionEvent event){

        int emp_id  = Integer.parseInt(EmpIdTextField.getText());
        int task_id = Integer.parseInt(TaskIdTextField.getText());

        try {
            if(Utility.UserSingle.getInstance().emp.reassignTask(task_id , emp_id)){

                System.out.println("Task Reassigned Successfully");
                switchScenes(event , "TaskAssigment.fxml");
            }else{
                throw new Exception("yalahwyyyy");
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
        switchScenes(event, "MainPage.fxml");


    }



    public void switchToTeamLeader_Requests(ActionEvent event) throws IOException {
        switchScenes(event,"TeamLeader_Requests.fxml");


    }
    public void switchToTasks(ActionEvent event) throws IOException {
        switchScenes(event,"Tasks.fxml");
    }


    public void switchScenes(ActionEvent event , String fxml) throws IOException {

        root = FXMLLoader.load(getClass().getResource(fxml));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}
