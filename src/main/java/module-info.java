module com.example.project_pl2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;


    opens com.example.project_pl2 to javafx.fxml;
    exports com.example.project_pl2;
}