package com.appxbuild.nagpurit.rest;

import com.appxbuild.nagpurit.entity.Videos;
import com.appxbuild.nagpurit.service.VideosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class VideosRestController {

    private VideosService videosService;

    @Autowired
    public VideosRestController(VideosService theVideosService){
        videosService = theVideosService;
    }

    // add mapping GET("/videos") to get a list of Videos
    @GetMapping("/videos")
    public List<Videos> findAll(){
        return videosService.findAll();
    }

    // add mapping GET("/video/{id}") to get a Video
    @GetMapping("/videos/{id}")
    public Videos getVideo(@PathVariable int id){
        Videos thevideos = videosService.findById(id);
        if(thevideos==null){
            throw new RuntimeException("Video id is not found " + id);
        }
        return thevideos;
    }

    // add mapping POST("/videos") to add a Video
    @PostMapping("/videos")
    public Videos addVideo(@RequestBody Videos theVideo){
        theVideo.setId(0);
        LocalDateTime localDateTime = LocalDateTime.now();
        theVideo.setCreated(localDateTime);
        theVideo.setModified(null);
        Videos newVideo  = videosService.save(theVideo);
        return newVideo;
    }

    // add mapping PUT("/videos") to update an existing Video
    @PutMapping("/videos")
    public Videos updateVideo(@RequestBody Videos theVideo){
        LocalDateTime localDateTime = LocalDateTime.now();
        theVideo.setModified(localDateTime);
        Videos newVideo  = videosService.save(theVideo);
        return newVideo;
    }

    // add mapping DELETE("/video/{id}") to delete a Video
    @DeleteMapping("/videos/{id}")
    public String deleteVideo(@PathVariable int id){
        Videos theVideo = videosService.findById(id);
        if(theVideo==null){
            throw new RuntimeException("Video id is not found " + id);
        }
        videosService.deleteById(id);
        return "Deleted Video id" + id;
    }

}
