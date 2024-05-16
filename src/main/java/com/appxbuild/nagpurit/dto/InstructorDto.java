package com.appxbuild.nagpurit.dto;

import org.springframework.web.multipart.MultipartFile;

public class InstructorDto {

    // define fields
    private int id;

    private MultipartFile imageFile;

    private String name;

    private int totalStudents;

    private String designation;

    private String description;

    private String githubUrl;

    private String linkedinUrl;

    // define constructor
    public InstructorDto(){

    }
    public InstructorDto(MultipartFile imageFile, String name, int totalStudents, String designation, String description, String githubUrl, String linkedinUrl) {
        this.imageFile = imageFile;
        this.name = name;
        this.totalStudents = totalStudents;
        this.designation = designation;
        this.description = description;
        this.githubUrl = githubUrl;
        this.linkedinUrl = linkedinUrl;
    }

    // define getter/setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MultipartFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
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

    public void setTotalStudents(int totalStudents) {
        this.totalStudents = totalStudents;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
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

    // define toString method
    @Override
    public String toString() {
        return "InstructorDto{" +
                "id=" + id +
                ", imageFile=" + imageFile +
                ", name='" + name + '\'' +
                ", totalStudents=" + totalStudents +
                ", designation='" + designation + '\'' +
                ", description='" + description + '\'' +
                ", githubUrl='" + githubUrl + '\'' +
                ", linkedinUrl='" + linkedinUrl + '\'' +
                '}';
    }

}
