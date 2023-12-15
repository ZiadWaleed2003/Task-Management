package com.example.project_pl2;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.concurrent.Task;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class Project {
    //IDs:
    private SimpleIntegerProperty project_id;
    private SimpleIntegerProperty team_id;
    //Progress
    private SimpleStringProperty current_status;

    private SimpleStringProperty project_title;
    private SimpleStringProperty project_description;



    Project(int project_id, String project_title , String project_description , int team_id, String current_status){
        this.project_id          = new SimpleIntegerProperty(project_id);
        this.project_title       = new SimpleStringProperty(project_title);
        this.project_description = new SimpleStringProperty(project_description);
        this.team_id             = new SimpleIntegerProperty(team_id);
        this.current_status      = new SimpleStringProperty(current_status);
    }

    public Project(ResultSet res) throws SQLException {

        this.project_description = new SimpleStringProperty(res.getString("Project_desc"));
        this.project_title       = new SimpleStringProperty(res.getString("Project_Title"));
        this.project_id          = new SimpleIntegerProperty(res.getInt("Project_Id"));
        this.team_id             = new SimpleIntegerProperty(res.getInt("Assigned_To"));
        this.current_status      = new SimpleStringProperty(res.getString("Progress_status"));


    }



    public SimpleStringProperty project_descriptionProperty() {
        return project_description;
    }



    public SimpleStringProperty current_statusProperty() {
        return current_status;
    }

    public void setCurrent_status(String current_status) {
        this.current_status.set(current_status);
    }


    public SimpleIntegerProperty team_idProperty() {
        return team_id;
    }



    public SimpleIntegerProperty project_idProperty() {
        return project_id;
    }
    public SimpleStringProperty project_titleProperty() {
        return project_title;
    }

    public void setProject_title(String project_title) {
        this.project_title.set(project_title);
    }
}

    //method generate return query data required for db
    //make result set construct to return query


