package com.appxbuild.nagpurit.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "career_goal")
public class CareerGoal {

    // define fields

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "login_id", referencedColumnName = "id")
    private LoginDetails loginDetails;

    @Column(name = "current_goal")
    private String currentGoal;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "field_details_id", referencedColumnName = "id")
    private FieldsDetails fieldsDetails;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "modified")
    private LocalDateTime modified;

    //define constructor
    public CareerGoal(){

    }
    public CareerGoal(String currentGoal, LocalDateTime created, LocalDateTime modified) {
        this.currentGoal = currentGoal;
        this.created = created;
        this.modified = modified;
    }

    // define getter/setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LoginDetails getLoginDetails() {
        return loginDetails;
    }

    public void setLoginDetails(LoginDetails loginDetails) {
        this.loginDetails = loginDetails;
    }

    public String getCurrentGoal() {
        return currentGoal;
    }

    public void setCurrentGoal(String currentGoal) {
        this.currentGoal = currentGoal;
    }

    public FieldsDetails getFieldsDetails() {
        return fieldsDetails;
    }

    public void setFieldsDetails(FieldsDetails fieldsDetails) {
        this.fieldsDetails = fieldsDetails;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    // define toString method
    @Override
    public String toString() {
        return "CareerGoal{" +
                "id=" + id +
                ", loginDetails=" + loginDetails +
                ", currentGoal='" + currentGoal + '\'' +
                ", fieldsDetails=" + fieldsDetails +
                ", created=" + created +
                ", modified=" + modified +
                '}';
    }

}
