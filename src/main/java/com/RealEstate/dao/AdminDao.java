package com.RealEstate.dao;

import com.RealEstate.model.Admin;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AdminDao {

    void storeAdmin(Admin admin);
    Admin readAdminById(int id);
    Admin readAdminByUsername(String username);
    Admin readAdminByIdNumber(String id);
    Admin readAdminByEmail(String email);
    void updateAdmin(Admin admin);
    void deleteAdmin(String username);
    boolean checkAdminLogin(String username, String password);
    List<Admin> getAdminsList();
    String changeProfilePicture(MultipartFile file, String username);
    Admin registerAdmin(String name, String lastName, String username, String sex, String birthday, String phone, String email, String password, String address, String idNumber);


}
