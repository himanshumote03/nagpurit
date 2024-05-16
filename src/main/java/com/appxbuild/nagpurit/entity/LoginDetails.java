package com.appxbuild.nagpurit.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "login_details")
public class LoginDetails {

    // fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "modified")
    private LocalDateTime modified;

    @OneToMany(mappedBy = "loginDetails")
    private List<CareerGoal> careerGoals;

//    @OneToMany(mappedBy = "loginDetails")
//    private List<Transactions> transactions;

    @OneToMany(mappedBy = "loginDetails")
    private List<Reviews> reviews;

    @OneToMany(mappedBy = "loginDetails", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("loginDetails")
    private List<User> user;

    @OneToMany(mappedBy = "loginDetails", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("loginDetails")
    private List<MyCourses> myCourses;

    @OneToMany(mappedBy = "loginDetails", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("loginDetails")
    private List<Cart> cart;
    @OneToMany(mappedBy = "loginDetails", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("loginDetails")
    private List<Wishlist> wishlists;

    @OneToMany(mappedBy = "loginDetails", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("loginDetails")
    private List<Favourite> favourites;

    @OneToMany(mappedBy = "loginDetails", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("loginDetails")
    private List<Transactions> transactions;



    // Constructor
    public LoginDetails() {}

    public LoginDetails(String name, String email, String password, LocalDateTime created, LocalDateTime modified, List<User> user) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.created = created;
        this.modified = modified;
        this.user = user;
    }

    // getter and setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

    public List<MyCourses> getMyCourses() {
        return myCourses;
    }

    public void setMyCourses(List<MyCourses> myCourses) {
        this.myCourses = myCourses;
    }

    public List<Cart> getCart() {
        return cart;
    }

    public void setCart(List<Cart> cart) {
        this.cart = cart;
    }

    public List<Wishlist> getWishlists() {
        return wishlists;
    }

    public void setWishlists(List<Wishlist> wishlists) {
        this.wishlists = wishlists;
    }

    public List<Favourite> getFavourites() {
        return favourites;
    }

    public void setFavourites(List<Favourite> favourites) {
        this.favourites = favourites;
    }

    public List<Transactions> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transactions> transactions) {
        this.transactions = transactions;
    }

    // toString()
    @Override
    public String toString() {
        return "LoginDetails{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", created=" + created +
                ", modified=" + modified +
                '}';
    }
}
