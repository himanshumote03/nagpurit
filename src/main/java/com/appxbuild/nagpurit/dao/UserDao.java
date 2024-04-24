package com.appxbuild.nagpurit.dao;

import com.appxbuild.nagpurit.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {

    // no need to write anything

}
