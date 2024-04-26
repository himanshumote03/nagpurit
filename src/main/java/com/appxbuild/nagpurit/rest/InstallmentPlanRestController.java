package com.appxbuild.nagpurit.rest;

import com.appxbuild.nagpurit.entity.InstallmentPlan;
import com.appxbuild.nagpurit.service.InstallmentPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class InstallmentPlanRestController {

    private InstallmentPlanService installmentPlanService;

    @Autowired
    public InstallmentPlanRestController(InstallmentPlanService installmentPlanService) {
        this.installmentPlanService = installmentPlanService;
    }
    

    @GetMapping("/installmentPlan")
    public List<InstallmentPlan> findAll() {
        return installmentPlanService.findAll();
    }

    @GetMapping("/installmentPlan/{id}")
    public InstallmentPlan getInstallmentPlanById(@PathVariable int id) {
        InstallmentPlan installmentPlan = installmentPlanService.findById(id);
        if (installmentPlan == null) {
            throw new RuntimeException("installmentPlan id is not found " + id);
        }
        return installmentPlan;
    }


    @PostMapping("/installmentPlan")
    public InstallmentPlan addInstallmentPlan(@RequestBody InstallmentPlan installmentPlan) {
        installmentPlan.setId(0);
        LocalDateTime dt = LocalDateTime.now();
        installmentPlan.setCreated(dt);
        InstallmentPlan newInstallmentPlan = installmentPlanService.save(installmentPlan);
        return newInstallmentPlan;
    }

    @PutMapping("/installmentPlan")
    public InstallmentPlan updateInstallmentPlan(@RequestBody InstallmentPlan installmentPlan) {
        InstallmentPlan existinginstallmentPlan = installmentPlanService.findById(installmentPlan.getId());

        if (existinginstallmentPlan == null) {
            throw new RuntimeException("Installment plan with id " + installmentPlan.getId() + " not found");
        }
        installmentPlan.setCreated(existinginstallmentPlan.getCreated());
        LocalDateTime dt = LocalDateTime.now();
        installmentPlan.setModified(dt);

        InstallmentPlan newInstallmentPlan = installmentPlanService.save(installmentPlan);
        return newInstallmentPlan;
    }

    @DeleteMapping("/installmentPlan/{id}")
    public String deleteInstallmentPlan(@PathVariable int id) {
        InstallmentPlan installmentPlan = installmentPlanService.findById(id);
        if (installmentPlan == null) {
            throw new RuntimeException("installmentPlan id is not found " + id);
        }
        installmentPlanService.deleteById(id);
        return ("Deleted installmentPlan id " + id);
    }
}
