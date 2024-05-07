package com.appxbuild.nagpurit.service;

import com.appxbuild.nagpurit.dao.FavouriteDao;
import com.appxbuild.nagpurit.entity.Favourite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavouriteServiceImpl implements FavouriteService {

    private FavouriteDao favouriteDao;

    @Autowired
    public FavouriteServiceImpl(FavouriteDao favouriteDao) {
        this.favouriteDao = favouriteDao;
    }

    @Override
    public List<Favourite> findAll() {
        return favouriteDao.findAll();
    }

    @Override
    public Favourite findById(int theId) {
        Optional<Favourite> result = favouriteDao.findById(theId);
        Favourite theFavourite = null;
        if(result.isPresent()){
            theFavourite = result.get();
        }
        else {
            throw new RuntimeException("Favourite id is not found " + theId);
        }
        return theFavourite;
    }

    @Override
    public Favourite save(Favourite theFavourite) {
        return favouriteDao.save(theFavourite);
    }

    @Override
    public void deleteById(int theId) {
        favouriteDao.deleteById(theId);
    }
}
