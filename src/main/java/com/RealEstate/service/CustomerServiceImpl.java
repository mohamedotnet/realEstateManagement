package com.RealEstate.service;

import com.RealEstate.dao.*;
import com.RealEstate.model.Appointment;
import com.RealEstate.model.Customer;

import com.RealEstate.model.PaymentReceipt;
import com.RealEstate.model.Sale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service

public class CustomerServiceImpl implements CustomerService {
    
    @Autowired
    protected CustomerDao customerDao;

    @Autowired
    protected SaleDao saleDao;

    @Autowired
    protected AppointmentDao appointmentDao;

    @Autowired
    protected ApartmentDao apartmentDao;

    @Autowired
    protected PaymentReceiptDao paymentReceiptDao;


    public Customer getCustomerByUsername(String username) {
        return customerDao.readCustomerByUsername(username);
    }

    public void registerCustomer(Customer customer)
    {
        customerDao.storeCustomer(customer);
    }

    @Override
    public boolean idExists(String id){
        return customerDao.readCustomerById(id) != null;
    }
    @Override
    public boolean emailExists(String email) {
        return customerDao.readCustomerByEmail(email) != null;
    }

    @Override
    public boolean userExists(String username) {
        return customerDao.readCustomerByUsername(username) != null;
    }

    @Override
    public boolean isCustomerValide(String username) {
        return customerDao.isValidated(username);
    }

    public boolean checkCustomerLogin(String username, String password) {
        return customerDao.checkCustomerLogin(username, password);
    }

    public void makeSale(Sale sale) {
        saleDao.storeSale(sale);
    }

    public List<Appointment> getAppointmentsListByCustomer(String username) {
        return appointmentDao.getAppointmentsListByCustomer(username);
    }

    public void cancelAppointment(String reference){
        appointmentDao.deleteAppointment(reference);
    }

    public List<Sale> getSalesList(String username) {
        return saleDao.getSalesListByCustomer(username);
    }

    public void validateSale(String reference, PaymentReceipt paymentReceipt) {
        paymentReceiptDao.storePaymentReceipt(paymentReceipt);
        saleDao.validatePayment(reference, paymentReceipt);
    }

    public String changeProfilePicture(MultipartFile file, String username) {
        return customerDao.changeProfilePicture(file, username);
    }

    public void addAppointment(Appointment appointment) {
        appointmentDao.storeAppointment(appointment);
    }

    public List<Appointment> getAppointmentsList() {
        return appointmentDao.createAppointmentList();
    }

    public PaymentReceipt createPaymentReceipt(MultipartFile file, int value, String bank, String date, String time, String customer) {
        return paymentReceiptDao.createPaymentReceipt(file, value, bank, date, time, customer);
    }

}
