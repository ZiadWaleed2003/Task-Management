package com.example.project_pl2;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.IOException;


public class login_controller {
    @FXML
    private Label err_message_label;
    @FXML
    private TextField emailtextfield;
    @FXML
    private PasswordField passwordtextfield;

    @FXML
    public void LoginButtonAction(ActionEvent event) throws IOException {
        String email = emailtextfield.getText();
        String password = passwordtextfield.getText();
        if (email.isEmpty() || password.isEmpty()) {
            err_message_label.setText("Email And Password Are Required Fields Cannot Be Left Empty\n");
            err_message_label.setVisible(true);
            return;
        }
        if (login(email, password)) {
            //login successfull
            //TODO implement if login successful after checking DB
        } else {
            err_message_label.setText("Invalid Username or Password\n");
            err_message_label.setVisible(true);
        }
    }

    private boolean login(String email, String password) {
        //TODO implement logic to check if username and pass exist in DB
        return true;
    }
}