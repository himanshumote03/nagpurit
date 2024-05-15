package com.appxbuild.nagpurit.service;

import com.appxbuild.nagpurit.dao.FieldsDetailsDao;
import com.appxbuild.nagpurit.entity.FieldsDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FieldsDetailsServiceImpl implements FieldsDetailsService{

    private FieldsDetailsDao fieldsDetailsDao;

    @Autowired
    public FieldsDetailsServiceImpl(FieldsDetailsDao theFieldsDetailsDao){
        fieldsDetailsDao = theFieldsDetailsDao;
    }


    @Override
    public List<FieldsDetails> findAll() {
        return fieldsDetailsDao.findAll();
    }

    @Override
    public FieldsDetails findById(int theId) {
        Optional<FieldsDetails> result = fieldsDetailsDao.findById(theId);
        FieldsDetails theFieldsDetails = null;
        if(result.isPresent()){
            theFieldsDetails = result.get();
        }
        else {
//            throw  new RuntimeException("Field Details id is not found " + theId);
            return new FieldsDetails();
        }
        return theFieldsDetails;
    }

    @Override
    public FieldsDetails save(FieldsDetails theFieldDetails) {
        return fieldsDetailsDao.save(theFieldDetails);
    }

    @Override
    public void deleteById(int theId) {
        fieldsDetailsDao.deleteById(theId);
    }
}
