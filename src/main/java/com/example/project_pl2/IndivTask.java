package com.example.project_pl2;

import java.util.Date;
public class IndivTask {
    protected int id;

    protected String name;
    protected String description;
    protected Utility.CompletionStatus status;
    protected int assigned_to;
    protected int project;
    protected Priority priority;
    protected Date start_date;
    protected Date due_date;

        public enum Priority {
            LOW, MEDIUM, HIGH
        }
        public IndivTask(){

        }
        public IndivTask(int id, String name, String description, Utility.CompletionStatus status, int assigned_to, int project, Priority priority, Date start_date, Date due_date) {
            this.id = id;
            this.name = name;
            this.description = description;
            this.status = status;
            this.assigned_to = assigned_to;
            this.project = project;
            this.priority = priority;
            this.start_date = start_date;
            this.due_date = due_date;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Utility.CompletionStatus getStatus() {
            return status;
        }

        public void setStatus(Utility.CompletionStatus status) {
            this.status = status;
        }

        public int getAssignedTo() {
            return assigned_to;
        }

        public void setAssignedTo(int assigned_to) {
            this.assigned_to = assigned_to;
        }

        public int getProject() {
            return project;
        }

        public void setProject(int project) {
            this.project = project;
        }

        public Priority getPriority() {
            return priority;
        }

        public void setPriority(Priority priority) {
            this.priority = priority;
        }

        public Date getStartDate() {
            return start_date;
        }

        public void setStartDate(Date start_date) {
            this.start_date = start_date;
        }

        public Date getDueDate() {
            return due_date;
        }

        public void setDueDate(Date due_date) {
            this.due_date = due_date;
        }
        // after getter and setter
        // TODo (i need to know the main function that related to IndivTask )
        // Add, update, Delete task
    }

