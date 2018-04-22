package com.RealEstate.dao;

import com.RealEstate.model.Customer;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CustomerDao {

    Customer readCustomerByEmail(String email);
    void storeCustomer(Customer customer);
    Customer readCustomerById(String id);
    Customer readCustomerByUsername(String username);
    void updateCustomer(Customer customer);
    void deleteCustomer(String username);
    boolean isValidated(String username);
    boolean checkCustomerLogin(String username, String password);
    void validateAccount(String username);
    String changeProfilePicture(MultipartFile file, String username);
    List<Customer> getCustomersList();
    List<Customer> getCustomerListUnActive();
    List<Customer> getCustomerByName(String name);
    Customer createCustomer(String name, String lastName, String username, String sex, String birthday, String phone, String email, String password, String address, String idNumber);
}


