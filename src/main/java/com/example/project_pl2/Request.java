package com.example.project_pl2;
import javafx.util.Pair;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Request {
    private String request_type;
    private String request_description;
    private int request_id;
    private int requested_by;
    private boolean request_status;

    public Request(){
    }

    public Request(ResultSet resultSet) throws SQLException {

        this.request_id = resultSet.getInt("Request_Id");
        this.requested_by = resultSet.getInt("Request_By");
        this.request_description = resultSet.getString("Request_Desc");
        this.request_status = resultSet.getBoolean("Request_Status");
        this.request_type = resultSet.getString("Request_Type");

    }

/*
    public Request(int request_by, String request_description, String request_type) {
        this.requested_by =
        this.request_description = request_description;
        this.request_type        = request_type;
        this.request_status      = false;
    }
*/

    public void set_id(int request_id){
        this.request_id = request_id;
    }

    public int get_id(){
        return request_id;
    }

    public void set_description(String request_description){
        this.request_description = request_description;
    }

    public String get_description(){
        return request_description;
    }

    public void set_type(String request_type){
        this.request_type = request_type;
    }

    public String get_type() {
        return request_type;
    }

    public void set_status(boolean request_status){
        this.request_status = request_status;
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

    public Pair<Boolean , Integer> SendRequest(String request_description, String request_type ) {
        String query = "insert into request (Request_By, Request_Desc, Request_Status, Request_Type,) " +
                "values(?, ?, ?, ?)";
        Object[] args = {Utility.UserSingle.getInstance().emp,request_description, request_type,false};

        Pair<Boolean , Integer> check_insert = CRUD2.updateDbDynamic(query);

        return check_insert;
    }
    public boolean checkRequest(int request_id) throws SQLException {
       return get_status();
    }

}

