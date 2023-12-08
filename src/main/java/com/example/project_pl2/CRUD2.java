package com.example.project_pl2;

import Database.DbConnection;
import javafx.util.Pair;

import java.sql.*;

public abstract class CRUD2 {


    // Pair allows us to get the id of the affected row when the query is valid on the spot
    public static Pair<Boolean, Integer> updateDbDynamic(String query){

        DbConnection db_conn = new DbConnection();

        try{
            Connection conn_obj = db_conn.getConnection();
            Statement statement = conn_obj.createStatement();

            // returns x|x>0 for DML and x|x==0 for DDL
            int aff_rows = statement.executeUpdate(query,
                    Statement.RETURN_GENERATED_KEYS);

            //TODO: should we account for the possibility of multiple row queries?
            if (aff_rows > 0){
            try (ResultSet rs_obj = statement.getGeneratedKeys()){
                if (rs_obj.next()){
                    return new Pair<Boolean, Integer>(true, rs_obj.getInt(1));
                }
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
            }

        }catch(SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
        return new Pair<Boolean, Integer>(false, null);
    }



    public static Pair<Boolean, Integer> updateDbDynamic(String query ,Object[] args ){

        DbConnection db_conn = new DbConnection();

        try{
            Connection conn_obj = db_conn.getConnection();
            PreparedStatement statement = conn_obj.prepareStatement(query,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            int i = 1;
            for (Object arg: args) {
                statement.setObject(i++, arg);
            }
            int aff_rows = statement.executeUpdate();
            if (aff_rows > 0){
                try (ResultSet rs_obj = statement.getGeneratedKeys()){
                    if (rs_obj.isBeforeFirst()){
                        rs_obj.next();
                        return new Pair<Boolean, Integer>(true, rs_obj.getInt(1));
                    }
                }catch (SQLException e){
                    throw new RuntimeException(e);
                }
            }

        }catch(SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
        return new Pair<Boolean, Integer>(false, null);
    }





    public static ResultSet readDbDynamic(String query){
        DbConnection db_conn = new DbConnection();
        try {
            Connection conn_obj = db_conn.getConnection();
            Statement statement = conn_obj.createStatement();
            // Execute the statement and get the results
            return statement.executeQuery(query);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }




    public static ResultSet readDbDynamic(String query, Object[] args){
        DbConnection db_conn = new DbConnection();
        try {
            Connection conn_obj = db_conn.getConnection();
            PreparedStatement statement = conn_obj.prepareStatement(query);
            int i = 1;
            for (Object arg: args) {
                statement.setObject(i++, arg);
            }

            // Execute the statement and get the results
            return statement.executeQuery();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
