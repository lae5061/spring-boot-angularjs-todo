package com.buildourweb.springbootangularjstodo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Todo {

    public Todo() {
    }

    public Todo(String description, String assignee, LocalDate dueDate) {
        this.description = description;
        this.assignee = assignee;
        this.dueDate = dueDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Description field cannot be empty")
    private String description;

    @NotEmpty(message = "Assignee field cannot be empty")
    private String assignee;

    @Future(message = "Due date must be a future date")
    @NotNull(message = "Due date field cannot be empty")
    private LocalDate dueDate;

    private boolean completed;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", assignee='" + assignee + '\'' +
                ", dueDate=" + dueDate +
                ", completed=" + completed +
                '}';
    }
}
