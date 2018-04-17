package com.RealEstate.dao;

import com.RealEstate.model.Operator;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface OperatorDao {
    void storeOperator(Operator operator);
    Operator readOperatorByIdNumber(String id);
    Operator readOperatorByEmail(String email);
    Operator storeOperator(String name, String lastName, String username, String sex, String birthday, String phone, String email, String password, String address, String idNumber);
    Operator readOperatorById(int id);
    Operator readOperatorByUsername(String username);
    void updateOperator(Operator operator);
    void deleteOperator(String username);
    boolean checkOperatorLogin(String username, String password);
    List<Operator> getOperatorsList();
    String changeProfilePicture(MultipartFile file, String username);

}
