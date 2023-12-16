package com.example.project_pl2;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.util.Pair;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
public class IndivTask {

    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty description;
    // [TODO 12]Change Utility to variable have property and change result set , add setter and getter
    private SimpleStringProperty status;
    private SimpleIntegerProperty assigned_to;
    private SimpleIntegerProperty project;
    // [TODO 12]Change Priority to variable have property and change result set , add setter and getter
    private SimpleStringProperty priority;
    // [TODO 12]Change Date to variable have property and change result set , add setter and getter
    private SimpleStringProperty start_date;
    // [TODO 12]Change Date to variable have property and change result set , add setter and getter
    private SimpleStringProperty due_date;




 // [TODO 12]Change Contructor to variable have property or change in employee to take variable direct not from constructor

    public IndivTask(ResultSet res) throws SQLException{

        Utility.CompletionStatus stat = Utility.CompletionStatus.values()[res.getInt("task_Status")];

        this.id = new SimpleIntegerProperty(res.getInt("Task_Id"));
        this.name = new SimpleStringProperty(res.getNString("Task_Name"));
        this.description = new SimpleStringProperty(res.getNString("Task_Desc"));

        this.status      = new SimpleStringProperty(stat.name());
        this.assigned_to = new SimpleIntegerProperty(res.getInt(("Assigned_To")));
        this.project = new SimpleIntegerProperty(res.getInt("Project_Id"));

        this.priority = new SimpleStringProperty(res.getString("Priority"));


        this.start_date = new SimpleStringProperty(res.getString("Start_Date"));
        this.due_date = new SimpleStringProperty (res.getString("Due_Date"));
    }




    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public int getAssigned_to() {
        return assigned_to.get();
    }

    public SimpleIntegerProperty assigned_toProperty() {
        return assigned_to;
    }

    public void setAssigned_to(int assigned_to) {
        this.assigned_to.set(assigned_to);
    }

    public int getProject() {
        return project.get();
    }

    public SimpleIntegerProperty projectProperty() {
        return project;
    }

    public void setProject(int project) {
        this.project.set(project);
    }

    public String getPriority() {
        return priority.get();
    }

    public SimpleStringProperty priorityProperty() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority.set(priority);
    }
    public String getStart_date() {
        return start_date.get();
    }

    public SimpleStringProperty start_dateProperty() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date.set(start_date);
    }

    public String getDue_date() {
        return due_date.get();
    }

    public SimpleStringProperty due_dateProperty() {
        return due_date;
    }

    public void setDue_date(String due_date) {
        this.due_date.set(due_date);
    }

}

