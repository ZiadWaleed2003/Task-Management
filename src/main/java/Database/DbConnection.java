package Database;


import java.sql.*;


public class DbConnection {
    private final String db_host     = "jdbc:mysql://localhost:3306/Plproject";
    private final String db_password = "mssqlsucks123";
    private final String db_user     = "root";

    public DbConnection(){}

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

        return DriverManager.getConnection(db_host,db_user,db_password);
    }
}
