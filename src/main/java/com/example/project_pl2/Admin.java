package com.example.project_pl2;

public class Admin extends Person{

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setName(String name){
        this.name = name;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }

    @Override
    public String read() {
        return null;
    }

    @Override
    public void write(String content) {

    }

    public void showProjectProgress (){

    }
    public void reviewRequest (){

    }

    public void CreateProject (){

    }
    public void print(){

    }
}
