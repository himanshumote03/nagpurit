package com.appxbuild.nagpurit.dao;

import com.appxbuild.nagpurit.entity.Favourite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavouriteDao extends JpaRepository<Favourite, Integer> {
}
