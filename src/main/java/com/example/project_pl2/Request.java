package com.example.project_pl2;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Request {
    private String request_type;
    private String request_description;
    private int request_id;
    private boolean request_status;

    public Request(){
    }

    public Request(ResultSet resultSet) throws SQLException {

        this.request_id = resultSet.getInt("Request_Id");
        this.request_description = resultSet.getNString("Request_Desc");
        this.request_status = resultSet.getBoolean("Request_Status");
        this.request_type        = resultSet.getNString("Request_Type");

    }

    public Request(int request_id, String request_description, String request_type) {
        this.request_id          = request_id;
        this.request_description = request_description;
        this.request_type        = request_type;
        this.request_status      = false;
    }

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

    public boolean SendRequest( int request_id , String request_description, String request_type ) {
        this.request_id          = request_id;
        this.request_description = request_description;
        this.request_type        = request_type;
        this.request_status      = false;

        String query = "insert into request (Request_Type,Request_Desc,Request_Status) " +
                "values(" + request_type + "," + request_description + "," + false + ")";

        boolean check_insert = CRUD2.updateDbDynamic(query).getKey();

        return check_insert;
    }
    public boolean checkRequest(int request_id) throws SQLException {
       return get_status();
    }

}

