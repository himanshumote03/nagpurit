package com.appxbuild.nagpurit.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "courses")
public class Courses {

    // define fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_category_id", referencedColumnName = "id")
    private CourseCategories courseCategories;

    @Column(name = "course_title")
    private String courseTitle;

    @Column(name = "decsription")
    private String description;

    @Column(name = "ratings")
    private Double ratings;

    @Column(name = "image")
    private String image;

    @Column(name = "language")
    private String  language;

    @Column(name = "sub_title")
    private String subTitle;

    @Column(name = "cost")
    private int cost;

    @Column(name = "course_outcome")
    private String courseOutcome;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "instructor_id", referencedColumnName = "id")
    private Instructor instructor;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "modified")
    private LocalDateTime modified;

    @OneToMany(mappedBy = "courses")
    private List<MyCourses> myCourses;

    @OneToMany(mappedBy = "courses", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("courses")
    private List<Section> section;

    @OneToMany(mappedBy = "courses")
    private List<Reviews> reviews;

    // define constructor
    public Courses(){

    }

    public Courses(CourseCategories courseCategories, String courseTitle, String description, Double ratings, String image, String language, String subTitle, int cost, String courseOutcome, Instructor instructor, LocalDateTime created, LocalDateTime modified, List<Section> sections) {
        this.courseCategories = courseCategories;
        this.courseTitle = courseTitle;
        this.description = description;
        this.ratings = ratings;
        this.image = image;
        this.language = language;
        this.subTitle = subTitle;
        this.cost = cost;
        this.courseOutcome = courseOutcome;
        this.instructor = instructor;
        this.created = created;
        this.modified = modified;
        this.section = section;
    }

    // define getter/setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CourseCategories getCourseCategories() {
        return courseCategories;
    }

    public void setCourseCategories(CourseCategories courseCategories) {
        this.courseCategories = courseCategories;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getRatings() {
        return ratings;
    }

    public void setRatings(Double ratings) {
        this.ratings = ratings;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getCourseOutcome() {
        return courseOutcome;
    }

    public void setCourseOutcome(String courseOutcome) {
        this.courseOutcome = courseOutcome;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
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

    public List<Section> getSection() {
        return section;
    }

    public void setSection(List<Section> section) {
        this.section = section;
    }

    // define toString method
    @Override
    public String toString() {
        return "Courses{" +
                "id=" + id +
                ", courseCategories=" + courseCategories +
                ", courseTitle='" + courseTitle + '\'' +
                ", description='" + description + '\'' +
                ", ratings=" + ratings +
                ", image='" + image + '\'' +
                ", language='" + language + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", cost=" + cost +
                ", courseOutcome='" + courseOutcome + '\'' +
                ", instructor=" + instructor +
                ", created=" + created +
                ", modified=" + modified +
                ", myCourses=" + myCourses +
                ", section=" + section +
                '}';
    }
}
