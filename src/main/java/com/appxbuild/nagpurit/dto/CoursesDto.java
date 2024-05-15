package com.appxbuild.nagpurit.dto;

import com.appxbuild.nagpurit.entity.CourseCategories;
import com.appxbuild.nagpurit.entity.Instructor;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class CoursesDto {

    // define fields
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_category_id", referencedColumnName = "id")
    private CourseCategories courseCategories;

    private String courseTitle;

    private String description;

    private Double ratings;

    private MultipartFile imageFile;

    private String  language;

    private String subTitle;

    private int cost;

    private List<String> courseOutcome;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "instructor_id", referencedColumnName = "id")
    private Instructor instructor;

    // define constructor
    public CoursesDto(){

    }
    public CoursesDto(String courseTitle, String description, Double ratings, MultipartFile imageFile, String language, String subTitle, int cost) {
        this.courseTitle = courseTitle;
        this.description = description;
        this.ratings = ratings;
        this.imageFile = imageFile;
        this.language = language;
        this.subTitle = subTitle;
        this.cost = cost;
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

    public MultipartFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
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

    public List<String> getCourseOutcome() {
        return courseOutcome;
    }

    public void setCourseOutcome(List<String> courseOutcome) {
        this.courseOutcome = courseOutcome;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    // define toString method
    @Override
    public String toString() {
        return "CoursesDto{" +
                "id=" + id +
                ", courseCategories=" + courseCategories +
                ", courseTitle='" + courseTitle + '\'' +
                ", description='" + description + '\'' +
                ", ratings=" + ratings +
                ", imageFile=" + imageFile +
                ", language='" + language + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", cost=" + cost +
                ", courseOutcome='" + courseOutcome + '\'' +
                ", instructor=" + instructor +
                '}';
    }

}
