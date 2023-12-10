package com.example.project_pl2;

import javafx.util.Pair;

import java.util.Date;
public class IndivTask {

    protected int id;
    protected String name;
    protected String description;
    protected Utility.CompletionStatus status;
    protected int assigned_to;
    protected int project;
    protected Priority priority;
    protected Date start_date;
    protected Date due_date;

    public enum Priority {
        LOW, MEDIUM, HIGH
    }
    public IndivTask(){
        this.id = 0;
        this.name = null;
        this.description = null;
        this.status = Utility.CompletionStatus.valueOf("PENDING");
        this.assigned_to = 0;
        this.project = 0;
        this.priority = Priority.valueOf("LOW");
        this.start_date = new Date();
        this.due_date = null;
    }
    public IndivTask(int id, String name, String description, Utility.CompletionStatus status, int assigned_to, int project, Priority priority, Date start_date, Date due_date) {
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

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Utility.CompletionStatus getStatus() {
        return status;
    }

    public void setStatus(Utility.CompletionStatus status) {
        this.status = status;
    }

    public int getAssignedTo() {
        return assigned_to;
    }

    public void setAssignedTo(int assigned_to) {
        this.assigned_to = assigned_to;
    }

    public int getProject() {
        return project;
    }

    public void setProject(int project) {
        this.project = project;
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
    // after getter and setter
    // TODo (i need to know the main function that related to IndivTask )
    // TODO make (ADD, UPDATE, DELETE) to task




    //TODO: use overloaded updatedbdynamic w/ args for better code readability
    public boolean addTask(){
        String addTaskQuery = String.format("INSERT INTO task (Task_Id, Assigned_To, Due_date, Priority, Project_Id, Start_Date, Task_Desc, Task_Name, Task_Status) " +
                        "VALUES (%d, %d, '%s', '%s', %d, '%s', '%s', '%s', '%s');",
                getId(), getAssignedTo(), getDueDate(), getPriority(), getProject(), getStartDate(), getDescription(), getName(), getStatus());

        Pair<Boolean , Integer> res = CRUD2.updateDbDynamic(addTaskQuery);

        return res.getKey();
    }

    public boolean updateTask(int required_task_id){
        String updateTaskQuery = String.format("UPDATE task " +
                        "SET " +
                        "    Assigned_To = %d, " +
                        "    Due_date = '%s' " +
                        "    Priority = '%s', " +
                        "    Project_Id = %d, " +
                        "    Start_Date = '%s', " +
                        "    Task_Desc = '%s', " +
                        "    Task_Name = '%s', " +
                        "    Task_Status = '%s', " +
                        "WHERE Task_Id = %d;",
                getAssignedTo(), getDueDate(), getPriority(), getProject(), getStartDate(), getDescription(), getName(), getStatus(), required_task_id);

        Pair<Boolean , Integer> res = CRUD2.updateDbDynamic(updateTaskQuery);

        return res.getKey();
    }

    public boolean deleteTask(int required_task_id){
        String deleteTaskQuery = "DELETE FROM task " +
                "WHERE id = "+required_task_id+";";

        Pair<Boolean , Integer> res = CRUD2.updateDbDynamic(deleteTaskQuery);

        return res.getKey();
    }

    // TODO [Zeyad Hussam] make (readTask) to task
}

