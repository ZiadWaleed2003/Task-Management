package com.example.project_pl2;


public abstract class Person implements CRUD {

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

    public abstract boolean login(String email , String password);

}
