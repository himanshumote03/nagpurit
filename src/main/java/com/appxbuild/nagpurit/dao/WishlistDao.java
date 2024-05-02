package com.appxbuild.nagpurit.dao;

import com.appxbuild.nagpurit.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishlistDao extends JpaRepository<Wishlist, Integer> {
}
