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
import java.util.ResourceBundle;

public class AddProjects_Controller {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField DescTextField;

    @FXML
    private TextField IdTextField;

    @FXML
    private TextField StatusTextField;

    @FXML
    private Button LogoutButton;

    @FXML
    private TextField TeamIdTextField;

    @FXML
    private TextField TitleIdTextField;





    public void addProject(ActionEvent event) throws IOException {

        int project_id = Integer.parseInt(IdTextField.getText());
        int team_id    = Integer.parseInt(TeamIdTextField.getText());
        String desc    = DescTextField.getText();
        String title   = TitleIdTextField.getText();
        String status  = StatusTextField.getText();


        try{

            if(Utility.UserSingle.getInstance().admin.createProject(project_id , team_id , desc , title , status) && Utility.UserSingle.getInstance().admin.addProjectFile(project_id)){

                switchScenes(event , "AddProject.fxml");

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
            switchScenes(event , "AddProject.fxml");

        }


    }


    public void switchToEditProject(ActionEvent event) throws IOException {

        switchScenes(event , "Projects.fxml");
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


    public void switchToEmployees(ActionEvent event)throws IOException{
        switchScenes(event,"EmployeesView.fxml");
    }


    public void switchToRequests(ActionEvent event) throws IOException {
        switchScenes(event,"Requests.fxml");
    }




    public void switchScenes(ActionEvent event , String fxml) throws IOException {

        root = FXMLLoader.load(getClass().getResource(fxml));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}




