package com.example.project_pl2;

import DataBase.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CRUD2 {


    public boolean updateDb(String query ,String [] args ){

        DbConnection conn = new DbConnection();

        try{
            Connection conn1 = conn.getConnection();

            PreparedStatement statement = conn1.prepareStatement(query);
        }catch(SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean insertDb(String query){
        return false;
    }




    public ResultSet readDbDynamic(String query, String[] args){
        DbConnection conn = new DbConnection();
        try {
            Connection connection = conn.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            int i = 1;
            for (String arg: args) {
                statement.setObject(i++, arg);
            }

            // Execute the statement and get the results
            return statement.executeQuery();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteDb(String query){
        return false;
    }

}
