package com.appxbuild.nagpurit.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @Column(name = "designation")
    private String designation;

    @Column(name = "total_students")
    private int totalStudents;

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

    @OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("instructor")
    private List<InstructorRatings> instructorRatings;

    @OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("instructor")
    private List<InstructorReviews> instructorReviews;

    // define constructor
    public Instructor(){

    }
    public Instructor(String image, String name, int totalStudents, String designation, String description, String githubUrl, String linkedinUrl, LocalDateTime created, LocalDateTime modified, List<InstructorRatings> instructorRatings, List<InstructorReviews> instructorReviews) {
        this.image = image;
        this.name = name;
        this.totalStudents = totalStudents;
        this.designation = designation;
        this.description = description;
        this.githubUrl = githubUrl;
        this.linkedinUrl = linkedinUrl;
        this.created = created;
        this.modified = modified;
        this.instructorRatings = instructorRatings;
        this.instructorReviews = instructorReviews;
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

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setTotalStudents(int totalStudents) {
        this.totalStudents = totalStudents;
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

    public List<InstructorRatings> getInstructorRatings() {
        return instructorRatings;
    }

    public void setInstructorRatings(List<InstructorRatings> instructorRatings) {
        this.instructorRatings = instructorRatings;
    }

    public List<InstructorReviews> getInstructorReviews() {
        return instructorReviews;
    }

    public void setInstructorReviews(List<InstructorReviews> instructorReviews) {
        this.instructorReviews = instructorReviews;
    }

    // define toString method
    @Override
    public String toString() {
        return "Instructor{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", name='" + name + '\'' +
                ", designation='" + designation + '\'' +
                ", totalStudents=" + totalStudents +
                ", description='" + description + '\'' +
                ", githubUrl='" + githubUrl + '\'' +
                ", linkedinUrl='" + linkedinUrl + '\'' +
                ", created=" + created +
                ", modified=" + modified +
                ", courses=" + courses +
                ", instructorRatings=" + instructorRatings +
                ", instructorReviews=" + instructorReviews +
                '}';
    }

}
