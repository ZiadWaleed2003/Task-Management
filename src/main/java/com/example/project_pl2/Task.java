package com.example.project_pl2;
import java.util.Date;
public class Task {
    protected final int id;

    protected String name;
    protected String description;
    protected Status status;
    protected int assignedTo;
    protected int project;
    protected Priority priority;
    protected Date startDate;
    protected Date dueDate;

        public enum Status {
            Pending, InProgress, Completed, Canceled
        }
        public enum Priority {
            Low, Medium, High
        }

        public Task(int id, String name, String description,
                    Status status, int assignedTo, int project,
                    Priority priority, Date startDate, Date dueDate) {
            this.id = id;
            this.name = name;
            this.description = description;
            this.status = status;
            this.assignedTo = assignedTo;
            this.project = project;
            this.priority = priority;
            this.startDate = startDate;
            this.dueDate = dueDate;
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

        public Status getStatus() {
            return status;
        }

        public void setStatus(Status status) {
            this.status = status;
        }

        public int getAssignedTo() {
            return assignedTo;
        }

        public void setAssignedTo(int assignedTo) {
            this.assignedTo = assignedTo;
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
            return startDate;
        }

        public void setStartDate(Date startDate) {
            this.startDate = startDate;
        }

        public Date getDueDate() {
            return dueDate;
        }

        public void setDueDate(Date dueDate) {
            this.dueDate = dueDate;
        }
        // after getter and setter
        // TODo (i need to know the main function that related to Task )
    }

