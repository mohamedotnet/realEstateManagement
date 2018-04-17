package com.RealEstate.service;

import com.RealEstate.dao.ApartmentDao;
import com.RealEstate.model.Apartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    protected ApartmentDao apartmentDao;

    public List<Apartment> getApartmentsList() {
        return apartmentDao.apartmentList();
    }

}
