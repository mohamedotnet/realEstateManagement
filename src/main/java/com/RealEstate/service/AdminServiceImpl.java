package com.RealEstate.service;

import com.RealEstate.dao.*;
import com.RealEstate.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    protected AdminDao adminDao;

    @Autowired
    protected OperatorDao operatorDao;

    @Autowired
    protected AgentDao agentDao;

    @Autowired
    protected ManagerDao managerDao;

    @Autowired
    protected CustomerDao customerDao;

    public AdminServiceImpl() {

    }

    public AdminDao getAdminDao() {
        return adminDao;
    }

    public void setAdminDao(AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    public OperatorDao getOperatorDao() {
        return operatorDao;
    }

    public void setOperatorDao(OperatorDao operatorDao) {
        this.operatorDao = operatorDao;
    }

    public AgentDao getAgentDao() {
        return agentDao;
    }

    public void setAgentDao(AgentDao agentDao) {
        this.agentDao = agentDao;
    }

    public ManagerDao getManagerDao() {
        return managerDao;
    }

    public void setManagerDao(ManagerDao managerDao) {
        this.managerDao = managerDao;
    }

    public CustomerDao getCustomerDao() {
        return customerDao;
    }

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public Admin getAdminByUsername(String username) {
        return adminDao.readAdminByUsername(username);
    }

    @Override
    public boolean idExists(String id) {
        return adminDao.readAdminByIdNumber(id) != null;
    }

    @Override
    public boolean emailExists(String email) {
        return adminDao.readAdminByEmail(email) != null;
    }

    @Override
    public boolean userExists(String username) {
        return adminDao.readAdminByUsername(username) != null;
    }

    public boolean checkAdminLogin(String username, String password) {
        return adminDao.checkAdminLogin(username, password);
    }

    public void addAgent(Agent agent) {
        agentDao.storeAgent(agent);
    }

    public void registerAdmin(Admin admin) {
        adminDao.storeAdmin(admin);
    }
    @Override
    public Admin registerAdmin(String name, String lastName, String username, String sex, String birthday, String phone, String email, String password, String address, String idNumber) {
        return adminDao.registerAdmin(name, lastName, username, sex, birthday, phone, email, password, address, idNumber);
    }

    @Override
    public Operator addOperator(String name, String lastName, String username, String sex, String birthday, String phone, String email, String password, String address, String idNumber) {
        return operatorDao.storeOperator(name, lastName, username, sex, birthday, phone, email, password, address, idNumber);
    }

    @Override
    public Manager addManager(String name, String lastName, String username, String sex, String birthday, String phone, String email, String password, String address, String idNumber) {
        return managerDao.storeManager(name, lastName, username, sex, birthday, phone, email, password, address, idNumber);
    }

    @Override
    public Customer addCustomer(String name, String lastName, String username, String sex, String birthday, String phone, String email, String password, String address, String idNumber) {
        return customerDao.createCustomer(name, lastName, username, sex, birthday, phone, email, password, address, idNumber);
    }

    @Override
    public Agent addAgent(String name, String lastName, String username, String sex, String birthday, String phone, String email, String password, String address, String idNumber) {
        return agentDao.createAgent(name, lastName, username, sex, birthday, phone, email, password, address, idNumber);
    }

    public void addOperator(Operator operator){
        operatorDao.storeOperator(operator);
    }

    public void addManager(Manager manager){
        managerDao.storeManager(manager);
    }

    public void addCustomer(Customer customer) {
        customerDao.storeCustomer(customer);
    }

    public void validateAccount(String username) {
        customerDao.validateAccount(username);
    }

    public void editAdmin(Admin admin) {
        adminDao.updateAdmin(admin);
    }

    public void editAgent(Agent agent) {
        agentDao.updateAgent(agent);
    }

    public void editOperator(Operator operator) {
        operatorDao.updateOperator(operator);
    }

    public void editManager(Manager manager) {
        managerDao.updateManager(manager);
    }

    public void editCustomer(Customer customer) {
        customerDao.updateCustomer(customer);
    }

    public void removeAdmin(String username) {
        adminDao.deleteAdmin(username);
    }

    public void removeAgent(String username) {
        agentDao.deleteAgent(username);
    }

    public void removeOperator(String username) {
        operatorDao.deleteOperator(username);
    }

    public void removeManager(String username) {
        managerDao.deleteManager(username);
    }

    public void removeCustomer(String username) {
        customerDao.deleteCustomer(username);
    }

    public List<Admin> getAdminsList() {
        return adminDao.getAdminsList();
    }

    public List<Agent> getAgentsList() {
        return agentDao.getAgentsList();
    }

    public List<Customer> getCustomersList() {
        return customerDao.getCustomersList();
    }

    public List<Customer> getInactiveAccount(){
        return customerDao.getCustomerListUnActive();
    }
    public List<Operator> getOperatorsList() {
        return operatorDao.getOperatorsList();
    }

    public List<Manager> getManagersList() {
        return managerDao.getManagersList();
    }

    public String changeProfilePicture(MultipartFile file, String picture) {
        return adminDao.changeProfilePicture(file, picture);
    }

}
