package com.RealEstate.dao;

import com.RealEstate.model.Agent;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AgentDao {

    void storeAgent(Agent agent);
    Agent readAgentById(int id);
    Agent readAgentByUsername(String username);
    Agent readAgentByIdNumber(String id);
    Agent readAgentByEmail(String email);
    void updateAgent(Agent agent);
    void deleteAgent(String username);
    boolean checkAgentLogin(String username, String password);
    List<Agent> getAgentsList();
    String changeProfilePicture(MultipartFile file, String username);
    Agent createAgent(String name, String lastName, String username, String sex, String birthday, String phone, String email, String password, String address, String idNumber);


}
