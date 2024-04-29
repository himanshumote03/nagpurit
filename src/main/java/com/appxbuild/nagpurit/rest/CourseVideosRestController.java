package com.appxbuild.nagpurit.rest;

import com.appxbuild.nagpurit.entity.CourseVideos;
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
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CourseVideosRestController {
    private CourseVideosService courseVideosService;

    @Autowired
    public CourseVideosRestController(CourseVideosService courseVideosService) {
        this.courseVideosService = courseVideosService;
    }

    @GetMapping("/courseVideo")
    public List<CourseVideos> findAll() {
        return courseVideosService.findAll();
    }

    @GetMapping("/courseVideo/{id}")
    public CourseVideos findById(@PathVariable int id) {
        CourseVideos courseVideos = courseVideosService.findById(id);
        if(courseVideos==null){
            throw new RuntimeException("Field id is not found " + id);
        }
        return courseVideos;
    }

    @GetMapping("/courseVideo/play/{id}")
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

    @PostMapping("/courseVideo")
    public CourseVideos addCourseVideo(@RequestBody CourseVideos courseVideos) {
        courseVideos.setId(0);
        LocalDateTime localDateTime = LocalDateTime.now();
        courseVideos.setCreated(localDateTime);
        courseVideos.setModified(null);
        CourseVideos newCourseVideos = courseVideosService.save(courseVideos);
        return newCourseVideos;
    }

    @PutMapping("/courseVideo")
    public CourseVideos updateCourseVideo(@RequestBody CourseVideos courseVideos){
        LocalDateTime localDateTime = LocalDateTime.now();
        courseVideos.setModified(localDateTime);
        CourseVideos newCourseVideos = courseVideosService.save(courseVideos);
        return newCourseVideos;
    }

    @DeleteMapping("courseVideo/{id}")
    public String deleteCourseVideo(@PathVariable int id){
        CourseVideos courseVideos = courseVideosService.findById(id);
        if(courseVideos==null){
            throw new RuntimeException("Field id is not found " + id);
        }
        courseVideosService.deleteById(id);
        return "Deleted Field id " + id;
    }

}
