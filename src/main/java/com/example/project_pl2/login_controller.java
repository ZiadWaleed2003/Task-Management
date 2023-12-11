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

    Employee MyEmployee = new Employee();
    private boolean Success;


    @FXML
    public void LoginButtonAction(ActionEvent event) throws IOException {
        String email = emailtextfield.getText();
        String password = passwordtextfield.getText();
        if (email.isEmpty() || password.isEmpty()) {
            err_message_label.setText("Email And Password Are Required Fields Cannot Be Left Empty\n");
            err_message_label.setVisible(true);
            return;
        }
        if ((login(email, password))) {
            switchToEmployee(event);
            //login successfull
            //TODO implement if login successful after checking DB
        } else {
            err_message_label.setText("Invalid Username or Password\n");
            err_message_label.setVisible(true);
        }
    }



        private boolean login(String email, String password) {
        boolean confirm = false;
        //TODO implement logic to check if username and pass exist in DB
        if (email.equals("Zyad") &&  password.equals("123")){
           confirm = true;}
    return confirm;}



    public void switchToAdmin(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Admin_Dashboard.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void switchToEmployee(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("TeamLeader_Dashboard.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}