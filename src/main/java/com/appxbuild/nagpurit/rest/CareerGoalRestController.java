package com.appxbuild.nagpurit.rest;

import com.appxbuild.nagpurit.entity.CareerGoal;
import com.appxbuild.nagpurit.service.CareerGoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CareerGoalRestController {

    private CareerGoalService careerGoalService;

    @Autowired
    public CareerGoalRestController(CareerGoalService theCareerGoalService){
        careerGoalService = theCareerGoalService;
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
    public CareerGoal addCareerGoal(@RequestBody CareerGoal theCareerGoal){
        theCareerGoal.setId(0);
        LocalDateTime localDateTime = LocalDateTime.now();
        theCareerGoal.setCreated(localDateTime);
        theCareerGoal.setModified(null);
        CareerGoal newCareerGoal = careerGoalService.save(theCareerGoal);
        return newCareerGoal;
    }

    // add mapping POST("/careerGoal") to update an existing CareerGoal
    @PutMapping("/careerGoal")
    public CareerGoal updateCareerGoal(@RequestBody CareerGoal theCareerGoal){

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
