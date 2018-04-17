package com.RealEstate.dao;

import com.RealEstate.model.Apartment;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ApartmentDao {
    Apartment createApartment(String reference, String price, String floor, String type, String nbrRoom, String building, String surface, String nbrBalcony, MultipartFile[] pictures);
    void storeApartment(Apartment apartment);
    Apartment readApartmentById(int id);
    Apartment readApartmentByReference(String reference);
    void updateApartment(String query);
    void deleteApartment(Apartment apartment);
    void validatePayment(int id);
    List<Apartment> apartmentList();
    List<Apartment> customApartmentsList(String floor, String nbrBalcony, String locality, String type, String minPrice, String maxPrice, String minSurface, String maxSurface);
}


