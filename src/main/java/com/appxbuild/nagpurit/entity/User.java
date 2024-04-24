package com.appxbuild.nagpurit.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "id")
    private int id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "login_id")
    private LoginDetails loginDetails;

    @Column(name = "image")
    private String image;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "language")
    private String language;

    @Column(name = "github_url")
    private String githubUrl;

    @Column(name = "linkdin_url")
    private String linkdinUrl;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "modified")
    private LocalDateTime modified;

    public User(){

    }

    public User( String image, String firstName, String lastName, String language, String githubUrl, String linkdinUrl, LocalDateTime created, LocalDateTime modified) {
        this.image = image;
        this.firstName = firstName;
        this.lastName = lastName;
        this.language = language;
        this.githubUrl = githubUrl;
        this.linkdinUrl = linkdinUrl;
        this.created = created;
        this.modified = modified;
    }

    // Getters setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LoginDetails getLoginDetails() {
        return loginDetails;
    }

    public void setLoginDetails(LoginDetails loginDetails) {
        this.loginDetails = loginDetails;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    public void setGithubUrl(String githubUrl) {
        this.githubUrl = githubUrl;
    }

    public String getLinkdinUrl() {
        return linkdinUrl;
    }

    public void setLinkdinUrl(String linkdinUrl) {
        this.linkdinUrl = linkdinUrl;
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
        return "User{" +
                "id=" + id +
                ", loginDetails=" + loginDetails +
                ", image='" + image + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", language='" + language + '\'' +
                ", githubUrl='" + githubUrl + '\'' +
                ", linkdinUrl='" + linkdinUrl + '\'' +
                ", created=" + created +
                ", modified=" + modified +
                '}';
    }
}
