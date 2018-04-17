package com.RealEstate.service;

import com.RealEstate.model.Contract;
import com.RealEstate.model.Manager;
import com.RealEstate.model.Sale;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ManagerService {

    boolean idExists(String id);
    boolean emailExists(String email);
    boolean userExists(String username);

    boolean checkManagerLogin(String username, String password);
    Manager getManagerByUsername(String username);
    void validatePayment(String reference, int apartment);
    String changeProfilePicture(MultipartFile file, String username);

    Contract addContract(MultipartFile file, String sale, String content, String date, String time, String manager);
    void deleteContract(int id);
    void updateContract(Contract contract);

    List<Sale> getSalesList();
    List<Contract> getContractsList();
}
