package com.example.project_pl2;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;


public class login_controller {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label err_message_label;
    @FXML
    private TextField emailtextfield;
    @FXML
    private PasswordField passwordtextfield;

    private boolean Success;


    @FXML
    public void LoginButtonAction(ActionEvent event) throws IOException, SQLException {
        String email = emailtextfield.getText();
        String password = passwordtextfield.getText();
        if (email.isEmpty() || password.isEmpty()) {
            err_message_label.setText("Email And Password Are Required Fields Cannot Be Left Empty\n");
            err_message_label.setVisible(true);
            return;
        }
        if (Employee.login(email,password)) {
           System.out.print("Login successfully !\n" + "Name is : " + Utility.UserSingle.getInstance().emp.name);

           App.setRoot("EmployeesView.fxml");
        } else {
            err_message_label.setText("Invalid Username or Password\n");
            err_message_label.setVisible(true);
        }
    }

    private void employeeLogIn(ActionEvent event){

    }

    public void switchToAdmin(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Admin_Dashboard.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}