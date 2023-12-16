package com.example.project_pl2;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.util.Pair;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Request {
    private SimpleStringProperty request_type;
    private SimpleStringProperty request_description;
    private SimpleIntegerProperty request_id;
    private SimpleIntegerProperty requested_by;
    private SimpleBooleanProperty request_status;


    public Request(ResultSet resultSet) throws SQLException {

        int req_stat = resultSet.getInt("Request_Status");
        boolean status;

        if(req_stat != 0){
            status = true;
        }else {
            status = false;
        }


        this.request_id          = new SimpleIntegerProperty(resultSet.getInt("Request_Id"));
        this.requested_by        = new SimpleIntegerProperty(resultSet.getInt("Requested_By"));
        this.request_description = new SimpleStringProperty(resultSet.getString("Request_Desc"));
        this.request_status      = new SimpleBooleanProperty(status);
        this.request_type        = new SimpleStringProperty(resultSet.getString("Request_Type"));

    }


    public boolean get_status() throws SQLException {
        String query= "SELECT Request_Status from request where Request_Id = ? ";

        Object [] args = {this.request_id};

        ResultSet result = CRUD2.readDbDynamic(query , args);

        if(result.isBeforeFirst()){
            result.next();
            return true;
        }
        else
            return false;
    }




    public int getRequested_by() {
        return requested_by.get();
    }

    public SimpleIntegerProperty requested_byProperty() {
        return requested_by;
    }

    public void setRequested_by(int requested_by) {
        this.requested_by.set(requested_by);
    }



    public int getRequest_Id() {
        return request_id.get();
    }

    public SimpleIntegerProperty request_idProperty() {
        return request_id;
    }

    public void setRequest_Id(int request_id) {
        this.requested_by.set(request_id);
    }

    public boolean isRequest_status() {
        return request_status.get();
    }

    public SimpleBooleanProperty request_statusProperty() {
        return request_status;
    }

    public void setRequest_status(boolean request_status) {
        this.request_status.set(request_status);
    }

    public String getRequest_description() {
        return request_description.get();
    }

    public SimpleStringProperty request_descriptionProperty() {
        return request_description;
    }

    public void setRequest_description(String request_description) {
        this.request_description.set(request_description);
    }

    public String getRequest_type() { return request_type.get();}

    public SimpleStringProperty request_typeProperty() {
        return request_type;
    }

    public void setRequest_type(String request_type) {
        this.request_type.set(request_type);
    }
}

