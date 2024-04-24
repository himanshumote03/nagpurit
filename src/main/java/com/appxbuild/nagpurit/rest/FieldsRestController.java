package com.appxbuild.nagpurit.rest;

import com.appxbuild.nagpurit.entity.Fields;
import com.appxbuild.nagpurit.service.FieldsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class FieldsRestController {

    private FieldsService fieldsService;

    @Autowired
    public FieldsRestController(FieldsService theFieldsService){
        fieldsService = theFieldsService;
    }

    // add mapping GET "/fields" to get a list of Fields
    @GetMapping("/fields")
    public List<Fields> findAll(){
        return fieldsService.findAll();
    }

    // add mapping GET("/fields/{id}") to get a Field
    @GetMapping("/fields/{id}")
    public Fields getField(@PathVariable int id){
        Fields theFields = fieldsService.findById(id);
        if(theFields==null){
            throw new RuntimeException("Field id is not found " + id);
        }
        return theFields;
    }

    // add mapping POST("/fields") to add a new Field
    @PostMapping("/fields")
    public Fields addField(@RequestBody Fields thefields){
        thefields.setId(0);
        LocalDateTime localDateTime = LocalDateTime.now();
        thefields.setCreated(localDateTime);
        thefields.setModified(null);
        Fields newField = fieldsService.save(thefields);
        return newField;
    }

    // add mapping PUT("/fields") to update an existing Field
    @PutMapping("/fields")
    public Fields updateField(@RequestBody Fields thefields){
        LocalDateTime localDateTime = LocalDateTime.now();
        thefields.setModified(localDateTime);
        Fields newField = fieldsService.save(thefields);
        return newField;
    }

    // add mapping DELETE("fields/{id}") to delete a Field
    @DeleteMapping("fields/{id}")
    public String deleteField(@PathVariable int id){
        Fields theFields = fieldsService.findById(id);
        fieldsService.deleteById(id);
        if(theFields==null){
            throw new RuntimeException("Field id is not found " + id);
        }
        return "Deleted Field id " + id;
    }

}

