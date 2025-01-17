package com.appxbuild.nagpurit.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "fields")
public class Fields {

    // define fields

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "description")
    private String description;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "modified")
    private LocalDateTime modified;

    @OneToMany(mappedBy = "fields", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("fields")
    private List<FieldsDetails> fieldsDetails;

    // define constructor
    public Fields(){

    }

    public Fields(String description, LocalDateTime created, LocalDateTime modified, List<FieldsDetails> fieldsDetails) {
        this.description = description;
        this.created = created;
        this.modified = modified;
        this.fieldsDetails = fieldsDetails;
    }

    // define getter/setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public List<FieldsDetails> getFieldsDetails() {
        return fieldsDetails;
    }

    public void setFieldsDetails(List<FieldsDetails> fieldsDetails) {
        this.fieldsDetails = fieldsDetails;
    }

    //define toString method
    @Override
    public String toString() {
        return "Fields{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", created=" + created +
                ", modified=" + modified +
                '}';
    }

}
