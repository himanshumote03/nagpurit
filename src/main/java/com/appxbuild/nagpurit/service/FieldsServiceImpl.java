package com.appxbuild.nagpurit.service;

import com.appxbuild.nagpurit.dao.FieldsDao;
import com.appxbuild.nagpurit.entity.Fields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FieldsServiceImpl implements FieldsService{

    private FieldsDao fieldsDao;

    @Autowired
    public FieldsServiceImpl(FieldsDao theFieldsDao){
        fieldsDao = theFieldsDao;
    }

    @Override
    public List<Fields> findAll() {
        return fieldsDao.findAll();
    }

    @Override
    public Fields findById(int theId) {
        Optional<Fields> result = fieldsDao.findById(theId);
        Fields theFields = null;
        if(result.isPresent()){
            theFields = result.get();
        }
        else {
            throw new RuntimeException("Field id is not found " + theId);
        }
        return theFields;
    }

    @Override
    public Fields save(Fields theFields) {
        return fieldsDao.save(theFields);
    }

    @Override
    public void deleteById(int theId) {
        fieldsDao.deleteById(theId);
    }
}
