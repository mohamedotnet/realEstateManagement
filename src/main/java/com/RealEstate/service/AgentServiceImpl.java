package com.RealEstate.service;


import com.RealEstate.dao.AgentDao;
import com.RealEstate.dao.AppointmentDao;
import com.RealEstate.dao.ReportDao;
import com.RealEstate.dao.VisitDao;
import com.RealEstate.model.Agent;
import com.RealEstate.model.Appointment;
import com.RealEstate.model.Report;
import com.RealEstate.model.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class AgentServiceImpl implements AgentService {

    @Autowired
    protected AgentDao agentDao;

    @Autowired
    protected ReportDao reportDao;

    @Autowired
    protected VisitDao visitDao;

    @Autowired
    AppointmentDao appointmentDao;
    public AgentServiceImpl() {

    }

    public AgentDao getAgentDao() {
        return agentDao;
    }

    public void setAgentDao(AgentDao agentDao) {
        this.agentDao = agentDao;
    }

    public ReportDao getReportDao() {
        return reportDao;
    }

    public void setReportDao(ReportDao reportDao) {
        this.reportDao = reportDao;
    }

    public VisitDao getVisitDao() {
        return visitDao;
    }

    public void setVisitDao(VisitDao visitDao) {
        this.visitDao = visitDao;
    }

    public Agent getAgentByUsername(String username) {
        return agentDao.readAgentByUsername(username);
    }

    @Override
    public boolean idExists(String id) {
        return agentDao.readAgentByIdNumber(id) != null;
    }

    @Override
    public boolean emailExists(String email) {
        return agentDao.readAgentByEmail(email) != null;
    }

    @Override
    public boolean userExists(String username) {
        return agentDao.readAgentByUsername(username) != null;
    }

    public boolean checkAgentLogin(String username, String password) {
        return agentDao.checkAgentLogin(username, password);
    }

    public void addReport(Report report) {
        reportDao.storeReport(report);
    }

    public void removeReport(int id) {
        reportDao.deleteReport(id);
    }

    public void editReport(Report report) {
        reportDao.updateReport(report);
    }

    @Override
    public void validateAppointment(Appointment appointment, String agent) {
        appointmentDao.confirmAppointment(appointment, agent);
    }

    public List<Report> getReportsList() {
        return reportDao.getReportsList();
    }

    public List<Visit> getVisitsList() {
        return visitDao.getVisitsList();
    }

    public List<Visit> getVisitsListByAgent(String username) {
        return visitDao.getVisitsListByAgent(username);
    }

    @Override
    public List<Appointment> getAppList(Agent agent) {
        return appointmentDao.getAppointmentsListByLocality(agent);
    }

    public String changeProfilePicture(MultipartFile file, String username) {
        return agentDao.changeProfilePicture(file, username);
    }
}
