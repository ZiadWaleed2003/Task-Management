package com.example.project_pl2;
import java.io.*;
import java.sql.ResultSet;
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

    @Override
    public boolean login(String email, String password) {
        return false;
    }


    public void showProjectProgress (){

    }
    public void reviewRequest (){

    }

    public void CreateProject (){

    }
    public void print(){

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
    public ResultSet readDb(String Query) {
        return null;
    }

    @Override
    public boolean deleteDb(String Query) {
        return false;
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