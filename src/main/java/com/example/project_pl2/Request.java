package com.example.project_pl2;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Request {
    private String requset_type;
    private String requset_description;
    private int requset_id;
    private boolean requset_status;

    public Request(){
    }

    public Request(int requset_id,String requset_description,String requset_type) {
        this.requset_id=requset_id;
        this.requset_description=requset_description;
        this.requset_type=requset_type;
        this.requset_status=false;
    }

    public void set_id(int requset_id){
        this.requset_id=requset_id;
    }

    public int get_id(){
        return requset_id;
    }

    public void set_description(String requset_description){
        this.requset_description=requset_description;
    }

    public String get_description(){
        return requset_description;
    }

    public void set_type(String requset_type){
        this.requset_type=requset_type;
    }

    public String get_type() {
        return requset_type;
    }

    public void set_status(boolean requset_status){
        this.requset_status=requset_status;
    }

    public boolean get_status() throws SQLException {
        String query= "SELECT Requset_Status from requset where Requset_Id = ? ";

        Object [] args = {this.requset_id};

        ResultSet result = CRUD2.readDbDynamic(query , args);

        if(result.isBeforeFirst()){
            result.next();
            return true;
        }
        else
            return false;
    }

    public boolean SendRequset( int requset_id , String requset_description, String requset_type ) {
        this.requset_id = requset_id;
        this.requset_description = requset_description;
        this.requset_type = requset_type;
        this.requset_status=false;

        boolean checkinsert = CRUD2.updateDbDynamic("insert into requset (Requset_Type,Requset_Desc,Requset_Status ) " +
                "values( "+ requset_type + "," + requset_description+","+requset_status+")" ).getKey();

        return checkinsert;
    }
    public boolean checkRequset(int requset_id) throws SQLException {
        if(get_status())
        {
            return true;
        }
        else {
            return false;
        }
    }

}

