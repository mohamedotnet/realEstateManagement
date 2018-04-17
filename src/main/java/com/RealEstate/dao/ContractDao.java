package com.RealEstate.dao;

import com.RealEstate.model.Contract;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ContractDao {
    void storeContract(Contract contract);
    Contract readContractById(int id);
    void updateContract(Contract contract);
    void deleteContract(int id);
    List<Contract> getContractsList();
    Contract createContract(MultipartFile file, String sale, String content, String date, String time, String manager);

}
