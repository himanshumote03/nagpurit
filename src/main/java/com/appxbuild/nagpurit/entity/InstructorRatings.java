package com.appxbuild.nagpurit.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "instructor_ratings")
public class InstructorRatings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "login_id", referencedColumnName = "id")
    private LoginDetails loginDetails;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "instructor_id", referencedColumnName = "id")
    private Instructor instructor;

    @Column(name = "ratings")
    private Double ratings;

    @Column(name = "created")
    private LocalDateTime created;


    public InstructorRatings() {
    }

    public InstructorRatings(Double ratings, LocalDateTime created) {
        this.ratings = ratings;
        this.created = created;
    }

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

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public Double getRatings() {
        return ratings;
    }

    public void setRatings(Double ratings) {
        this.ratings = ratings;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }


    @Override
    public String toString() {
        return "Reviews{" +
                "id=" + id +
                ", loginDetails=" + loginDetails +
                ", instructor=" + instructor +
                ", ratings='" + ratings + '\'' +
                ", created=" + created +
                '}';
    }
}
