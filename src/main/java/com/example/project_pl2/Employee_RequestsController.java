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

public class Employee_RequestsController implements Initializable {
    ObservableList<Request> requestObservableList = FXCollections.observableArrayList();

    @FXML
    private TableView<Request> requestTableView;
    @FXML
    private TableColumn<Request,Integer> ID;
    @FXML
    private TableColumn<Request,Integer> by_ID;

    @FXML
    private TableColumn<Request,String> Type;
    @FXML
    private TableColumn<Request,String> request_desc;
    @FXML
    private TableColumn<Request,Boolean> status;
    @FXML
    private TextField request_id;

    private Scene scene;
    private Stage stage;
    private Parent root;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println("Ha5a");
        try {
            ArrayList<Request> requests = Utility.UserSingle.getInstance().emp.retrieveAllRequests();

            requestObservableList.addAll(requests);

            ID.setCellValueFactory(new PropertyValueFactory<Request,Integer>("request_id"));
            by_ID.setCellValueFactory(new PropertyValueFactory<Request,Integer>("requested_by"));
            Type.setCellValueFactory(new PropertyValueFactory<Request,String>("request_type"));
            request_desc.setCellValueFactory(new PropertyValueFactory<Request,String>("request_description"));
            status.setCellValueFactory(new PropertyValueFactory<Request,Boolean>("request_status"));

            requestTableView.setItems(requestObservableList);


        } catch (Exception e){
            System.out.println("A7A");
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



    public void switchToEmpDashboard(ActionEvent event) throws IOException {
        switchScenes(event,"Employee_Dashboard.fxml");
    }

    public void switchToModify(ActionEvent event) throws IOException {
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
