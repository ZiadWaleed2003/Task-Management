package com.example.project_pl2;

import java.io.*;

public interface File {

    String file_name = "F:\\Drasaaaa\\FCAI - Helwan University\\Level 2\\First Term\\PL-2\\2a5r mrra\\Task-Management\\src\\main\\java\\com\\example\\project_pl2\\admin_data.txt"; // add File bath

    String readFile() throws IOException;

    void updateAdminUsername(String username) throws IOException;

    void updateAdminPassword(String password) throws IOException;

    boolean addProjectFile(int projectId) throws IOException;

    boolean deleteProjectFile(int projectId) throws IOException;

    int[] getAllProjectIds() throws IOException;

//    void write(int id,String content,String email,String password)throws IOException;
}