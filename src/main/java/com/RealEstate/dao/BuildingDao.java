package com.RealEstate.dao;

import com.RealEstate.model.Building;

import java.util.List;

public interface BuildingDao {

    void storeBuilding(Building building);
    Building readBuildingById(int id);
    Building readBuildingByReference(String reference);
    void updateBuilding(String query);
    void deleteBuilding(Building building);
    List<Building> getBuildingsList();
    
}
