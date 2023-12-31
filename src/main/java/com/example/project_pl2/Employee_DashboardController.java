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
    private ChoiceBox<Utility.CompletionStatus> statusChoiceBox;

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




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        statusChoiceBox.setItems(FXCollections.observableArrayList(Utility.CompletionStatus.values()));

        try {
            ArrayList<IndivTask> tasks = Utility.UserSingle.getInstance().emp.constructTasksList();

            if (!tasks.isEmpty()) {

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

            }

        } catch(Exception e){

        }
    }


    public void changeStatus(ActionEvent event) throws IOException{
        int task_id = Integer.parseInt(tasktextfield.getText());
//        int status = Utility.CompletionStatus.valueOf(statustextfield.getText().toUpperCase()).ordinal();
        Utility.CompletionStatus selectedStatus = statusChoiceBox.getSelectionModel().getSelectedItem();
        int status = selectedStatus.ordinal();

        try {

            if (Utility.UserSingle.getInstance().emp.updateTaskStatus(task_id, status) && (task_id != 0)) {
                switchScenes(event, "Employee_Dashboard.fxml");
            } else {
                throw new Exception();
            }


        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("ERROR");
            alert.setHeaderText("OPERATION FAILED");
            alert.setContentText("PLEASE TRY AGAIN");
            if (alert.showAndWait().get() == ButtonType.OK) {

                switchScenes(event, "Employee_Dashboard.fxml");

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
