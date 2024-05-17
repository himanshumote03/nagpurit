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

    @Column(name = "description_title")
    private String descriptionTitle;

    @Column(name = "description_content")
    private String descriptionContent;

    @Column(name = "duration")
    private Double duration;

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
    @JsonIgnoreProperties("courses")
    private List<MyCourses> myCourses;

    @OneToMany(mappedBy = "courses", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("courses")
    private List<Section> section;

    @OneToMany(mappedBy = "courses")
    @JsonIgnoreProperties("courses")
    private List<Reviews> reviews;

    @OneToMany(mappedBy = "courses", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("courses")
    private List<CourseRatings> courseRatings;


    // define constructor
    public Courses(){

    }

    public Courses(CourseCategories courseCategories, String courseTitle, String descriptionTitle, String descriptionContent, Double duration, String image, String language, String subTitle, int cost, String courseOutcome, Instructor instructor, LocalDateTime created, LocalDateTime modified, List<MyCourses> myCourses, List<Section> section, List<Reviews> reviews, List<CourseRatings> courseRatings) {
        this.courseCategories = courseCategories;
        this.courseTitle = courseTitle;
        this.descriptionTitle = descriptionTitle;
        this.descriptionContent = descriptionContent;
        this.duration = duration;
        this.image = image;
        this.language = language;
        this.subTitle = subTitle;
        this.cost = cost;
        this.courseOutcome = courseOutcome;
        this.instructor = instructor;
        this.created = created;
        this.modified = modified;
        this.myCourses = myCourses;
        this.section = section;
        this.reviews = reviews;
        this.courseRatings = courseRatings;
    }


    // define getter/setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public CourseCategories getCourseCategories() {
//        return courseCategories;
//    }

    public void setCourseCategories(CourseCategories courseCategories) {
        this.courseCategories = courseCategories;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getDescriptionTitle() {
        return descriptionTitle;
    }

    public void setDescriptionTitle(String descriptionTitle) {
        this.descriptionTitle = descriptionTitle;
    }

    public String getDescriptionContent() {
        return descriptionContent;
    }

    public void setDescriptionContent(String descriptionContent) {
        this.descriptionContent = descriptionContent;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
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

    public List<Reviews> getReviews() {
        return reviews;
    }

    public void setReviews(List<Reviews> reviews) {
        this.reviews = reviews;
    }

    // define toString method
    @Override
    public String toString() {
        return "Courses{" +
                "id=" + id +
                ", courseCategories=" + courseCategories +
                ", courseTitle='" + courseTitle + '\'' +
                ", descriptionTitle='" + descriptionTitle + '\'' +
                ", descriptionContent='" + descriptionContent + '\'' +
                ", duration=" + duration +
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
                ", reviews=" + reviews +
                ", courseRatings=" + courseRatings +
                '}';
    }
}
