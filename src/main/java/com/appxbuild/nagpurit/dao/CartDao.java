package com.appxbuild.nagpurit.dao;

import com.appxbuild.nagpurit.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartDao extends JpaRepository<Cart, Integer> {
}
