package com.appxbuild.nagpurit.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "fields_details")
public class FieldsDetails {

    // define fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "field_id", referencedColumnName = "id")
    private Fields fields;

    @Column(name = "field_name")
    private String fieldName;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "modified")
    private LocalDateTime modified;

    @OneToMany(mappedBy = "fieldsDetails")
    @JsonIgnoreProperties("fieldsDetails")
    private List<CareerGoal> careerGoals;

    // define constructor
    public FieldsDetails(){

    }

    public FieldsDetails(Fields fields, String fieldName, LocalDateTime created, LocalDateTime modified) {
        this.fields = fields;
        this.fieldName = fieldName;
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

    public Fields getFields() {
        return fields;
    }

    public void setFields(Fields fields) {
        this.fields = fields;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
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
        return "FieldsDetails{" +
                "id=" + id +
                ", fields=" + fields +
                ", fieldName='" + fieldName + '\'' +
                ", created=" + created +
                ", modified=" + modified +
                '}';
    }

}
