package com.appxbuild.nagpurit.dao;

import com.appxbuild.nagpurit.entity.LoginDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginDetailsDao extends JpaRepository<LoginDetails, Integer> {
    LoginDetails findByEmail(String email);
}
