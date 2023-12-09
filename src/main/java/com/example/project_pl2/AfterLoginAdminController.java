package com.example.project_pl2;


import javafx.beans.value.ObservableValue;
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
import java.util.ResourceBundle;
public class AfterLoginAdminController
        //implements Initializable
        {
    private Scene scene;
    private Parent root;
    private Stage stage;
//    @FXML
//    private Label Selection;
//
//    @FXML
//    private TableView<Project> projectTableView;
//    @FXML
//    private TableColumn<Project, Integer> project_id;
//    @FXML
//    private TableColumn<Project, String> project_title;
//    @FXML
//    private TableColumn<Project, String> project_status;
//    @FXML
//    private TableColumn<Project, String> project_desc;
//    @FXML
//    private TableColumn<Project, Integer> Assigned_to;
//
//    ObservableList<Project> Project = FXCollections.observableArrayList();
//
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//
//        project_id.setCellValueFactory(new PropertyValueFactory<Project,Integer>("project_id"));
//        project_title.setCellValueFactory(new PropertyValueFactory<Project,String>("project_title"));
//        project_status.setCellValueFactory(new PropertyValueFactory<Project,String>("project_status"));
//        Assigned_to.setCellValueFactory(new PropertyValueFactory<Project,Integer>("team_id"));
//
//
//
//    }
//

    public void switchToProjects(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Projects.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}

