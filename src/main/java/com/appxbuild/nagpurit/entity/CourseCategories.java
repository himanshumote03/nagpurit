package com.appxbuild.nagpurit.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "course_categories")
public class CourseCategories {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "course_type")
    private String courseType;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "modified")
    private LocalDateTime modified;

    @OneToMany(mappedBy = "courseCategories", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("courseCategories")
    private List<Courses> courses;

    public CourseCategories() {
    }

    public CourseCategories(String courseType, LocalDateTime created, LocalDateTime modified, List<Courses> courses) {
        this.courseType = courseType;
        this.created = created;
        this.modified = modified;
        this.courses = courses;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
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

    public List<Courses> getCourses() {
        return courses;
    }

    public void setCourses(List<Courses> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "CourseCategories{" +
                "id=" + id +
                ", courseType='" + courseType + '\'' +
                ", created=" + created +
                ", modified=" + modified +
                '}';
    }
}
