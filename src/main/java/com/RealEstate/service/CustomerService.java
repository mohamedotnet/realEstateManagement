package com.RealEstate.service;

import com.RealEstate.model.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public interface CustomerService {
        void fixApp(Appointment app);
        Appointment chooseApp(Date date, Time time, String username, String apartment);
        boolean idExists(String id);
        boolean emailExists(String email);
        boolean userExists(String username);
        boolean isCustomerValide(String username);
        boolean checkCustomerLogin(String username, String password);
        Customer getCustomerByUsername(String username);
        void registerCustomer(Customer customer);
        void makeSale(Sale sale);
        //void addAppointment(Appointment appointment);
        void cancelAppointment(String reference);
        void validateSale(String reference, PaymentReceipt paymentReceipt);
        String changeProfilePicture(MultipartFile file, String username);
        List<Sale> getSalesList(String username);
        List<Customer> getCustomerByName(String name);
        List<Appointment> getAppointmentsListByCustomer(String username);
        List<Appointment> getAllAppointmentsListByCustomer(String username);
        List<Apartment> getAppartmentList();
        Apartment getAppartmentByRef(String ref);
        //List<Appointment> getAppointmentsList();
        PaymentReceipt createPaymentReceipt(MultipartFile file, int value, String bank, String date, String time, String customer);
}
