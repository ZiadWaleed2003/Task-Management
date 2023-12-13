package com.example.project_pl2;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.IOException;
import java.sql.SQLException;

public abstract class Person {
    // protected not private bec the inheritance
    protected SimpleStringProperty name;
    protected SimpleStringProperty email;
    protected SimpleStringProperty password;
    protected SimpleIntegerProperty id;

    public Person(){}

/*    public Person(String name, String email, String password, int id) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.id = id;
    }*/

    public abstract String getName();

    public abstract SimpleStringProperty nameProperty();

    public abstract void setName(String name);

    public abstract String getEmail();

    public abstract SimpleStringProperty emailProperty();

    public abstract void setEmail(String email);

    public abstract String getPassword();

    public abstract SimpleStringProperty passwordProperty();

    public abstract void setPassword(String password);

    public abstract int getId();

    public abstract SimpleIntegerProperty idProperty();

    public abstract void setId(int id);


    public static boolean login(String email , String password) throws SQLException, IOException{
        return false;
    };

}
