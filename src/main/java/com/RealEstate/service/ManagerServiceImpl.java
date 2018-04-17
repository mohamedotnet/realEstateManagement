package com.RealEstate.service;


import com.RealEstate.dao.*;
import com.RealEstate.model.Contract;
import com.RealEstate.model.Manager;
import com.RealEstate.model.Sale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerDao managerDao;

    @Autowired
    private SaleDao saleDao;

    @Autowired
    private ApartmentDao apartmentDao;

    @Autowired
    private ContractDao contractDao;

    public Manager getManagerByUsername(String username) {
        return managerDao.readManagerByUsername(username);
    }

    @Override
    public boolean idExists(String id) {
        return managerDao.readManagerByIdNumber(id) != null;
    }

    @Override
    public boolean emailExists(String email) {
        return managerDao.readManagerByEmail(email) != null;
    }

    @Override
    public boolean userExists(String username) {
        return managerDao.readManagerByUsername(username) !=null;
    }

    public boolean checkManagerLogin(String username, String password) {
        return managerDao.checkManagerLogin(username, password);
    }

    public void validatePayment(String reference, int apartment) {
        saleDao.validateSale(reference);
        apartmentDao.validatePayment(apartment);
    }

    public List<Sale> getSalesList() {
        return saleDao.getSalesList();
    }

    public Contract addContract(MultipartFile file, String sale, String content, String date,String time, String manager) {
        return contractDao.createContract(file, sale, content, date,time, manager);
    }

    public void deleteContract(int id) {
        contractDao.deleteContract(id);
    }

    public void updateContract(Contract contract) {
        contractDao.updateContract(contract);
    }

    public List<Contract> getContractsList() {
        return contractDao.getContractsList();
    }

    public String changeProfilePicture(MultipartFile file, String username) {
        return managerDao.changeProfilePicture(file, username);
    }
}
