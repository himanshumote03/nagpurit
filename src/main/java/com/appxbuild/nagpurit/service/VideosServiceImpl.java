package com.appxbuild.nagpurit.service;

import com.appxbuild.nagpurit.dao.VideosDao;
import com.appxbuild.nagpurit.entity.Videos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



@Service
public class VideosServiceImpl implements VideosService{

    private VideosDao videosDao;

    @Autowired
    public VideosServiceImpl(VideosDao theVideosDao){
        videosDao = theVideosDao;
    }
    @Override
    public List<Videos> findAll() {
        return videosDao.findAll();
    }

    @Override
    public Videos findById(int theId) {
        Optional<Videos> result = videosDao.findById(theId);
        Videos theVideos = null;
        if (result.isPresent()){
            theVideos = result.get();
        }
        else {
            throw new RuntimeException("Video id is not found " + theId);
        }
        return theVideos;
    }

    @Override
    public Videos save(Videos theVideo) {
        return videosDao.save(theVideo);
    }

    @Override
    public void deleteById(int theId) {
        videosDao.deleteById(theId);
    }
}
