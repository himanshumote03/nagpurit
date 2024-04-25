package com.appxbuild.nagpurit.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "courses")
public class Courses {

    // define fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
}
