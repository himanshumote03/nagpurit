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

    @Column(name = "login_id")
    private int loginId;

    @Column(name = "course_id")
    private int courseId;

    @Column(name = "message")
    private String message;

    @Column(name = "created")
    private LocalDateTime created;


    public Reviews() {
    }

    public Reviews( int loginId, int courseId, String message, LocalDateTime created) {
        this.loginId = loginId;
        this.courseId = courseId;
        this.message = message;
        this.created = created;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLoginId() {
        return loginId;
    }

    public void setLoginId(int loginId) {
        this.loginId = loginId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
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
                ", loginId=" + loginId +
                ", courseId=" + courseId +
                ", message='" + message + '\'' +
                ", created=" + created +
                '}';
    }
}
