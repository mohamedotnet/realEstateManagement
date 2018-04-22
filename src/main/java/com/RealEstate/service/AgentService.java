package com.RealEstate.service;

import com.RealEstate.model.Agent;
import com.RealEstate.model.Appointment;
import com.RealEstate.model.Report;
import com.RealEstate.model.Visit;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AgentService {
    boolean idExists(String id);
    boolean emailExists(String email);
    boolean userExists(String username);

    boolean checkAgentLogin(String username, String password);
    Agent getAgentByUsername(String username);
    String changeProfilePicture(MultipartFile file, String username);

    void addReport(Report report);
    void removeReport(int id);
    void editReport(Report report);

    void validateAppointment(Appointment appointment, String agent);
    List<Report> getReportsList();
    List<Visit> getVisitsList();
    List<Visit> getVisitsListByAgent(String username);
    List<Appointment> getAppList(Agent agent);
}
