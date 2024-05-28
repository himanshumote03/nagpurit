package com.appxbuild.nagpurit.rest;

import com.appxbuild.nagpurit.entity.CareerGoal;
import com.appxbuild.nagpurit.entity.LoginDetails;
import com.appxbuild.nagpurit.service.CareerGoalService;
import com.appxbuild.nagpurit.service.LoginDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CareerGoalRestController {

    private CareerGoalService careerGoalService;
    private LoginDetailsService loginDetailsService;

    @Autowired
    public CareerGoalRestController(CareerGoalService theCareerGoalService, LoginDetailsService theLoginDetailsService){
        careerGoalService = theCareerGoalService;
        loginDetailsService = theLoginDetailsService;
    }

    // add mapping GET("/careerGoal") to get a list of CareerGoal
    @GetMapping("/careerGoal")
    public List<CareerGoal> findAll(){
        return careerGoalService.findAll();
    }

    // add mapping GET("/careerGoal/{id}") to get a CareerGoal
    @GetMapping("/careerGoal/{id}")
    public CareerGoal getCareerGoal(@PathVariable int id){
        CareerGoal theCareerGoal = careerGoalService.findById(id);
        if (theCareerGoal==null){
            throw new RuntimeException("Career Gaol id is not found " + id);
        }
        return theCareerGoal;
    }

    // add mapping GET("/careerGoal) to add a CareerGoal
    @PostMapping("/careerGoal")
    public ResponseEntity<CareerGoal> addOrUpdateCareerGoal(@RequestBody CareerGoal theCareerGoal) {
        LocalDateTime now = LocalDateTime.now();
        CareerGoal existingCareerGoal = careerGoalService.findByLoginId(theCareerGoal.getLoginDetails());

        if (existingCareerGoal != null) {
            // Update existing CareerGoal
            existingCareerGoal.setCurrentGoal(theCareerGoal.getCurrentGoal());
            existingCareerGoal.setFieldsDetails(theCareerGoal.getFieldsDetails());
            existingCareerGoal.setModified(now);
            careerGoalService.save(existingCareerGoal);
            return new ResponseEntity<>(existingCareerGoal, HttpStatus.OK);
        } else {
            // Add new CareerGoal
            theCareerGoal.setId(0);
            theCareerGoal.setCreated(now);
            theCareerGoal.setModified(null);

            // Find the associated LoginDetails
            LoginDetails loginDetails = loginDetailsService.findById(theCareerGoal.getLoginDetails());
            if (loginDetails == null) {
                throw new RuntimeException("LoginDetails id not found - " + theCareerGoal.getLoginDetails());
            }
            theCareerGoal.setLoginDetails(loginDetails);

            CareerGoal newCareerGoal = careerGoalService.save(theCareerGoal);
            return new ResponseEntity<>(newCareerGoal, HttpStatus.CREATED);
        }
    }

    // add mapping POST("/careerGoal") to update an existing CareerGoal
    @PutMapping("/careerGoal")
    public CareerGoal updateCareerGoal(@RequestBody CareerGoal theCareerGoal){

        CareerGoal existingCareerGoal = careerGoalService.findById(theCareerGoal.getId());

        if (existingCareerGoal == null) {
            throw new RuntimeException("Login Detail with id " + theCareerGoal.getId() + " not found");
        }
        theCareerGoal.setCreated(existingCareerGoal.getCreated());

        LocalDateTime localDateTime = LocalDateTime.now();
        theCareerGoal.setModified(localDateTime);

        CareerGoal newCareerGoal = careerGoalService.save(theCareerGoal);
        return newCareerGoal;
    }


    // add mapping DELETE("/careerGoal/{id}") to delete a CareerGoal
    @DeleteMapping("/careerGoal/{id}")
    public String deleteCareerGoal(@PathVariable int id){
        CareerGoal theCareerGoal = careerGoalService.findById(id);
        if(theCareerGoal==null){
            throw new RuntimeException("CareerGoal id is not found " + id);
        }
        careerGoalService.deleteById(id);
        return "Deleted Career Goal id " + id;
    }

}
