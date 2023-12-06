package com.example.project_pl2;

import java.sql.ResultSet;

public interface CRUD {

    boolean updateDb(String query);
    boolean insertDb(String query);
    ResultSet readDb(String query);
    ResultSet readDbDynamic(String query, String[] args);
    boolean deleteDb(String query);
}
