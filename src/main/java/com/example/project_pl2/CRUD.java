package com.example.project_pl2;

public interface CRUD {

    boolean updateDb(String Query);
    boolean insertDb(String Query);
    void readDb(String Query);
    boolean deleteDb(String Query);
}
