package com.appxbuild.nagpurit.rest;

import com.appxbuild.nagpurit.entity.Section;
import com.appxbuild.nagpurit.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SectionRestController {
    private SectionService sectionService;
    @Autowired
    public SectionRestController(SectionService theSectionService){
        sectionService = theSectionService;
    }

    // add mapping GET("/section") to get list of Sections
    @GetMapping("/section")
    public List<Section> findAll(){
        return sectionService.findAll();
    }

    // add mapping GET("/section/{id}") to get a Section
    @GetMapping("/section/{id}")
    public Section getSection(@PathVariable int id){
        Section theSection = sectionService.findById(id);
        if(theSection==null){
            throw new RuntimeException("Section id is not found " + id);
        }
        return theSection;
    }

    // add mapping POST("/section") to add a Section
    @PostMapping("/section")
    public Section addSection(@RequestBody Section theSection){
        theSection.setId(0);
        LocalDateTime localDateTime = LocalDateTime.now();
        theSection.setCreated(localDateTime);
        Section newSection = sectionService.save(theSection);
        return newSection;
    }

    // add mapping PUT("/section") to update an existing of Section
    @PutMapping("/section")
    public Section updateSection(@RequestBody Section theSection){
        LocalDateTime localDateTime = LocalDateTime.now();
        theSection.setModified(localDateTime);
        Section newSection = sectionService.save(theSection);
        return newSection;
    }

    // add mapping DELETE("/section/{id}") to delete a Section
    @DeleteMapping("/section/{id}")
    public String deleteSection(@PathVariable int id){
        Section theSection = sectionService.findById(id);
        if(theSection==null){
            throw new RuntimeException("Section id is not found " + id);
        }
        sectionService.deleteById(id);
        return "Deleted Section id" + id;
    }

}
