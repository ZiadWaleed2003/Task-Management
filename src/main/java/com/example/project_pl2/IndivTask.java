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
    private Utility.CompletionStatus status;
    private SimpleIntegerProperty assigned_to;
    private SimpleIntegerProperty project;
    // [TODO 12]Change Priority to variable have property and change result set , add setter and getter
    private Priority priority;
    // [TODO 12]Change Date to variable have property and change result set , add setter and getter
    private Date start_date;
    // [TODO 12]Change Date to variable have property and change result set , add setter and getter
    private Date due_date;


    public enum Priority {
        LOW, MEDIUM, HIGH
    }
 /*   public IndivTask(){
        this.id = 0;
        this.name = null;
        this.description = null;
        this.status = Utility.CompletionStatus.valueOf("PENDING");
        this.assigned_to = 0;
        this.project = 0;
        this.priority = Priority.valueOf("LOW");
        this.start_date = new Date();
        this.due_date = null;
    }*/
 // [TODO 12]Change Contructor to variable have property or change in employee to take variable direct not from constructor
    public IndivTask(int id, String name, String description, Utility.CompletionStatus status, int assigned_to,
                     int project, Priority priority, Date start_date, Date due_date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.assigned_to = assigned_to;
        this.project = project;
        this.priority = priority;
        this.start_date = start_date;
        this.due_date = due_date;
    }

    public IndivTask(ResultSet res) throws SQLException{
        this.id = new SimpleIntegerProperty(res.getInt("Task_Id"));
        this.name = new SimpleStringProperty(res.getNString("Task_Name"));
        this.description = new SimpleStringProperty(res.getNString("Task_Desc"));
        this.status = Utility.CompletionStatus.values()[res.getInt("task_Status")];
        this.assigned_to = new SimpleIntegerProperty(res.getInt(("Assigned_To")));
        this.project = new SimpleIntegerProperty(res.getInt("Project_Id"));
        this.priority = Priority.values()[res.getInt("Priority)")];
        this.start_date = res.getDate("Start_Date");
        this.due_date = res.getDate("Due_Date");
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


    public Utility.CompletionStatus getStatus() {
        return status;
    }

    public void setStatus(Utility.CompletionStatus status) {
        this.status = status;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Date getStartDate() {
        return start_date;
    }

    public void setStartDate(Date start_date) {
        this.start_date = start_date;
    }

    public Date getDueDate() {
        return due_date;
    }

    public void setDueDate(Date due_date) {
        this.due_date = due_date;
    }



    //TODO: use overloaded updatedbdynamic w/ args for better code readability

    public boolean deleteTask(int required_task_id){
        String deleteTaskQuery = "DELETE FROM task " +
                "WHERE id = "+required_task_id+";";

        Pair<Boolean , Integer> res = CRUD2.updateDbDynamic(deleteTaskQuery);

        return res.getKey();
    }

    // TODO [Zeyad Hussam] make (readTask) to task

    public ResultSet readTask(){

        int emp_id = Utility.UserSingle.getInstance().emp.id;

        String query = "SELECT * FROM plproject.task WHERE Assigned_To = " + emp_id;

        ResultSet result = CRUD2.readDbDynamic(query);

        try {
            if(result.isBeforeFirst()){
                result.next();

                return result;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}

