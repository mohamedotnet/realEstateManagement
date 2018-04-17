package com.RealEstate.service;

import com.RealEstate.model.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CustomerService {
        boolean idExists(String id);
        boolean emailExists(String email);
        boolean userExists(String username);
        boolean isCustomerValide(String username);
        boolean checkCustomerLogin(String username, String password);
        Customer getCustomerByUsername(String username);
        void registerCustomer(Customer customer);
        void makeSale(Sale sale);
        void addAppointment(Appointment appointment);
        void cancelAppointment(String reference);
        void validateSale(String reference, PaymentReceipt paymentReceipt);
        String changeProfilePicture(MultipartFile file, String username);
        List<Sale> getSalesList(String username);
        List<Appointment> getAppointmentsListByCustomer(String username);
        List<Appointment> getAppointmentsList();
        PaymentReceipt createPaymentReceipt(MultipartFile file, int value, String bank, String date, String time, String customer);
}
