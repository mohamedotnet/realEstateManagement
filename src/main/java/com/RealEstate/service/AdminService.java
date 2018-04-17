package com.RealEstate.service;

import com.RealEstate.model.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AdminService {
    boolean idExists(String id);
    boolean emailExists(String email);
    boolean userExists(String username);
    boolean checkAdminLogin(String username, String password);
    Admin getAdminByUsername(String username);
    String changeProfilePicture(MultipartFile file, String username);
    void registerAdmin(Admin admin);
    void addOperator(Operator operator);
    void addManager(Manager manager);
    void addCustomer(Customer customer);
    void addAgent(Agent agent);
    Admin registerAdmin(String name, String lastName, String username,String sex, String birthday, String phone,String email, String password, String address, String idNumber);
    Operator addOperator(String name, String lastName, String username,String sex, String birthday, String phone,String email, String password,String address, String idNumber);
    Manager addManager(String name, String lastName, String username,String sex, String birthday, String phone,String email, String password, String address, String idNumber);
    Customer addCustomer(String name, String lastName, String username,String sex, String birthday, String phone,String email, String password, String address, String idNumber);
    Agent addAgent(String name, String lastName, String username,String sex, String birthday, String phone,String email, String password, String address, String idNumber);
    void validateAccount(String username);
    void editAdmin(Admin admin);
    void editAgent(Agent agent);
    void editOperator(Operator operator);
    void editManager(Manager manager);
    void editCustomer(Customer customer);
    void removeAdmin(String username);
    void removeAgent(String username);
    void removeOperator(String username);
    void removeManager(String username);
    void removeCustomer(String username);
    List<Admin> getAdminsList();
    List<Agent> getAgentsList();
    List<Customer> getCustomersList();
    List<Customer> getInactiveAccount();
    List<Operator> getOperatorsList();
    List<Manager> getManagersList();
}












