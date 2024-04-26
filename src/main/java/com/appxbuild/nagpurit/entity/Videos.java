package com.appxbuild.nagpurit.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "videos")
public class Videos {

    // define fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;

    @Column(name = "title")
    private String title;

    @Column(name = "video_url")
    private String videoUrl;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "modified")
    private LocalDateTime modified;

    // constructors, getters, and setters
    public Videos(){

    }
    public Videos(Section section, String title, String videoUrl, LocalDateTime created, LocalDateTime modified) {
        this.section = section;
        this.title = title;
        this.videoUrl = videoUrl;
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

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
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
        return "Videos{" +
                "id=" + id +
                ", section=" + section +
                ", title='" + title + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", created=" + created +
                ", modified=" + modified +
                '}';
    }

}
