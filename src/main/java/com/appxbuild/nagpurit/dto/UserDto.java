package com.appxbuild.nagpurit.dto;

import org.springframework.web.multipart.MultipartFile;

public class UserDto {

    private int id;

//    private LoginDetails loginDetails;

    private int loginId;

    private String firstName;

    private String lastName;

    private String language;

    private String githubUrl;

    private String linkdinUrl;

    private MultipartFile imageFile;

    public UserDto( String firstName, String lastName, String language, String githubUrl, String linkdinUrl, MultipartFile imageFile) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.language = language;
        this.githubUrl = githubUrl;
        this.linkdinUrl = linkdinUrl;
        this.imageFile = imageFile;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLoginId() {
        return loginId;
    }

    public void setLoginId(int loginId) {
        this.loginId = loginId;
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

    public MultipartFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", loginId=" + loginId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", language='" + language + '\'' +
                ", githubUrl='" + githubUrl + '\'' +
                ", linkdinUrl='" + linkdinUrl + '\'' +
                ", imageFile=" + imageFile +
                '}';
    }
}
