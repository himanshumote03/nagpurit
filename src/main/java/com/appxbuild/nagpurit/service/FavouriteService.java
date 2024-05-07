package com.appxbuild.nagpurit.service;

import com.appxbuild.nagpurit.entity.Favourite;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FavouriteService {

    List<Favourite> findAll();
    Favourite findById(int theId);
    Favourite save(Favourite theFavourite);
    void deleteById(int theId);
}
