package com.example.project_pl2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class TeamLeader_Controller implements Initializable {

        @FXML
        private Button LogoutButton;
        @FXML
        private Label addtask_err_label;

        @FXML
    private TextField tasknametextfield;
        @FXML
    private TextField taskdesctextfield;
        @FXML
    private TextField projectIDtextfield;
        @FXML

    private ChoiceBox<Utility.CompletionStatus> completionStatusChoiceBox;
        @FXML
        private ChoiceBox<IndivTask.Priority> priorityChoiceBox;
    private TextField statustextfield;
        @FXML
        private TextField prioritytextfield;
        @FXML
        private DatePicker startdatetextfield;
        @FXML
        private DatePicker duedatetextfield;
        @FXML
        private TextField taskidtextfield;
        @FXML
        private TextField assigendtotextfield;


        private Scene scene;
        private Stage stage;
        private Parent root;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        completionStatusChoiceBox.getItems().addAll(Utility.CompletionStatus.values());
        priorityChoiceBox.getItems().addAll(IndivTask.Priority.values());
    }


        public void gettaskinfo(ActionEvent event){
            int task_id = Integer.parseInt(taskidtextfield.getText());
            String name = tasknametextfield.getText();
            String description = taskdesctextfield.getText();
//            Utility.CompletionStatus status = (Utility.CompletionStatus) completionStatusChoiceBox.getSelectionModel().getSelectedItem();
            int project_id = Integer.parseInt(projectIDtextfield.getText());
//            IndivTask.Priority priority = (IndivTask.Priority) priorityChoiceBox.getSelectionModel().getSelectedItem();
            int assignto =Integer.parseInt(assigendtotextfield.getText());
            Date startDate = Date.valueOf(startdatetextfield.getValue());
            Date dueDate = Date.valueOf(duedatetextfield.getValue());
//            boolean success = Employee.addTask(task_id,name,description,status,assignto,project_id,priority,startDate,dueDate);
//            if (success){
////                addtask_err_label.setText("Task Added Successfully");
//                Alert alert= new Alert(Alert.AlertType.INFORMATION);
//                alert.setTitle("Task Added");
//                alert.setContentText("Task Added Successfully");
            }






        public void createtaskbutton(ActionEvent event){
            int task_id = Integer.parseInt(taskidtextfield.getText());
            String name = tasknametextfield.getText();
            String description = taskdesctextfield.getText();
            Utility.CompletionStatus status = Utility.CompletionStatus.valueOf(statustextfield.getText());
            int project_id = Integer.parseInt(projectIDtextfield.getText());
            IndivTask.Priority priority = IndivTask.Priority.valueOf(prioritytextfield.getText());
            int assignto =Integer.parseInt(assigendtotextfield.getText());
            Date startDate = Date.valueOf(startdatetextfield.getValue());
            Date dueDate = Date.valueOf(duedatetextfield.getValue());
            boolean success = Employee.addTask(task_id,name,description,status,assignto,project_id,priority,startDate,dueDate);
            if (success){
//                addtask_err_label.setText("Task Added Successfully");
                Alert alert= new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Task Added");
                alert.setContentText("Task Added Successfully");
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
        public void switchToATaskAssigment(ActionEvent event) throws IOException {
            switchScenes(event,"TaskAssigment.fxml");


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


    public void getstat(ActionEvent event)throws IOException{
        IndivTask.Priority priority = (IndivTask.Priority) priorityChoiceBox.getSelectionModel().getSelectedItem();
    }
    public void getprioority(ActionEvent event)throws IOException{
         Utility.CompletionStatus status = (Utility.CompletionStatus) completionStatusChoiceBox.getSelectionModel().getSelectedItem();


    }

}



