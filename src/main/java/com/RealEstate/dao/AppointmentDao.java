package com.RealEstate.dao;

import com.RealEstate.model.Agent;
import com.RealEstate.model.Appointment;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public interface AppointmentDao {
    Appointment readAppointmentById(int id);
    Appointment readAppointmentByReference(String reference);
    void updateAppointment(String query);
    void deleteAppointment(Appointment appointment);
    List<Appointment> getAppList();
    List<Appointment> getAppointmentsListByLocality(Agent agent);
    List<Appointment> getAppointmentsListByCustomer(String customer);
    List<Appointment> getAllAppointmentsListByCustomer(String customer);
    //List<Appointment> createAppointmentList();
    void deleteAppointment(String reference);
    void confirmAppointment(Appointment appointment, String agent);
    //void proposeAppointment(Appointment app);

    Appointment createAppObject(Date date, Time time, String username, String locality);
    boolean checkIfAppointmentExists(Appointment appointment);
    void storeAppointment(Appointment appointment);
}
