package com.example.project_pl2;


public abstract class File {

    protected String file_name;
    protected String file_path;
    protected String operation_type;



    public abstract void update();

    public abstract void delete();

    public abstract String read();

    public abstract void write(String content);
}
