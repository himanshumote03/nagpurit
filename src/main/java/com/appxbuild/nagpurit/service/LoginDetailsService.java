package com.appxbuild.nagpurit.service;

import com.appxbuild.nagpurit.entity.LoginDetails;

import java.util.List;

public interface LoginDetailsService {

    List<LoginDetails> findAll();
    LoginDetails findById(int theId);
    LoginDetails save(LoginDetails loginDetails);
    void deleteById(int theId);

    LoginDetails findByEmail(String email);
}
