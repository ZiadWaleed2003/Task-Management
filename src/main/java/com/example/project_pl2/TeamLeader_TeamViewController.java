package com.example.project_pl2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
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

public class TeamLeader_TeamViewController implements Initializable {


    private Scene scene;
    private Parent root;
    private Stage stage;



    ObservableList<Employee> TeamObservableList = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Employee, String> Emp_Dep;

    @FXML
    private TableColumn<Employee, Integer> Emp_Id;

    @FXML
    private TableColumn<Employee, String> Emp_Name;

    @FXML
    private TableColumn<Employee, String> Emp_Role;

    @FXML
    private TableColumn<Employee, Integer> Emp_Team_Id;

    @FXML
    private TableView<Employee> TeamTableView;


    @FXML
    private Label Team_View;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


            try {

                ArrayList<Employee> Emp_data = Utility.UserSingle.getInstance().emp.ShowTeam();

                if(!Emp_data.isEmpty()){
                        TeamObservableList.addAll(Emp_data);


                    Emp_Id.setCellValueFactory(new PropertyValueFactory<Employee , Integer>("id"));
                    Emp_Team_Id.setCellValueFactory(new PropertyValueFactory<Employee , Integer>("team_id"));
                    Emp_Name.setCellValueFactory(new PropertyValueFactory<Employee , String>("name"));
                    Emp_Role.setCellValueFactory(new PropertyValueFactory<Employee , String>("role"));
                    Emp_Dep.setCellValueFactory(new PropertyValueFactory<Employee , String>("department"));

                    TeamTableView.setItems(TeamObservableList);
                }else{

                    throw new Exception();
                }

            }catch (Exception e){
                System.out.println(e);
            }

    }


    public void switchToProjects(ActionEvent event) throws IOException {
        switchScenes(event , "Leader_ProjectsView.fxml");
    }

    public void switchToTasks(ActionEvent event) throws IOException {
        switchScenes(event , "Leader_TasksView.fxml");
    }

    public void Logout(ActionEvent event)throws IOException{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You Are About To Logout");
        alert.setContentText("You Will Be Redirected To the Main Page");
        if (alert.showAndWait().get() == ButtonType.OK)
            switchToMainPage(event);
    }

    public void switchToMainPage(ActionEvent event) throws IOException {
        switchScenes(event,"MainPage.fxml");
    }





    public void switchScenes(ActionEvent event , String fxml) throws IOException {

        root = FXMLLoader.load(getClass().getResource(fxml));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}
