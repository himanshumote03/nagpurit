package com.appxbuild.nagpurit.rest;

import com.appxbuild.nagpurit.entity.Wishlist;
import com.appxbuild.nagpurit.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class WishlistRestController {

    private WishlistService wishlistService;

    @Autowired
    public WishlistRestController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @GetMapping("/wishlist")
    public List<Wishlist> findAll(){
        return wishlistService.findAll();
    }

    @GetMapping("/wishlist/{id}")
    public Wishlist getWishlist(@PathVariable int id){
        Wishlist theWishlist = wishlistService.findById(id);
        if(theWishlist==null){
            throw new RuntimeException("Wishlist id is not found " + id);
        }
        return theWishlist;
    }

    @GetMapping("/wishlist/login/{loginId}")
    public ResponseEntity<List<Wishlist>> getWishlistByLoginId(@PathVariable int loginId) {
        List<Wishlist> theWishlist = wishlistService.findAll()
                .stream()
                .filter(wishlist -> wishlist.getLoginDetails() != null && wishlist.getLoginDetails().getId() == loginId)
                .collect(Collectors.toList());
        return ResponseEntity.ok(theWishlist);
    }

    @PostMapping("/wishlist")
    public Wishlist addWishlist(@RequestBody Wishlist theWishlist){
        theWishlist.setId(0);
        LocalDateTime localDateTime = LocalDateTime.now();
        theWishlist.setCreated(localDateTime);
        theWishlist.setModified(null);
        Wishlist newWishlist = wishlistService.save(theWishlist);
        return newWishlist;
    }

    @PutMapping("/wishlist")
    public Wishlist updateMyCourse(@RequestBody Wishlist theWishlist){
        Wishlist existingWishlist = wishlistService.findById(theWishlist.getId());

        if (existingWishlist == null) {
            throw new RuntimeException("Login Detail with id " + theWishlist.getId() + " not found");
        }
        theWishlist.setCreated(existingWishlist.getCreated());

        LocalDateTime localDateTime = LocalDateTime.now();
        theWishlist.setModified(localDateTime);
        Wishlist newWishlist = wishlistService.save(theWishlist);
        return newWishlist;
    }

    @DeleteMapping("/wishlist/{id}")
    public String deleteWishlist(@PathVariable int id){
        Wishlist theWishlist = wishlistService.findById(id);
        if(theWishlist==null){
            throw new RuntimeException("Wishlist is not found " + id);
        }
        wishlistService.deleteById(id);
        return "Deleted Wishlist id " + id;
    }

}
