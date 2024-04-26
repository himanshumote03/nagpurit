package com.appxbuild.nagpurit.service;

import com.appxbuild.nagpurit.entity.Videos;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VideosService {

    List<Videos> findAll();
    Videos findById(int theId);
    Videos save(Videos theVideo);
    void deleteById(int theId);

}
