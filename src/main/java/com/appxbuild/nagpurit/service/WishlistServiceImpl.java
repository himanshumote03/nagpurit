package com.appxbuild.nagpurit.service;

import com.appxbuild.nagpurit.dao.WishlistDao;
import com.appxbuild.nagpurit.entity.Wishlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishlistServiceImpl implements WishlistService {

    private WishlistDao wishlistDao;

    @Autowired
    public WishlistServiceImpl(WishlistDao wishlistDao) {
        this.wishlistDao = wishlistDao;
    }

    @Override
    public List<Wishlist> findAll() {
        return wishlistDao.findAll();
    }

    @Override
    public Wishlist findById(int theId) {
        Optional<Wishlist> result = wishlistDao.findById(theId);
        Wishlist theWishlist = null;
        if(result.isPresent()){
            theWishlist = result.get();
        }
        else {
//            throw new RuntimeException("Wishlist id is not found " + theId);
            return new Wishlist();
        }
        return theWishlist;
    }

    @Override
    public Wishlist save(Wishlist theWishlist) {
        return wishlistDao.save(theWishlist);
    }

    @Override
    public void deleteById(int theId) {
        wishlistDao.deleteById(theId);
    }
}
