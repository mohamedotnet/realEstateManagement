package com.RealEstate.service;

import com.RealEstate.model.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface OperatorService {
    boolean idExists(String id);
    boolean emailExists(String email);
    boolean userExists(String username);
    boolean checkOperatorLogin(String username, String password);
    Operator getOperatorByUsername(String username);
    String changeProfilePicture(MultipartFile file, String username);
    void addCustomer(Customer customer);
    void addApartment(Apartment apartment);
    void addBuilding(Building building);
    void addLocality(Locality locality);
    void addAppointment(Appointment appointment);
    void deleteAppointment(Appointment appointment);
    void cancelAppointment(String reference);
    void fixAppointment(String reference, String customer, String agent);
    //List<Appointment> createAppointmentsList();
    //List<Appointment> getAppointmentsList();
    Apartment createApartment(String reference, String price, String floor, String type, String nbrRoom, String building, String surface, String nbrBalcony, MultipartFile[] pictures);
    Customer createCustomer(String name, String lastName, String username, String sex, String birthday, String phone, String email, String password, String address, String idNumber);
    Operator createOperator(String name, String lastName, String username, String sex, String birthday, String phone, String email, String password, String address, String idNumber);
    List<Locality> getLocalityList();
    List<Building> getBuildingList();
    List<Appointment> getAppList();

}
