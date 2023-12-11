package com.example.project_pl2;

import java.sql.*;
import java.util.*;


public class Employee extends Person{


    public enum EmpType {
        LEADER , MEMBER
    }

    private EmpType emp_type;

    private ArrayList<IndivTask> tasks_list;

    private ArrayList<Request> requests_list;


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
        super.name = set.getNString(2);
        super.email = set.getNString(1);
        super.password = set.getNString(3);
        super.id = set.getInt(0);
        this.emp_type = EmpType.values()[set.getInt(6)]; //TODO: object -> enum conversion
        this.team_id = set.getInt(8);
        this.time_card = set.getDouble(9);
        this.request_id = set.getInt(7);
        this.role = set.getNString(5);

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
//          System.out.println("Email : " + this.email + "\nPassword is : " + this.password);
        }else{
            System.out.println("Error");
            return false;
        }

        return false;
    }

    public ArrayList<IndivTask> constructTasksList() throws SQLException{
        ArrayList<IndivTask> result =  new ArrayList<IndivTask>();
        String query = "SELECT * FROM plproject.task WHERE Assigned_To = ?";
        Integer[] args = {this.id};
        try {
            ResultSet res = CRUD2.readDbDynamic(query, args);
            if (res.isBeforeFirst()) {
                while(res.next()){
//                    result.add(new IndivTask(res));
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
//                        result.add(new Project(res));
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
}
