package com.example.project_pl2;

import javafx.util.Pair;

import java.sql.*;
import java.util.*;
import java.util.Date;


public class Employee extends Person{


    public enum EmpType {
        LEADER , MEMBER
    }

    private EmpType emp_type;

    private int team_id;

    private double time_card;

    private int request_id;

    private String role;

    public Employee(){}

    public Employee(String name, String email, String password, int id, EmpType emp_type, int team_id, String role) {
        super(name, email, password, id);
        this.emp_type = emp_type;
        this.team_id = team_id;
        this.role = role;
    }

    public Employee(ResultSet set) throws SQLException{
        super.name = set.getString("Emp_Name");
        super.email = set.getString("Emp_Email");
        super.password = set.getString("Emp_Password");
        super.id = set.getInt("Emp_Id");
        this.emp_type = EmpType.values()[set.getInt("Emp_Type")]; //TODO: object -> enum conversion
        this.team_id = set.getInt("Team_Id");
        this.time_card = set.getDouble("Time_Card");
        this.request_id = set.getInt("Request_Id");
        this.role = set.getString("Emp_Role");

        }

    public static boolean login(String email, String password) throws SQLException {

        String query = "SELECT * FROM Employee WHERE Emp_Email = ? AND Emp_Password = ?";

        String[] arguments = {email, password};

        ResultSet result = CRUD2.readDbDynamic(query,arguments);

        if(result.isBeforeFirst()){
            result.next();
            Employee emp = new Employee(result);
            Utility.UserSingle CurrentUser = Utility.UserSingle.getInstance();
            CurrentUser.emp = emp;
            return true;
//          System.out.println("Email : " + this.email + "\nPassword is : " + this.password);
        }else{
            System.out.println("Error");
            return false;
        }
    }

    public ArrayList<IndivTask> constructTasksList() throws SQLException{
        ArrayList<IndivTask> result =  new ArrayList<IndivTask>();
        String query;
        Object[] args = {};
        if (this.emp_type == EmpType.LEADER){

            String Query1 = "SELECT Project_Id FROM plproject.project WHERE Assigned_To = ?";
            Object[] args1 = {this.team_id};
            ResultSet req = CRUD2.readDbDynamic(Query1, args);
            int team_id = 0;
            if (req.isBeforeFirst()){
                req.next();
                team_id = req.getInt("Assigned_To");
            }
            query = "SELECT * FROM plproject.task WHERE Project_Id = ?";
            args[0] = team_id;
        }else{
            query = "SELECT * FROM plproject.task WHERE Assigned_To = ?";
            args[0] = this.id;
        }
        try {
            ResultSet res = CRUD2.readDbDynamic(query, args);
            if (res.isBeforeFirst()) {
                while(res.next()){
                    result.add(new IndivTask(res));
                }
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return result;
    }
    public ArrayList<Request> retrieveAllRequests() throws SQLException{
        ArrayList<Request> result =  new ArrayList<Request>();
        String query = "SELECT * FROM plproject.request WHERE Request_by = ?";
        Integer[] args = {this.id};
        try {
            ResultSet res = CRUD2.readDbDynamic(query, args);
            if (res.isBeforeFirst()) {
                while(res.next()){
                    result.add(new Request(res));
                }
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return result;
    }
    public ArrayList<Project> retrieveAllProjects() throws SQLException{

        if (Utility.UserSingle.getInstance().emp.emp_type == EmpType.LEADER){

            ArrayList<Project> result = new ArrayList<Project>();
            String query = "SELECT * FROM plproject.project WHERE Assigned_To = ?";
            Integer[] args = {this.id};
            try {
                ResultSet res = CRUD2.readDbDynamic(query, args);
                if (res.isBeforeFirst()) {
                    while (res.next()) {
                        result.add(new Project(res));
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return result;
        }
        else {
            return null;
        }
    }
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public double getTime_card() {
        return time_card;
    }

    public void setTime_card(double time_card) {
        this.time_card = time_card;
    }

    public int getTeam_id() {
        return team_id;
    }

    public void setTeam_id(int team_id) {
        this.team_id = team_id;
    }

 /*   public List<String> getTask_id() {
        return task_id;
    }
*/
/*    public void setTask_id(List<String> task_id) {
        this.task_id = task_id;
    }*/

    public int getRequest_id() {
        return request_id;
    }

    public void setRequest_id(int request_id) {
        this.request_id = request_id;
    }


    public void setEmptype(String e_type){

        if(e_type.equals("TeamLeader")){
            this.emp_type = EmpType.LEADER;
        }else{
            this.emp_type = EmpType.MEMBER;
        }
    }

    public boolean addTask(int id, String name, String description, Utility.CompletionStatus status,
                           int assigned_to, int project, IndivTask.Priority priority, Date start_date, Date due_date){
        if(this.emp_type == EmpType.LEADER){
            IndivTask temp = new IndivTask(id, name, description, status,
                    assigned_to, project, priority, start_date, due_date);
            String addTaskQuery = "INSERT INTO task (Task_Id, Assigned_To, Due_date, Priority, Project_Id, Start_Date, " +
                    "Task_Desc, Task_Name, Task_Status) VALUES (?, ?, ?, ? ,? ,? ,?, ?, ?);";
            Object[] args = {temp.getId(), temp.getAssignedTo(), temp.getDueDate(), temp.getPriority(), temp.getProject(),
                    temp.getStartDate(), temp.getDescription(), temp.getName(), temp.getStatus()};

            Pair<Boolean, Integer> res = CRUD2.updateDbDynamic(addTaskQuery, args);

            return res.getKey();
        }else{
            return false;
        }
    }
    public boolean reassignTask(int task_id, int emp_id ){
        String query = "UPDATE plproject.task SET Assigned_To = ? WHERE Task_Id = ?";
        Object [] args = {emp_id, task_id};
        boolean result = CRUD2.updateDbDynamic( query , args).getKey();
        return result;
    }
    public boolean updateTaskStatus(int task_id, int status ){
        String query = "UPDATE plproject.task SET Task_Status = ? WHERE Task_Id = ?";
        Object [] args = {status, task_id};
        boolean result = CRUD2.updateDbDynamic( query , args).getKey();
        return result;
    }

    public EmpType getEmp_type(){
        return this.emp_type;
    }



}
