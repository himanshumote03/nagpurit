package com.appxbuild.nagpurit.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "installment_plan")
public class InstallmentPlan {

    // fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "no_of_months")
    private int noOfMonths;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "modified")
    private LocalDateTime modified;

    @OneToMany(mappedBy = "installmentPlan")
    private List<Transactions> transactions;

    // constructor
    public InstallmentPlan() {}

    public InstallmentPlan(int noOfMonths, LocalDateTime created, LocalDateTime modified) {
        this.noOfMonths = noOfMonths;
        this.created = created;
        this.modified = modified;
    }

    // getter and setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNoOfMonths() {
        return noOfMonths;
    }

    public void setNoOfMonths(int noOfMonths) {
        this.noOfMonths = noOfMonths;
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


    // toString()
    @Override
    public String toString() {
        return "InstallmentPlan{" +
                "id=" + id +
                ", month=" + noOfMonths +
                ", created=" + created +
                ", modified=" + modified +
                '}';
    }
}
