package com.RealEstate.dao;

import com.RealEstate.model.Locality;

import java.util.List;

public interface LocalityDao {
    void storeLocality(Locality locality);
    Locality readLocalityById(int id);
    Locality readLocalityByReference(String name);
    void updateLocality(String query);
    void deleteLocality(Locality locality);
    List<Locality> getLocalitiesList();

}
