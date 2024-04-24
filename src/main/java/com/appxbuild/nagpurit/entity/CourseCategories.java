package com.appxbuild.nagpurit.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

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

    public CourseCategories() {
    }

    public CourseCategories(String courseType, LocalDateTime created, LocalDateTime modified) {
        this.courseType = courseType;
        this.created = created;
        this.modified = modified;
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
