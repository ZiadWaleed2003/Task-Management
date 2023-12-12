package com.example.project_pl2;


import java.io.IOException;
import java.sql.SQLException;

public abstract class Person {

    protected String name;
    protected String email;
    protected String password;
    protected int id;

    public Person(){}
    public Person(String name, String email, String password, int id) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.id = id;
    }

    public abstract String getName();
    public abstract String getPassword();
    public abstract String getEmail();
    public abstract int getId();
    public abstract void setName(String name);
    public abstract void setId(int id);
    public abstract void setPassword(String password);
    public abstract void setEmail(String email);

    public static boolean login(String email , String password) throws SQLException, IOException{
        return false;
    };

}
