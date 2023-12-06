package com.example.project_pl2;

import DataBase.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class Employee extends Person{


    private enum emp_type {
        TeamLeader , TeamMember
    }

    private emp_type Emp_type;

    private List<String> task_id;

    private int team_id;

    private double time_card;

    private List<String> request_id;

    private String department;

    private String role;


    @Override
    public boolean login(String email, String password) {

        String Query = "SELECT Emp_Email , Emp_Password FROM Employee WHERE Emp_Password = '`password`' AND Emp_Email = '`email`' ";

        DbConnection connection = new DbConnection();


        try {
             Connection connection1      = connection.getConnection();
             PreparedStatement statement = connection1.prepareStatement(Query);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }




        return false;
    }

    @Override
    public boolean updateDb(String Query) {
        return false;
    }

    @Override
    public boolean insertDb(String Query) {
        return false;
    }

    @Override
    public void readDb(String Query) {

    }

    @Override
    public boolean deleteDb(String Query) {
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

    public String getDepartment() {
        return this.department;
    }

    public void setDepartment(String department) {
        this.department = department;
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

    public List<String> getRequest_id() {
        return request_id;
    }

    public void setRequest_id(List<String> request_id) {
        this.request_id = request_id;
    }


    public void setEmp_type(String Emp_Type){

        if(Emp_Type.equals("TeamLeader")){
            this.Emp_type = emp_type.TeamLeader;
        }else{
            this.Emp_type = emp_type.TeamMember;
        }
    }
}
