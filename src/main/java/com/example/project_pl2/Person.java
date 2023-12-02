package com.example.project_pl2;


public abstract class Person extends File {

    protected String name;
    protected String email;
    protected String password;
    protected int id;


    public abstract String getName();
    public abstract String getPassword();
    public abstract String getEmail();
    public abstract int getId();
    public abstract void setName(String name);
    public abstract void setId(int id);
    public abstract void setPassword(String password);
    public abstract void setEmail(String email);


    //this won't be an abstract method i'll implement it later
    //public abstract void login();

}
