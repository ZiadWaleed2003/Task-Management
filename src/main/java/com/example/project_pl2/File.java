package com.example.project_pl2;

import java.io.*;

public interface File {
    String file_name = "D:\\Project__pl2\\Task-Management\\src\\main\\java\\com\\example\\project_pl2\\admin_data.txt"; // add File bath
    String readFile() throws IOException;

    void updateAdminUsername(String username) throws IOException;

    void updateAdminPassword(String password) throws IOException;

    boolean addProjectFile(int projectId) throws IOException;

    boolean deleteProjectFile(int projectId) throws IOException;

    int[] getAllProjectIds() throws IOException;

//    void write(int id,String content,String email,String password)throws IOException;
}