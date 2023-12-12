package com.example.project_pl2;

import java.io.*;

public interface File {
    String file_name = "admin_data.txt"; // add File bath
    String readFile() throws IOException;

    void updateAdminUsername(String username) throws IOException;

    void updateAdminPassword(String password) throws IOException;

    void addProjectFile(int projectId) throws IOException;

    void deleteProjectFile(int projectId) throws IOException;

    int[] getAllProjectIds() throws IOException;

//    void write(int id,String content,String email,String password)throws IOException;
}