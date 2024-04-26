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

    @Column(name = "month")
    private int month;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "modified")
    private LocalDateTime modified;

    @OneToMany(mappedBy = "installmentPlan")
    private List<Transactions> transactions;

    // constructor
    public InstallmentPlan() {}

    public InstallmentPlan(int month, LocalDateTime created, LocalDateTime modified) {
        this.month = month;
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

    public int getNoOfMonth() {
        return month;
    }

    public void month(int month) {
        this.month = month;
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
                ", month=" + month +
                ", created=" + created +
                ", modified=" + modified +
                '}';
    }
}
