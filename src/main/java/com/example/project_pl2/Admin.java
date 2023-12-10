package com.example.project_pl2;
import javafx.scene.layout.Border;
import javafx.util.Pair;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class Admin extends Person implements File{

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setName(String name){
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


    public static boolean login(String email, String password) {
        return false;
    }


    public void showProjectProgress (){

    }

    public List<Request> reviewRequests () throws SQLException {


        String query = "SELECT * from Request";

        ResultSet result = CRUD2.readDbDynamic(query);

        List<Request> Request_list = null;



        if(result.isBeforeFirst()){
            int i = 0 ;

            while(result.next()){

                Request request = new Request(result);
                Request_list.set(i,request);

                i++;

            }

        }else{
            System.out.print("No Requests available to review");
        }
        return Request_list;
    }



    public boolean createProject (int proj_id , int team_id ,Utility.CompletionStatus status,String proj_desc , String proj_Title ){

        String query = "INSERT INTO Project (Project_Id , Assigned_To , Progress_status , Project_desc , Project_Title) " +
                "VALUES (?,?,?,?,?)";

        Object [] args = { proj_id ,team_id, status ,proj_desc,proj_Title};

        Pair <Boolean , Integer> result = CRUD2.updateDbDynamic(query , args);

       return result.getKey();

    }

    public boolean addEmployee(int emp_id , String emp_email , String emp_name , String emp_password
            , String emp_role , String emp_type , int team_id){

        String query = "INSERT INTO plproject.EMPLOYEE (Emp_Id , Emp_Email , Emp_Name , Emp_Password , Emp_Role , Emp_Type , Team_Id) VALUES ( ?, ?, ?, ?, ?, ?, ?)";

        Object [] args = {emp_id , emp_email , emp_name , emp_password , emp_role , emp_type , team_id};

        Pair< Boolean , Integer > result = CRUD2.updateDbDynamic(query , args);

        return result.getKey();
    }

    public void print(){

    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }

    @Override
    public String read() {
        return null;
    }




    @Override
    public void write(int id,String name,String email,String password) throws IOException{
        String filepath = "D:/ADMIN_DATA.TXT";
        java.io.File file = new java.io.File(filepath);
        if (!file.exists())
            file.createNewFile();
        FileWriter fwrite = new FileWriter(file,true);
        fwrite.write(id +"\n");
        fwrite.write(name+"\n");
        fwrite.write(email+"\n");
        fwrite.write(password+"\n");

        fwrite.close();

    }
}



//    Scanner scanner = new Scanner(System.in);
//
//    int option;
//        do {
//                System.out.println("Welcome to Admin Management!");
//
//                System.out.println("Select an option:");
//                System.out.println("1. Add Admin");
//                System.out.println("2. Delete Admin");
//                System.out.println("3. List All Admins");
//                System.out.println("0. Exit");
//                option = scanner.nextInt();
//
//                switch (option) {
//                case 1:
//                System.out.println("Enter admin ID");
//                int id = scanner.nextInt();
//                System.out.println("Enter admin name");
//                String name = scanner.next();
//                Admin admin = new Admin();
//                admin.write(id, name);
//                System.out.println("Admin added successfully\n");
//                break;
//                case 2:
//                System.out.println("Enter The Admin ID to be removed");
//                int Id = scanner.nextInt();
//                Admin dmin = new Admin();
//                dmin.delete(Id);
//                System.out.println("Admin OF ID " + Id + " Was deleted successfully.");
//
//
//
//
//                }
//                } while (option != 0);
//A DEMO OF WHAT SHOULD BE IN MAIN ADMIN PAGE AFTER LOGIN