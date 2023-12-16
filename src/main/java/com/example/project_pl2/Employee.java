package com.example.project_pl2;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.util.Pair;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.util.Date;


public class Employee extends Person{


    // [TODO 12]Change Enum to variable have property and change result set , add setter and getter
    public enum EmpType {
        LEADER , MEMBER
    }

    private SimpleStringProperty emp_type;

    private SimpleIntegerProperty team_id;

    private SimpleDoubleProperty time_card;

    private SimpleIntegerProperty request_id;

    private SimpleStringProperty role;

    private SimpleStringProperty department;

    public Employee(){}

    //[TODO 12]Change Contructor to variable have property or change in employee to take variable direct not from constructor

//    public Employee(String name, String email, String password, int id, EmpType emp_type, int team_id, String role) {
//        super(name, email, password, id);
//        this.emp_type = emp_type;
//        this.team_id = team_id;
//        this.role = role;
//    }

    public Employee(ResultSet set) throws SQLException{

        EmpType type = EmpType.values()[set.getInt("Emp_Type")];


        super.name          = new SimpleStringProperty(set.getString("Emp_Name"));
        super.email         = new SimpleStringProperty(set.getString("Emp_Email"));
        super.password      = new SimpleStringProperty(set.getString("Emp_Password"));
        super.id            = new SimpleIntegerProperty(set.getInt("Emp_Id"));
        this.emp_type       = new SimpleStringProperty(type.name());
        this.team_id        = new SimpleIntegerProperty(set.getInt("Team_Id"));
        this.time_card      = new SimpleDoubleProperty(set.getDouble("Time_Card"));
        this.request_id     = new SimpleIntegerProperty(set.getInt("Request_Id"));
        this.role           = new SimpleStringProperty(set.getString("Emp_Role"));
        this.department     = new SimpleStringProperty(set.getString("Department"));

        }


    public SimpleIntegerProperty team_idProperty(){return team_id;}
    public int getTeam_Id() {return team_id.get();}
    @Override
    public String getName() {return name.get();}
    @Override
    public SimpleStringProperty nameProperty(){return name;}
    @Override
    public void setName(String name){this.name.set(name);}
    @Override
    public String getEmail(){return email.get();}
    @Override
    public SimpleStringProperty emailProperty(){return email;}
    @Override
    public void setEmail(String email){this.email.set(email);}
    @Override
    public String getPassword(){return password.get();}
    @Override
    public SimpleStringProperty passwordProperty(){return password;}
    @Override
    public void setPassword(String password){this.password.set(password);}
    @Override
    public int getId() {return id.get();}
    @Override
    public SimpleIntegerProperty idProperty(){return id;}
    @Override
    public void setId(int id){this.id.set(id);}

    public String getDepartment() {
        return department.get();
    }

    public SimpleStringProperty departmentProperty() {
        return department;
    }

    public void setDepartment(String department) {
        this.department.set(department);
    }


    public double getTime_card() {
        return time_card.get();
    }

    public SimpleDoubleProperty time_cardProperty() {
        return time_card;
    }

    public void setTime_card(double time_card) {
        this.time_card.set(time_card);
    }

    public int getRequest_id() {
        return request_id.get();
    }

    public SimpleIntegerProperty request_idProperty() {
        return request_id;
    }

    public void setRequest_id(int request_id) {
        this.request_id.set(request_id);
    }
    public String getRole() {
        return role.get();
    }

    public SimpleStringProperty roleProperty() {
        return role;
    }

    public void setRole(String role) {
        this.role.set(role);
    }

    public void setEmp_type(String e_type){

        this.emp_type.set(e_type.toUpperCase());
    }
    public String getEmp_type(){return this.emp_type.get();}

    public SimpleStringProperty emp_typeProperty() {
        return emp_type;
    }



    public static boolean login(String email, String password) throws SQLException {

        String query = "SELECT * FROM Employee inner join Team on Employee.Team_Id = Team.Team_Id WHERE Emp_Email = ? AND Emp_Password = ?";

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
        Object[] args = new Object[1];

        if (this.emp_type.get().equals("LEADER")){

            query = "SELECT * FROM plproject.task WHERE Project_Id IN " +
                    "(SELECT Project_Id FROM plproject.project WHERE Assigned_To = ?)";
            args[0] = team_id.get();

        }else{
            query = "SELECT * FROM plproject.task WHERE Assigned_To = ?";
            args[0] = this.id.get();
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
        Integer[] args = {Utility.UserSingle.getInstance().emp.getId()};

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

        if (Utility.UserSingle.getInstance().emp.emp_type.get().equals("LEADER")){

            ArrayList<Project> result = new ArrayList<Project>();
            String query = "SELECT * FROM plproject.project WHERE Assigned_To = ?";
            Integer[] args = {Utility.UserSingle.getInstance().emp.team_id.get()};
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



    public boolean addTask(String name, String description, Utility.CompletionStatus status, int project,
                           String priority, LocalDate start_date, LocalDate due_date) throws SQLException{
        if(this.emp_type.get().equals("LEADER")){

            String addTaskQuery = "INSERT INTO task (Due_date, Priority, Project_Id, Start_Date, " +
                    "Task_Desc, Task_Name, Task_Status) VALUES (?, ?, ?, ? ,? ,? ,?);";


            Object[] args = {due_date , priority , project , start_date , description , name ,
                    status.ordinal() };

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


    public ArrayList<Employee> ShowTeam () throws SQLException {

        String query     = "SELECT * FROM Employee INNER JOIN Team ON Employee.Team_Id = Team.Team_Id WHERE Employee.Team_Id = ?";

        Object [] args   = {this.team_id.get()};

        ArrayList<Employee> Employees = new ArrayList<Employee>();

        ResultSet result = CRUD2.readDbDynamic(query , args);

        if(result.isBeforeFirst()){

            while(result.next()){

                Employee emp = new Employee(result);

                Employees.add(emp);


            }
        }

        return Employees;
    }


    public boolean deleteTask(int required_task_id){

        String deleteTaskQuery = "DELETE FROM task WHERE Task_Id = ?";
        Object [] args = {required_task_id};

        Pair<Boolean , Integer> res = CRUD2.updateDbDynamic(deleteTaskQuery , args);

        return res.getKey();
    }
    public boolean updateProjStatus(int proj_id, int status ){
        String query = "UPDATE plproject.project SET Progress_status = ? WHERE Project_Id = ?";
        Object [] args = {status, proj_id};
        boolean result = CRUD2.updateDbDynamic( query , args).getKey();
        return result;
    }
}
