package DataBase;


import java.sql.*;


public class DbConnection {
    private final String db_host     = "localhost:3306";
    private final String db_password = "mssqlsucks123";
    private final String db_user     = "root";

    public Connection getConnection()throws SQLException{

        return DriverManager.getConnection(db_host,db_user,db_password) ;
    }
}
