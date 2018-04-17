package com.RealEstate.dao;

import com.RealEstate.model.Appointment;

import java.util.List;

public interface AppointmentDao {
    Appointment readAppointmentById(int id);
    Appointment readAppointmentByReference(String reference);
    void updateAppointment(String query);
    void deleteAppointment(Appointment appointment);
    List<Appointment> getAppointmentsList();
    List<Appointment> getAppointmentsListByCustomer(String customer);
    List<Appointment> createAppointmentList();
    void storeAppointment(Appointment appointment);
    void deleteAppointment(String reference);

}
