package com.RealEstate.dao;

import com.RealEstate.model.Visit;

import java.util.List;

public interface VisitDao {
    
    void storeVisit(Visit visit);
    Visit readVisitById(int id);
    void updateVisit(Visit visit);
    void deleteVisit(int id);
    List<Visit> getVisitsList();
    List<Visit> getVisitsListByAgent(String username);

}
