package com.example.project_pl2;

import java.sql.*;
import java.util.*;


public class Employee extends Person{


    public enum EmpType {
        LEADER , MEMBER
    }

    private EmpType emp_type;

    private List<String> task_id;

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
        Object[] res = new Object[9];
        if (set.isBeforeFirst()){
            set.next();
            for (int i = 0; i<9; i++){
                res[i] = set.getObject(i);
            }
        }
        super.name = (String) res[2];
        super.email = (String) res[1];
        super.password = (String) res[3];
        super.id = (int) res[0];
        this.emp_type = (EmpType) res[6];
        this.team_id = (int) res[8];
        this.time_card = (double) res[9];
        this.request_id = (int) res[7];
        this.role = (String) res[5];

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

    public List<String> getTask_id() {
        return task_id;
    }

    public void setTask_id(List<String> task_id) {
        this.task_id = task_id;
    }

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
