package com.appxbuild.nagpurit.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "instructor")
public class Instructor {

    // define fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "image")
    private String image;

    @Column(name = "name")
    private String name;

    @Column(name = "total_students")
    private int totalStudents;

    @Column(name = "reviews")
    private int reviews;

    @Column(name = "description")
    private String description;

    @Column(name = "github_url")
    private String githubUrl;

    @Column(name = "linkdin_url")
    private String linkedinUrl;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "modified")
    private LocalDateTime modified;

    @OneToMany(mappedBy = "instructor")
    private List<Courses> courses;

    // define constructor
    public Instructor(){

    }
    public Instructor(String image, String name, int totalStudents, int reviews, String description, String githubUrl, String linkedinUrl, LocalDateTime created, LocalDateTime modified) {
        this.image = image;
        this.name = name;
        this.totalStudents = totalStudents;
        this.reviews = reviews;
        this.description = description;
        this.githubUrl = githubUrl;
        this.linkedinUrl = linkedinUrl;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(int totalStudents) {
        this.totalStudents = totalStudents;
    }

    public int getReviews() {
        return reviews;
    }

    public void setReviews(int reviews) {
        this.reviews = reviews;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    public void setGithubUrl(String githubUrl) {
        this.githubUrl = githubUrl;
    }

    public String getLinkedinUrl() {
        return linkedinUrl;
    }

    public void setLinkedinUrl(String linkedinUrl) {
        this.linkedinUrl = linkedinUrl;
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
        return "Instructor{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", name='" + name + '\'' +
                ", totalStudents=" + totalStudents +
                ", reviews=" + reviews +
                ", description='" + description + '\'' +
                ", githubUrl='" + githubUrl + '\'' +
                ", linkedinUrl='" + linkedinUrl + '\'' +
                ", created=" + created +
                ", modified=" + modified +
                '}';
    }

}
