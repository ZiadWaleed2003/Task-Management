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
import java.util.ArrayList;
import java.util.ResourceBundle;
public class Admin_RequestsViewController implements  Initializable{
    @FXML
    private Button LogoutButton;
    ObservableList<Request> requestObservableList = FXCollections.observableArrayList();

    @FXML
    private TableView<Request>  requestTableView;
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
    private Parent root;
    private Stage stage;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        try {


            ArrayList<Request> requests = Utility.UserSingle.getInstance().admin.reviewRequests();

            requestObservableList.addAll(requests);


            ID.setCellValueFactory(new PropertyValueFactory<Request,Integer>("request_id"));
            by_ID.setCellValueFactory(new PropertyValueFactory<Request,Integer>("requested_by"));
            Type.setCellValueFactory(new PropertyValueFactory<Request,String>("request_type"));
            request_desc.setCellValueFactory(new PropertyValueFactory<Request,String>("request_description"));
            status.setCellValueFactory(new PropertyValueFactory<Request,Boolean>("request_status"));

            requestTableView.setItems(requestObservableList);


        } catch (Exception e){
            System.out.println("No Requests Found");
        }



    }

    public void acceptRequest(ActionEvent event) throws IOException {

        int req_id = Integer.parseInt(request_id.getText());

        try{
            if(Utility.UserSingle.getInstance().admin.requestResponse(req_id , 1)){
                System.out.println("Request Accepted");

                switchScenes(event , "Admin_RequestsView.fxml");

            }else{

                throw new Exception();
            }
        }catch (Exception e) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("ERROR");
            alert.setHeaderText("OPERATION FAILED");
            alert.setContentText("PLEASE TRY AGAIN");
            if (alert.showAndWait().get()== ButtonType.OK){

                switchScenes(event , "Admin_RequestsView.fxml");
            }


        }

    }
    public void refuseRequest(ActionEvent event) throws IOException {
        int req_id = Integer.parseInt(request_id.getText());
        try{

            if(Utility.UserSingle.getInstance().admin.requestResponse(req_id, 0)){

                switchScenes(event, "Admin_RequestsView.fxml");
            }else{
                throw new Exception();
            }

        }catch (Exception e){

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("ERROR");
            alert.setHeaderText("OPERATION FAILED");
            alert.setContentText("PLEASE TRY AGAIN");
            if (alert.showAndWait().get()== ButtonType.OK){

                switchScenes(event , "Admin_RequestsView.fxml");
            }
        }

    }


            public void Logout(ActionEvent event)throws IOException{
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Logout");
                alert.setHeaderText("You Are About To Logout");
                alert.setContentText("You Will Be Redirected To the Main Page");
                if (alert.showAndWait().get()==ButtonType.OK)
                    switchToMainPage(event);
            }

            public void switchToMainPage(ActionEvent event) throws IOException {
                switchScenes(event,"MainPage.fxml");
            }

            public void switchToEmployees(ActionEvent event) throws IOException {
                switchScenes(event,"Admin_EmployeesView.fxml");
            }

            public void switchToProjects(ActionEvent event) throws IOException {
                switchScenes(event,"Admin_ProjectsView.fxml");
                   }





        public void switchScenes(ActionEvent event , String fxml) throws IOException {

                root = FXMLLoader.load(getClass().getResource(fxml));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            }


}

