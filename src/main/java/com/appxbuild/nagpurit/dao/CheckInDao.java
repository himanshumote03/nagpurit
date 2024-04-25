package com.appxbuild.nagpurit.dao;

import com.appxbuild.nagpurit.entity.CheckIn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckInDao extends JpaRepository<CheckIn, Integer> {

    // no need to write anything

}
