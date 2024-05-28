package com.appxbuild.nagpurit.dao;

import com.appxbuild.nagpurit.entity.CareerGoal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CareerGoalDao extends JpaRepository<CareerGoal, Integer> {

    @Query("SELECT cg FROM CareerGoal cg WHERE cg.loginDetails.id = :loginId")
    CareerGoal findByLoginId(@Param("loginId") int loginId);
}
