package com.example.project_pl2;

import javafx.concurrent.Task;

import java.util.ArrayList;
import java.util.Random;



public class Project {
    //IDs:
    protected final int project_id;
    protected final int team_id;
    //Progress
    protected Utility.CompletionStatus current_status;
    protected int internal_task_count;
    protected ArrayList<IndivTask> internal_tasks;
    protected int current_progress;

    //Desc:
    protected final String project_title;
    protected StringBuilder project_description = new StringBuilder(); //NOTE: probably doing this wrong

    Project(int team_id, String project_title){
        this.team_id = team_id;
        this.project_id = Utility.generateID(team_id); //read id from db after push project
        this.project_title = project_title;
        this.project_description.append("To be set.");
        this.current_status = Utility.CompletionStatus.PENDING;
        this.internal_task_count =0;
        this.current_progress = calculateProgress();

    }

    public void setProjectDesc(String desc){
        this.project_description.delete(0, this.project_description.length()-1);
        this.project_description.append(desc);
    }

    public int getTeamID(){
        return this.team_id;
    }
    public int getProjectID(){
        return this.project_id;
    }

    public boolean addTaskObject(IndivTask task){
        return this.internal_tasks.add(task);
    }

    public Utility.CompletionStatus getCompletionStatus(){
        return this.current_status;
    }

    public int getCompletionProgress(){
        return this.current_progress;
    }
    public String getProjectTitle(){
        return this.project_title;
    }
    public String getProjectDesc(){
        return this.project_description.toString();
    }

    private int calculateProgress(){
        int comp_tsks = 0;
        for (IndivTask tsk: this.internal_tasks) {
            if (tsk.getStatus() == Utility.CompletionStatus.COMPLETED){
                comp_tsks++;
            }
        }
        return  (comp_tsks/this.internal_tasks.size())*10;
    }
}

    //method generate return query data required for db
    //make result set construct to return query


