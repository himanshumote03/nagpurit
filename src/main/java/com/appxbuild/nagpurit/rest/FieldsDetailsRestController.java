package com.appxbuild.nagpurit.rest;

import com.appxbuild.nagpurit.entity.FieldsDetails;
import com.appxbuild.nagpurit.service.FieldsDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FieldsDetailsRestController {

    private FieldsDetailsService fieldsDetailsService;

    @Autowired
    public FieldsDetailsRestController(FieldsDetailsService theFieldsDetailsService){
        fieldsDetailsService = theFieldsDetailsService;
    }

    // add mapping for GET("/fieldsDetails") to get a list of FieldsDetails
    @GetMapping("/fieldsDetails")
    public List<FieldsDetails> findAll(){
        return fieldsDetailsService.findAll();
    }

    // add mapping for GET("/fieldsDetails/{id}") to get a FieldsDetails
    @GetMapping("fieldsDetails/{id}")
    public FieldsDetails getFieldsDetails(@PathVariable int id){
        FieldsDetails theFieldsDetails = fieldsDetailsService.findById(id);
        if(theFieldsDetails==null){
            throw new RuntimeException("Fields Details id is not found " + id);
         }
        return theFieldsDetails;
    }

    // add mapping for POST("/fieldsDetails") to add a FieldsDetails
    @PostMapping("/fieldsDetails")
    public FieldsDetails addFieldsDetails(@RequestBody FieldsDetails theFieldsDetails){
        theFieldsDetails.setId(0);
        LocalDateTime localDateTime = LocalDateTime.now();
        theFieldsDetails.setCreated(localDateTime);
        theFieldsDetails.setModified(null);
        FieldsDetails newFieldsDetails = fieldsDetailsService.save(theFieldsDetails);
        return newFieldsDetails;
    }

    // add mapping for PUT("/fieldsDetails") to update an existing FieldsDetails
    @PutMapping("/fieldsDetails")
    public FieldsDetails updateFieldsDetails(@RequestBody FieldsDetails theFieldsDetails){
        LocalDateTime localDateTime = LocalDateTime.now();
        theFieldsDetails.setModified(localDateTime);
        FieldsDetails newFieldsDetails = fieldsDetailsService.save(theFieldsDetails);
        return newFieldsDetails;
    }

    // add mapping for DELETE("/fieldsDetails/{id}") to delete a FieldsDetails
    @DeleteMapping("fieldsDetails/{id}")
    public String deleteFieldsDetails(@PathVariable int id){
        FieldsDetails theFieldsDetails = fieldsDetailsService.findById(id);
        if(theFieldsDetails==null){
            throw new RuntimeException("Fields Details id is not found " + id);
        }
        fieldsDetailsService.deleteById(id);
        return "Deleted Fields Details id " + id;
    }

}
