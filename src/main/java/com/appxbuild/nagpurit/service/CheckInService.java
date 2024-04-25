package com.appxbuild.nagpurit.service;

import com.appxbuild.nagpurit.entity.CheckIn;

import java.util.List;

public interface CheckInService {
    List<CheckIn> findAll();
    CheckIn findById(int theId);
    CheckIn save(CheckIn checkIn);
    void deleteById(int theId);
}
