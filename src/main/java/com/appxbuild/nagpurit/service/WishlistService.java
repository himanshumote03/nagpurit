package com.appxbuild.nagpurit.service;

import com.appxbuild.nagpurit.entity.Wishlist;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WishlistService {

    List<Wishlist> findAll();
    Wishlist findById(int theId);
    Wishlist save(Wishlist theWishlist);
    void deleteById(int theId);

}
