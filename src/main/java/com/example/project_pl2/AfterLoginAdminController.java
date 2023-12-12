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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
public class AfterLoginAdminController implements Initializable
        {
            @FXML
            private Button LogoutButton;

    private Scene scene;
    private Parent root;
    private Stage stage;
    @FXML
    private Label Selection;

    @FXML
    private TableView<Project> projectTableView;
    @FXML
    private TableColumn<Project, Integer> ID;
    @FXML
    private TableColumn<Project, String> Title;
    @FXML
    private TableColumn<Project, String> status;
    @FXML
    private TableColumn<Project, String> Descreption;
    @FXML
    private TableColumn<Project, Integer> Assigned_to;

    ObservableList<Project> Project = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ID.setCellValueFactory(new PropertyValueFactory<Project,Integer>("Project_Id"));
        Title.setCellValueFactory(new PropertyValueFactory<Project,String>("Project_Title"));
        status.setCellValueFactory(new PropertyValueFactory<Project,String>("Progress_status"));
        Descreption.setCellValueFactory(new PropertyValueFactory<Project,String>("Project_desc"));
        Assigned_to.setCellValueFactory(new PropertyValueFactory<Project,Integer>("Assigned_To"));

    String query = "SELECT * FROM Project";
        ResultSet resultSet= CRUD2.readDbDynamic(query);
        try{while (resultSet.next()){
            int project_id = resultSet.getInt("Project_Id");
            String project_title = resultSet.getString("Project_Title");
            Utility.CompletionStatus project_status = Utility.CompletionStatus.valueOf(resultSet.getString("Progress_status"));
            String project_desc = resultSet.getString("project_desc");
            int Assigned_to=resultSet.getInt("Assigned_To");
            Admin admin = new Admin();
            admin.createProject(project_id,Assigned_to,project_status,project_desc,project_title);



        }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        projectTableView.setItems(Project);


    }



            public void Logout(ActionEvent event)throws IOException{
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Logout");
                alert.setHeaderText("You Are About To Logout");
                alert.setContentText("You Will Be Redirected To the Main Page");
                if (alert.showAndWait().get()==ButtonType.OK)
                    switchToMainPage(event);}


            public void switchToMainPage(ActionEvent event) throws IOException {
                switchScenes(event,"MainPage.fxml");
            }

            public void switchToRequests(ActionEvent event) throws IOException {
                switchScenes(event,"Requests.fxml");
            }

            public void switchToEmployees(ActionEvent event) throws IOException {
                switchScenes(event,"EmployeesView.fxml");
            }

    public void switchToProjects(ActionEvent event) throws IOException {
        switchScenes(event,"Projects.fxml");
    }

            public void switchToAddEmployee(ActionEvent event) throws IOException {
                switchScenes(event,"AddEmployee.fxml");
            }

            public void switchToAddProjects(ActionEvent event) throws IOException {
                switchScenes(event,"AddPorject.fxml");
            }

            public void switchScenes(ActionEvent event , String fxml) throws IOException {

                root = FXMLLoader.load(getClass().getResource(fxml));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            }
        }

