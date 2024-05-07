package com.appxbuild.nagpurit.service;

import com.appxbuild.nagpurit.dao.CartDao;
import com.appxbuild.nagpurit.entity.Cart;
import com.appxbuild.nagpurit.entity.Wishlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private CartDao cartDao;

    @Autowired
    public CartServiceImpl(CartDao cartDao) {
        this.cartDao = cartDao;
    }

    @Override
    public List<Cart> findAll() {
        return cartDao.findAll();
    }

    @Override
    public Cart findById(int theId) {
        Optional<Cart> result = cartDao.findById(theId);
        Cart theCart = null;
        if(result.isPresent()){
            theCart = result.get();
        }
        else {
//            throw new RuntimeException("Cart id is not found " + theId);
            return new Cart();
        }
        return theCart;
    }

    @Override
    public Cart save(Cart theCart) {
        return cartDao.save(theCart);
    }

    @Override
    public void deleteById(int theId) {
        cartDao.deleteById(theId);
    }
}
