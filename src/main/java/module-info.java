module com.example.project_pl2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.project_pl2 to javafx.fxml;
    exports com.example.project_pl2;
}