package com.appxbuild.nagpurit.service;

import com.appxbuild.nagpurit.dao.InstallmentPlanDao;
import com.appxbuild.nagpurit.entity.CourseVideos;
import com.appxbuild.nagpurit.entity.InstallmentPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstallmentPlanServiceImpl implements InstallmentPlanService {

    private InstallmentPlanDao installmentPlanDao;

    @Autowired
    public InstallmentPlanServiceImpl(InstallmentPlanDao installmentPlanDao) {
        this.installmentPlanDao = installmentPlanDao;
    }

    @Override
    public List<InstallmentPlan> findAll() {
        return installmentPlanDao.findAll();
    }

    @Override
    public InstallmentPlan findById(int id) {
        Optional<InstallmentPlan> result = installmentPlanDao.findById(id);
        InstallmentPlan installmentPlan = null;
        if(result.isPresent()){
            installmentPlan = result.get();
        }
        else {
            throw  new RuntimeException("Installment plan id is not found " + id);
        }
        return installmentPlan;
    }

    @Override
    public InstallmentPlan save(InstallmentPlan installmentPlan) {
        return installmentPlanDao.save(installmentPlan);
    }

    @Override
    public void deleteById(int id) {
        installmentPlanDao.deleteById(id);
    }
}
