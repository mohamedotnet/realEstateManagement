package com.RealEstate.service;

import com.RealEstate.dao.LocalityDao;
import com.RealEstate.model.Locality;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class LocalityServiceImpl implements LocalityService{

    @Autowired
    LocalityDao localityDao;

    @Override
    public List<Locality> getLocalitiesList() {
        return localityDao.getLocalitiesList();
    }
}
