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
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Leader_TasksViewController implements Initializable {

    @FXML
    private TextField EmpIdTextField;
    @FXML
    private TextField TaskIdTextField;
    @FXML
    private Button reassignTaskButton;
    @FXML
    private Button createTaskButton;
    @FXML
    private TextField crTaskNameTextField;
    @FXML
    private TextField crTaskProjectIDTextField;
    @FXML
    private TextField crTaskStatusTextField;
    @FXML
    private TextField crTaskPrioTextField;
    @FXML
    private DatePicker crTaskStartField;
    @FXML
    private DatePicker crTaskDueField;
    @FXML
    private TextField crTaskDescTextField;


    ObservableList<IndivTask> TasksObservableList = FXCollections.observableArrayList();
    @FXML
    private ChoiceBox<Utility.CompletionStatus> statusChoiceBox;

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
        statusChoiceBox.setItems(FXCollections.observableArrayList(Utility.CompletionStatus.values()));

        try{

            ArrayList<IndivTask> tasks = Utility.UserSingle.getInstance().emp.constructTasksList();

            if(!tasks.isEmpty()){

                TasksObservableList.addAll(tasks);

                Task_ID.setCellValueFactory(new PropertyValueFactory<IndivTask , Integer>("id"));
                Project_Id.setCellValueFactory(new PropertyValueFactory<IndivTask , Integer>("project"));
                Assigned_To.setCellValueFactory(new PropertyValueFactory<IndivTask , Integer>("assigned_to"));
                Task_Name.setCellValueFactory(new PropertyValueFactory<IndivTask , String>("name"));
                Priority.setCellValueFactory(new PropertyValueFactory<IndivTask , String>("priority"));
                Task_Desc.setCellValueFactory(new PropertyValueFactory<IndivTask , String>("description"));
                Task_Status.setCellValueFactory(new PropertyValueFactory<IndivTask , String>("status"));


                TaskTableView.setItems(TasksObservableList);

            }else{
                throw new Exception();
            }

        }catch (Exception e){
            System.out.println("Task Not Found");
        }
    }

    public void reAssignTask(ActionEvent event) throws IOException{

        int emp_id  = Integer.parseInt(EmpIdTextField.getText());
        int task_id = Integer.parseInt(TaskIdTextField.getText());

        try {
            if(Utility.UserSingle.getInstance().emp.reassignTask(task_id , emp_id)){
                switchScenes(event , "Leader_TasksView.fxml");
            }else{
                throw new Exception();
            }
        }catch (Exception e){

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("ERROR");
            alert.setHeaderText("OPERATION FAILED");
            alert.setContentText("PLEASE TRY AGAIN");
            if (alert.showAndWait().get()== ButtonType.OK){

                switchScenes(event , "Leader_TasksView.fxml");
            }
        }
    }

    public void createTask(ActionEvent event) throws IOException {
        String name = crTaskNameTextField.getText();
        String description = crTaskDescTextField.getText();
        Utility.CompletionStatus selectedStatus = statusChoiceBox.getSelectionModel().getSelectedItem();
        String status = selectedStatus.name();
        int project = Integer.parseInt(crTaskProjectIDTextField.getText());
        String priority = crTaskPrioTextField.getText(); // TODO: Where the fuck is the enum?
        LocalDate start_date = crTaskStartField.getValue();
        LocalDate due_date = crTaskDueField.getValue();


        try {
            if(Utility.UserSingle.getInstance().emp.addTask(name, description, status, project,
                    priority, start_date, due_date)){

                switchScenes(event,"Leader_TasksView.fxml");

            }else{

                throw new Exception();
            }

        }catch (Exception e){

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("ERROR");
            alert.setHeaderText("OPERATION FAILED");
            alert.setContentText("PLEASE TRY AGAIN");
            if (alert.showAndWait().get()== ButtonType.OK){

                switchScenes(event , "Leader_TasksView.fxml");
            }
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

    public void switchToProjects(ActionEvent event) throws IOException {
        switchScenes(event,"Leader_ProjectsView.fxml");

    }

    public void switchToTeam(ActionEvent event) throws IOException {
        switchScenes(event,"Leader_TeamView.fxml");
    }
    public void switchToTasks(ActionEvent event)throws IOException{
        switchScenes(event,"Leader_TasksView");
    }

    public void switchScenes(ActionEvent event , String fxml) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}




