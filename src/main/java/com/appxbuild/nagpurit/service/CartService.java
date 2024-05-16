package com.appxbuild.nagpurit.service;

import com.appxbuild.nagpurit.entity.Cart;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {

    List<Cart> findAll();
    Cart findById(int theId);
    Cart save(Cart theCart);
    void deleteById(int theId);
}
