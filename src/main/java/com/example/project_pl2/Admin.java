package com.example.project_pl2;
import javafx.scene.layout.Border;
import javafx.util.Pair;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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


    public boolean requestResponse(int request_id , int response){

        String query   = "UPDATE plproject.request SET Request_Status = ? WHERE request_id = ?";

        Object [] args = {response , request_id};

        boolean result = CRUD2.updateDbDynamic(query , args).getKey();

        return result;
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


    public boolean deleteProject(int proj_id){

        String query = "DELETE * FROM plproject.project WHERE Project_Id = ?";

        Object [] args = {proj_id};

        boolean result = CRUD2.updateDbDynamic(query , args).getKey();

        return result;
    }

    public boolean reassignProject(int proj_id,int team_id){

        String query = "UPDATE plproject.PROJECT SET Assigned_To = ? WHERE Project_Id = ?";

        Object [] args = {team_id,proj_id};

        boolean result = CRUD2.updateDbDynamic( query , args).getKey();

        return result;
    }


    public boolean addEmployee(int emp_id , String emp_email , String emp_name , String emp_password
            , String emp_role , String emp_type , int team_id){

        String query = "INSERT INTO plproject.EMPLOYEE (Emp_Id , Emp_Email , Emp_Name , Emp_Password , Emp_Role , Emp_Type , Team_Id) VALUES ( ?, ?, ?, ?, ?, ?, ?)";

        Object [] args = {emp_id , emp_email , emp_name , emp_password , emp_role , emp_type , team_id};

        Pair< Boolean , Integer > result = CRUD2.updateDbDynamic(query , args);

        return result.getKey();
    }


    public boolean editEmployeeType(int emp_id , String type){

        String query = "UPDATE employee SET Emp_Type = ? WHERE Emp_Id = ?";

        int emp_type = 1;

        if(type.toUpperCase().equals("LEADER"))
            emp_type = 0;

        Object [] args = {emp_type,emp_id};
        boolean result;

        result = CRUD2.updateDbDynamic(query , args).getKey();

        return result;

    }

    public boolean deleteEmployee(int emp_id){

        String query = "DELETE * FROM employee WHERE Emp_Id = ?";

        Object [] args = {emp_id};

        boolean result = CRUD2.updateDbDynamic(query , args).getKey();

        return result;
    }

    @Override
    public String readFile() throws IOException {
        StringBuilder content = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(new java.io.File(file_name)));
        String line;
        while ((line = reader.readLine()) != null) {
            content.append(line).append("\n");
        }
        reader.close();
        return content.toString().trim();
    }

    @Override
    // Update Admin Password
    public void updateAdminUsername(String new_username) throws IOException {
        String file_content = readFromFile();
        String updated_content = file_content.replaceAll("Admin_Username: (.*)", "Admin_Username: " + new_username);
        writeToFile(updated_content);
        System.out.println("Admin username updated successfully!");
    }

    @Override
    // Update Admin Password
    public void updateAdminPassword(String new_password) throws IOException {
        String file_content = readFromFile();
        String updated_content = file_content.replaceAll("Admin_Password: (.*)", "Admin_Password: " + new_password);
        writeToFile(updated_content);
        System.out.println("Admin password updated successfully!");
    }

    @Override
    // Add Project
    public void addProjectFile(int project_id) throws IOException {
        String file_content = readFromFile();

        // Check if required ID is presents or not
        int[] ids = getAllProjectIds();
        for (int j : ids) {
            if (j == project_id){
                System.out.println("! This ID is already Exist !");
                return;
            }
        }

        // Extract current project count
        Pattern projects_pattern = Pattern.compile("Projects: (\\d+)");
        Matcher projects_matcher = projects_pattern.matcher(file_content);

        if (projects_matcher.find()) {
            int current_projects = Integer.parseInt(projects_matcher.group(1));

            // Add the project with the given ID
            file_content = file_content + "\nProject_Id = " + project_id;

            // Update the project count
            file_content = file_content.replaceAll("Projects: \\d+", "Projects: " + (current_projects + 1));

            // Write the updated content back to the file
            writeToFile(file_content);

            System.out.println("Project added successfully!");

        }
    }

    @Override
    // Delete Project
    public void deleteProjectFile(int project_id) throws IOException {
        String file_content = readFromFile();

        // Extract current project count
        Pattern projects_pattern = Pattern.compile("Projects: (\\d+)");
        Matcher projects_matcher = projects_pattern.matcher(file_content);

        if (projects_matcher.find()) {
            int current_projects = Integer.parseInt(projects_matcher.group(1));

            // Check if Admin have any Projects
            if(current_projects > 0) {
                // Remove the project with the given ID
                String project_pattern_str = "\n" + "Project_Id = " + project_id;

                int size_before_delete = file_content.length();
                file_content = file_content.replaceAll(project_pattern_str, "");

                // Check if Project of required ID is exist
                if(size_before_delete == file_content.length()){
                    System.out.println("! No Project of ID[" +project_id+ "] in the list !");
                    return;
                }

                // Update the project count
                file_content = file_content.replaceAll("Projects: \\d+", "Projects: " + (current_projects - 1));

                // Write the updated content back to the file
                writeToFile(file_content);

                System.out.println("Project deleted successfully!");
            }
            else {
                System.out.println("! No Project exist to be DELETED !");
            }
        } else {
            System.out.println("Projects count not found in the file.");
        }

    }

    @Override
    // Get an array of all project IDs
    public int[] getAllProjectIds() throws IOException {
        String file_content = readFromFile();

        // Extract all Project IDs using regular expression
        List<Integer> project_ids = new ArrayList<>();
        Pattern project_pattern = Pattern.compile("Project_Id = (\\d+)");
        Matcher project_matcher = project_pattern.matcher(file_content);

        while (project_matcher.find()) {
            int project_id = Integer.parseInt(project_matcher.group(1));
            project_ids.add(project_id);
        }

        // Convert the List to an array
        int[] project_ids_Array = new int[project_ids.size()];
        for (int i = 0; i < project_ids.size(); i++) {
            project_ids_Array[i] = project_ids.get(i);
        }

        return project_ids_Array;
    }

    // Read File Content
    private String readFromFile() throws IOException {
        StringBuilder content = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(new java.io.File(file_name)));
        String line;
        while ((line = reader.readLine()) != null) {
            content.append(line).append("\n");
        }
        reader.close();
        return content.toString().trim();
    }

    // Write Content to File
    private void writeToFile(String content) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(new java.io.File(file_name)));
        writer.write(content);
        writer.close();
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