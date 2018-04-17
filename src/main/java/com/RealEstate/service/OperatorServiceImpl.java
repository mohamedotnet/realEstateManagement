package com.RealEstate.service;


import com.RealEstate.dao.*;
import com.RealEstate.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class OperatorServiceImpl implements OperatorService {

    @Autowired
    protected OperatorDao operatorDao;

    @Autowired
    protected CustomerDao customerDao;

    @Autowired
    protected ApartmentDao apartmentDao;

    @Autowired
    protected BuildingDao buildingDao;

    @Autowired
    protected LocalityDao localityDao;

    @Autowired
    protected AppointmentDao appointmentDao;

    public OperatorServiceImpl() {

    }

    public Operator getOperatorByUsername(String username) {
        return operatorDao.readOperatorByUsername(username);
    }

    @Override
    public boolean idExists(String id) {
        return operatorDao.readOperatorByIdNumber(id) != null;
    }

    @Override
    public boolean emailExists(String email) {
        return operatorDao.readOperatorByEmail(email) != null;
    }

    @Override
    public boolean userExists(String username) {
        return operatorDao.readOperatorByUsername(username) != null;
    }

    public boolean checkOperatorLogin(String username, String password) {
        return operatorDao.checkOperatorLogin(username, password);
    }

    public void addCustomer(Customer customer)
    {
        customerDao.storeCustomer(customer);
    }

    public void addApartment(Apartment apartment) {
        apartmentDao.storeApartment(apartment);
    }

    public void addBuilding(Building building) {
        buildingDao.storeBuilding(building);
    }

    public void addLocality(Locality locality) {
        localityDao.storeLocality(locality);
    }

    public void addAppointment(Appointment appointment) {
        appointmentDao.storeAppointment(appointment);
    }

    public void deleteAppointment(Appointment appointment) {
        appointmentDao.deleteAppointment(appointment);
    }

    public void fixAppointment(String reference, String customer, String agent) {
        //appointmentDao.fixAppointment(reference, customer, agent);
    }

    public void cancelAppointment(String reference){
        appointmentDao.deleteAppointment(reference);
    }

    public List<Appointment> getAppointmentsList() {
        return appointmentDao.getAppointmentsList();
    }

    public List<Appointment> createAppointmentsList() {
        return appointmentDao.createAppointmentList();
    }

    public String changeProfilePicture(MultipartFile file, String username) {
        return operatorDao.changeProfilePicture(file, username);
    }

    public Apartment createApartment(String reference, String price, String floor, String type, String nbrRoom, String building, String surface, String nbrBalcony, MultipartFile[] pictures) {
        return apartmentDao.createApartment(reference, price, floor, type, nbrRoom, building, surface, nbrBalcony, pictures);
    }

    public Customer createCustomer(String name, String lastName, String username, String sex, String birthday, String phone, String email, String password, String address, String idNumber) {
        return customerDao.createCustomer(name, lastName, username, sex, birthday, phone, email, password, address, idNumber);
    }

    public Operator createOperator(String name, String lastName, String username, String sex, String birthday, String phone, String email, String password, String address, String idNumber) {
        return operatorDao.storeOperator(name, lastName, username, sex, birthday, phone, email, password, address, idNumber);
    }
}
