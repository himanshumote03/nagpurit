package com.appxbuild.nagpurit.service;

import com.appxbuild.nagpurit.entity.InstallmentPlan;

import java.util.List;

public interface InstallmentPlanService {
    List<InstallmentPlan> findAll();
    InstallmentPlan findById(int id);
    InstallmentPlan save(InstallmentPlan installmentPlan);
    void deleteById(int id);
}
