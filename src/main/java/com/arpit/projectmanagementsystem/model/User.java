package com.arpit.projectmanagementsystem.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long Id;

    private String fullName;
    private String email;
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "assignee",cascade = CascadeType.ALL )
    private List<Issue> assignedValues = new ArrayList<>();

    public User() {
    }

    public User(Long id, String fullName, String email, String password, List<Issue> assignedValues, int projectSize) {
        Id = id;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.assignedValues = assignedValues;
        this.projectSize = projectSize;
    }

    private int projectSize;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Issue> getAssignedValues() {
        return assignedValues;
    }

    public void setAssignedValues(List<Issue> assignedValues) {
        this.assignedValues = assignedValues;
    }

    public int getProjectSize() {
        return projectSize;
    }

    public void setProjectSize(int projectSize) {
        this.projectSize = projectSize;
    }
}
