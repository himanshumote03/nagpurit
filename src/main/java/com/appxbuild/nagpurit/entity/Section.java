package com.appxbuild.nagpurit.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "section")
public class Section {

    // define fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "section_name")
    private String sectionName;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Courses courses;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "modified")
    private LocalDateTime modified;

    @OneToMany(mappedBy = "section", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("section")
    private List<Videos> videos;


    // define constructor
    public Section(){

    }
    public Section(String sectionName, Courses courses, LocalDateTime created, LocalDateTime modified, List<Videos> videos) {
        this.sectionName = sectionName;
        this.courses = courses;
        this.created = created;
        this.modified = modified;
        this.videos = videos;
    }

    // define getter/setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public Courses getCourses() {
        return courses;
    }

    public void setCourses(Courses courses) {
        this.courses = courses;
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

    public List<Videos> getVideos() {
        return videos;
    }

    public void setVideos(List<Videos> videos) {
        this.videos = videos;
    }

    // define toString method
    @Override
    public String toString() {
        return "Section{" +
                "id=" + id +
                ", sectionName='" + sectionName + '\'' +
                ", courses=" + courses +
                ", created=" + created +
                ", modified=" + modified +
                ", videos=" + videos +
                '}';
    }

}