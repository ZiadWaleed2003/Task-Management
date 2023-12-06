package com.example.project_pl2;

import java.sql.ResultSet;

public interface CRUD {

    boolean updateDb(String Query);
    boolean insertDb(String Query);
    ResultSet readDb(String Query);
    boolean deleteDb(String Query);
}
