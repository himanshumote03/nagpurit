package com.appxbuild.nagpurit.rest;

import com.appxbuild.nagpurit.entity.Cart;
import com.appxbuild.nagpurit.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CartRestController {

    private CartService cartService;

    @Autowired
    public CartRestController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/cart")
    public List<Cart> findAll(){
        return cartService.findAll();
    }

    @GetMapping("/cart/{id}")
    public Cart getMyCart(@PathVariable int id){
        Cart theCart = cartService.findById(id);
        if(theCart==null){
            throw new RuntimeException("Cart id is not found " + id);
        }
        return theCart;
    }

//    @GetMapping("/cart/login/{loginId}")
//    public ResponseEntity<List<Cart>> getCartByLoginId(@PathVariable int loginId) {
//        List<Cart> theCart = cartService.findAll()
//                .stream()
//                .filter(cart -> cart.getLoginDetails() != null && cart.getLoginDetails().getId() == loginId)
//                .collect(Collectors.toList());
//        return ResponseEntity.ok(theCart);
//    }
    @PostMapping("/cart")
    public Cart addCart(@RequestBody Cart theCart){
        theCart.setId(0);
        LocalDateTime localDateTime = LocalDateTime.now();
        theCart.setCreated(localDateTime);
        theCart.setModified(null);
        Cart newCart = cartService.save(theCart);
        return newCart;
    }

    @PutMapping("/cart")
    public Cart updateCart(@RequestBody Cart theCart){
        Cart existingCart = cartService.findById(theCart.getId());

        if (existingCart == null) {
            throw new RuntimeException("Login Detail with id " + theCart.getId() + " not found");
        }
        theCart.setCreated(existingCart.getCreated());

        LocalDateTime localDateTime = LocalDateTime.now();
        theCart.setModified(localDateTime);
        Cart newCart = cartService.save(theCart);
        return newCart;
    }

    @DeleteMapping("/cart/{id}")
    public String deleteCart(@PathVariable int id){
        Cart theCart = cartService.findById(id);
        if(theCart==null){
            throw new RuntimeException("Cart is not found " + id);
        }
        cartService.deleteById(id);
        return "Deleted Wishlist id " + id;
    }

}
