package com.appxbuild.nagpurit.rest;

import com.appxbuild.nagpurit.entity.Favourite;
import com.appxbuild.nagpurit.service.FavouriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FavouriteRestController {

    private FavouriteService favouriteService;

    @Autowired
    public FavouriteRestController(FavouriteService favouriteService) {
        this.favouriteService = favouriteService;
    }

    @GetMapping("/favourite")
    public List<Favourite> findAll(){
        return favouriteService.findAll();
    }

    @GetMapping("/favourite/{id}")
    public Favourite getMyFavourite(@PathVariable int id){
        Favourite theFavourite = favouriteService.findById(id);
        if(theFavourite==null){
            throw new RuntimeException("Favourite id is not found " + id);
        }
        return theFavourite;
    }


    @GetMapping("/favourite/login/{loginId}")
    public ResponseEntity<List<Favourite>> getFavouriteByLoginId(@PathVariable int loginId) {
        List<Favourite> theFavourite = favouriteService.findAll()
                .stream()
                .filter(Favourite -> Favourite.getLoginDetails() != null && Favourite.getLoginDetails().getId() == loginId)
                .collect(Collectors.toList());
        return ResponseEntity.ok(theFavourite);
    }


    @PostMapping("/favourite")
    public Favourite addFavourite(@RequestBody Favourite theFavourite){
        theFavourite.setId(0);
        LocalDateTime localDateTime = LocalDateTime.now();
        theFavourite.setCreated(localDateTime);
        theFavourite.setModified(null);
        Favourite newFavourite = favouriteService.save(theFavourite);
        return newFavourite;
    }

    @PutMapping("/favourite")
    public Favourite updateFavourite(@RequestBody Favourite theFavourite){
        Favourite existingFavourite = favouriteService.findById(theFavourite.getId());

        if (existingFavourite == null) {
            throw new RuntimeException("Login Detail with id " + theFavourite.getId() + " not found");
        }
        theFavourite.setCreated(existingFavourite.getCreated());

        LocalDateTime localDateTime = LocalDateTime.now();
        theFavourite.setModified(localDateTime);
        Favourite newFavourite = favouriteService.save(theFavourite);
        return newFavourite;
    }

    @DeleteMapping("/favourite/{id}")
    public String deleteFavourite(@PathVariable int id){
        Favourite theFavourite = favouriteService.findById(id);
        if(theFavourite==null){
            throw new RuntimeException("Favourite is not found " + id);
        }
        favouriteService.deleteById(id);
        return "Deleted Wishlist id " + id;
    }

}
