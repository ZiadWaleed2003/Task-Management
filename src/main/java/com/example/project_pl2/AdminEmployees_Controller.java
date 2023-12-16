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


public class AdminEmployees_Controller  implements Initializable {

        @FXML
        private Button LogoutButton;

        private Scene scene;
        private Parent root;
        private Stage stage;
        @FXML
        private Label Selection;

    @FXML
    private TextField Emp_email;


    @FXML
    private TextField emp_name;
    @FXML
    private TextField emp_password;
    @FXML
    private TextField emp_team;
    @FXML
    private TextField emp_type;
    @FXML
    private TextField employee_id;
    @FXML
    private TextField emp_role;

    ObservableList<Employee> EmployeeObservableList = FXCollections.observableArrayList();

    @FXML
    private TableView<Employee> EmployeeTableView;
    @FXML
    private TableColumn<Employee, Integer> Emp_ID;
    @FXML
    private TableColumn<Employee, String> Emp_Name;
    @FXML
    private TableColumn<Employee, String> Emp_Email;
    @FXML
    private TableColumn<Employee, String> Emp_Password;
    @FXML
    private TableColumn<Employee, Integer> Emp_Team;
    @FXML
    private TableColumn<Employee, String> Emp_Depart;
    @FXML
    private TableColumn<Employee, String> Emp_Role;
    @FXML
    private TableColumn<Employee, String> Emp_Type;

    @FXML
    private TextField IdTextFieldDel;

    @FXML
    private TextField IdTextFieldUp;
    @FXML
    private TextField IdTextFieldUp1;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try{
            ArrayList<Employee> Employees = Utility.UserSingle.getInstance().admin.ShowEmployees();

            if(!Employees.isEmpty()){

                EmployeeObservableList.addAll(Employees);

                Emp_ID.setCellValueFactory(new PropertyValueFactory<Employee , Integer>("id"));
                Emp_Team.setCellValueFactory(new PropertyValueFactory<Employee , Integer>("team_id"));
                Emp_Name.setCellValueFactory(new PropertyValueFactory<Employee , String>("name"));
                Emp_Email.setCellValueFactory(new PropertyValueFactory<Employee , String>("email"));
                Emp_Password.setCellValueFactory(new PropertyValueFactory<Employee , String>("password"));
                Emp_Role.setCellValueFactory(new PropertyValueFactory<Employee , String>("role"));
                Emp_Depart.setCellValueFactory(new PropertyValueFactory<Employee , String>("department"));
                Emp_Type.setCellValueFactory(new PropertyValueFactory<Employee , String>("emp_type"));

                EmployeeTableView.setItems(EmployeeObservableList);

            }else{
                throw new Exception("moseeeba el Employees msh mwgodeen");
            }


        }catch (Exception e){

            System.out.println(e);
        }

    }

    public void addEmployee(ActionEvent event){

        int Emp_Id          = Integer.parseInt(employee_id.getText());
        String Emp_Name     = emp_name.getText();
        String Emp_Email    = Emp_email.getText();
        String Emp_Password = emp_password.getText();
        int    Emp_Team     = Integer.parseInt(emp_team.getText());
        String Emp_Type     = emp_type.getText();
        String Emp_Role     = emp_role.getText();


        try {
            if(Utility.UserSingle.getInstance().admin.addEmployee(Emp_Id , Emp_Email , Emp_Name , Emp_Password , Emp_Role ,Emp_Type , Emp_Team)){

                System.out.println("Employee has been Added Successfully");
                switchScenes(event ,"EmployeesView.fxml");
            }else{
                throw new Exception("el72oooona el Employee md5lsh ");
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
            switchScenes(event,"MainPage.fxml");
        }

        public void switchToProjects(ActionEvent event) throws IOException {
            switchScenes(event,"Projects.fxml");
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

    public void deleteEmployee(ActionEvent actionEvent) throws IOException {
        try{
            int emp_id = Integer.parseInt(IdTextFieldDel.getText());
            Utility.UserSingle.getInstance().admin.deleteEmployee((emp_id));
            switchScenes(actionEvent, "EmployeesView.fxml");
        }catch(IOException e){
            System.out.print("kosom 7ayty");
        }

    }

    public void updateEmployee(ActionEvent actionEvent) throws IOException {
        try {
            int emp_id = Integer.parseInt(IdTextFieldUp.getText());
            String emp_type = IdTextFieldUp1.getText();
            Utility.UserSingle.getInstance().admin.editEmployeeType(emp_id, emp_type);
            switchScenes(actionEvent, "EmployeesView.fxml");
        }catch(IOException e){
            System.out.print("kosom 7ayty");
        }

    }

}



