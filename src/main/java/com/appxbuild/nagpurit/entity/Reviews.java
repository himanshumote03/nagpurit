package com.appxbuild.nagpurit.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
public class Reviews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "login_id", referencedColumnName = "id")
    private LoginDetails loginDetails;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Courses courses;

    @Column(name = "ratings")
    private Double ratings;

    @Column(name = "message")
    private String message;

    @Column(name = "created")
    private LocalDateTime created;


    public Reviews() {
    }

    public Reviews(Double ratings, String message, LocalDateTime created) {
        this.ratings = ratings;
        this.message = message;
        this.created = created;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoginDetails() {
        return loginDetails.getName();
    }

    public void setLoginDetails(LoginDetails loginDetails) {
        this.loginDetails = loginDetails;
    }

//    public Courses getCourses() {
//        return courses;
//    }

    public void setCourses(Courses courses) {
        this.courses = courses;
    }

    public Double getRatings() {
        return ratings;
    }

    public void setRatings(Double ratings) {
        this.ratings = ratings;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
                ", courses=" + courses +
                ", ratings=" + ratings +
                ", message='" + message + '\'' +
                ", created=" + created +
                '}';
    }
}
