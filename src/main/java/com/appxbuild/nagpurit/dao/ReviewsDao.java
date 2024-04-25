package com.appxbuild.nagpurit.dao;

import com.appxbuild.nagpurit.entity.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewsDao extends JpaRepository<Reviews, Integer> {
}
