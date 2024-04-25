package com.appxbuild.nagpurit.rest;

import com.appxbuild.nagpurit.entity.CourseVideos;
import com.appxbuild.nagpurit.entity.Fields;
import com.appxbuild.nagpurit.service.CourseVideosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CourseVideosRestController {
    private CourseVideosService courseVideosService;

    @Autowired
    public CourseVideosRestController(CourseVideosService courseVideosService) {
        this.courseVideosService = courseVideosService;
    }

    @GetMapping("/video")
    public List<CourseVideos> findAll() {
        return courseVideosService.findAll();
    }

    @GetMapping("/video/{id}")
    public CourseVideos findById(@PathVariable int id) {
        CourseVideos courseVideos = courseVideosService.findById(id);
        if(courseVideos==null){
            throw new RuntimeException("Field id is not found " + id);
        }
        return courseVideos;
    }

    @GetMapping("/video/play/{id}")
    public ResponseEntity<?> getVideoById(@PathVariable int id) {
        CourseVideos video = courseVideosService.findById(id);
        if (video == null) {
            return ResponseEntity.notFound().build();
        }

        String link = video.getVideoUrl();

        // Redirect users to the YouTube link
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(ServletUriComponentsBuilder.fromUriString(link).build().toUri())
                .build();
    }

    @PostMapping("/video")
    public CourseVideos addCourseVideo(@RequestBody CourseVideos courseVideos) {
        courseVideos.setId(0);
        LocalDateTime localDateTime = LocalDateTime.now();
        courseVideos.setCreated(localDateTime);
        courseVideos.setModified(null);
        CourseVideos newCourseVideos = courseVideosService.save(courseVideos);
        return newCourseVideos;
    }

    @PutMapping("/video")
    public CourseVideos updateCourseVideo(@RequestBody CourseVideos courseVideos){
        LocalDateTime localDateTime = LocalDateTime.now();
        courseVideos.setModified(localDateTime);
        CourseVideos newCourseVideos = courseVideosService.save(courseVideos);
        return newCourseVideos;
    }

    @DeleteMapping("video/{id}")
    public String deleteCourseVideo(@PathVariable int id){
        CourseVideos courseVideos = courseVideosService.findById(id);
        if(courseVideos==null){
            throw new RuntimeException("Field id is not found " + id);
        }
        courseVideosService.deleteById(id);
        return "Deleted Field id " + id;
    }

}
