package com.RealEstate.dao;

import com.RealEstate.model.Manager;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ManagerDao {
    void storeManager(Manager manager);
    Manager readManagerByIdNumber(String id);
    Manager readManagerByEmail(String email);
    Manager storeManager(String name, String lastName, String username,String sex, String birthday, String phone,String email, String password, String address, String idNumber);
    Manager readManagerById(int id);
    Manager readManagerByUsername(String username);
    void updateManager(Manager manager);
    void deleteManager(String username);
    boolean checkManagerLogin(String username, String password);
    List<Manager> getManagersList();
    String changeProfilePicture(MultipartFile file, String username);

}
