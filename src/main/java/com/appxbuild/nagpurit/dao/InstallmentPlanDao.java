package com.appxbuild.nagpurit.dao;

import com.appxbuild.nagpurit.entity.InstallmentPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstallmentPlanDao extends JpaRepository<InstallmentPlan, Integer> {
}
