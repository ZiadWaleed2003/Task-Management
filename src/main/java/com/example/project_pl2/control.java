//package com.example.project_pl2;
//
//import javafx.event.ActionEvent;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Node;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Alert;
//import javafx.scene.control.ButtonType;
//import javafx.stage.Stage;
//
//import java.io.IOException;
//
//public class control {
//    private Scene scene;
//    private Stage stage;
//    private Parent root;
//
//
//
//
//    public void Logout(ActionEvent event)throws IOException{
//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//        alert.setTitle("Logout");
//        alert.setHeaderText("You Are About To Logout");
//        alert.setContentText("You Will Be Redirected To the Main Page");
//        if (alert.showAndWait().get()== ButtonType.OK)
//            switchToMainPage(event);
//    }
//
//    public void switchTorequests(ActionEvent event) throws IOException {
//        switchScenes(event,"Admin_RequestsView.fxml");
//    }
//
//
//    public void switchToMainPage(ActionEvent event) throws IOException {
//        switchScenes(event,"MainPage.fxml");
//
//    }
//    public void switchToemployees(ActionEvent event) throws IOException {
//        switchScenes(event,"Admin_EmployeesView.fxml");
//    }
//    public void switchToprojects(ActionEvent event) throws IOException {
//        switchScenes(event,"Admin_ProjectsView.fxml");
//    }
//    public void switchScenes(ActionEvent event , String fxml) throws IOException {
//
//        root = FXMLLoader.load(getClass().getResource(fxml));
//        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//
//    }
//}
