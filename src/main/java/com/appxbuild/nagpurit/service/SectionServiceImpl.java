package com.appxbuild.nagpurit.service;

import com.appxbuild.nagpurit.dao.SectionDao;
import com.appxbuild.nagpurit.entity.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SectionServiceImpl implements SectionService{


    private SectionDao sectionDao;

    @Autowired
    public SectionServiceImpl(SectionDao theSectionDao){
        sectionDao = theSectionDao;
    }

    @Override
    public List<Section> findAll() {
        return sectionDao.findAll();
    }

    @Override
    public Section findById(int theId) {
        Optional<Section> result = sectionDao.findById(theId);
        Section theSection = null;
        if (result.isPresent()){
            theSection = result.get();
        }
        else {
            throw new RuntimeException("Section id is not found " + theId);
        }
        return theSection;
    }

    @Override
    public Section save(Section section) {
        return sectionDao.save(section);
    }

    @Override
    public void deleteById(int theId) {
        sectionDao.deleteById(theId);
    }

}
