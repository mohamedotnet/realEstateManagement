package com.RealEstate.dao;

import com.RealEstate.model.Agent;
import com.RealEstate.model.Appointment;
import com.RealEstate.model.Visit;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.sql.Date;
import java.sql.Time;
import java.util.List;


@Repository
public class AppointmentDaoImpl implements AppointmentDao {

    @Autowired
    private SessionFactory sessionFactory ;

    @Autowired
    private VisitDao visitDao;
    public AppointmentDaoImpl() {

    }

    public void confirmAppointment(Appointment appointment, String agent){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("UPDATE Appointment SET status = 1 WHERE reference =:ref");
        query.setParameter("ref", appointment.getReference());
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        Visit visit = new Visit(appointment.getReference(), appointment.getDate(),
                                appointment.getTime(), appointment.getCustomer(),
                                agent);
        visitDao.storeVisit(visit);
    }
    public Appointment createAppObject(Date date, Time time, String username, String apartment){
        String reference = "RDV" + date + "/" + time + apartment;
        System.out.println(date);
        System.out.println(time);
        System.out.println(username);
        System.out.println(apartment);
        System.out.println(reference);
        return new Appointment(date, reference, time, username, apartment);
    }
    @SuppressWarnings("unchecked")
    public List<Appointment> getAppointmentsListByLocality(Agent agent){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Appointment WHERE status = false AND appartment " +
                                                  "IN (SELECT reference FROM Apartment WHERE building " +
                                                  "IN (SELECT id FROM Building WHERE locality = :loc))");
        query.setParameter("loc", agent.getLocality());
        List list = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return (List<Appointment>) list;
    }
    /*
        TODO: Check Date Validity
     */
    public boolean checkIfAppointmentExists(Appointment appointment){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Appointment WHERE reference =:ref");
        query.setParameter("ref", appointment.getReference());
        List list = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return list.isEmpty();
    }
    public void storeAppointment(Appointment appointment){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(appointment);
        session.getTransaction().commit();
        session.close();
    }

    /*public void storeAppointment(Appointment app) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Agent where locality = :locality ORDER BY nbrAffectation ASC");
        query.setParameter("locality", app.getLocality());
        List list = query.getResultList();
        String agent = ((Agent) list.get(0)).getUsername();
        //app.setAgent(agent);
        session.save(app);
        Visit v = new Visit(app.getReference(), app.getCustomer(), app.getAgent());
        session.save(v);
        session.getTransaction().commit();
        session.close();
    }*/

    public Appointment readAppointmentById(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Appointment appointment = session.get(Appointment.class, id);
        session.getTransaction().commit();
        session.close();
        return appointment;
    }

    public Appointment readAppointmentByReference(String reference) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Appointment AS a WHERE a.reference = :reference");
        query.setParameter("reference", reference);
        List list = query.getResultList();
        session.getTransaction().commit();
        session.close();
        if(list.size() > 0)
            return (Appointment) list.get(0);
        return null;
    }

    public void updateAppointment(String  query) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createQuery(query).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public void deleteAppointment(Appointment app) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Appointment appointment = session.get(Appointment.class, app.getId());
        session.delete(appointment);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Appointment> getAppList() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Appointment");
        List list = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return (List<Appointment>) list;
    }

    public void deleteAppointment(String reference) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("DELETE FROM Appointment WHERE reference = :reference");
        query.setParameter("reference", reference);
        query.executeUpdate();
        query = session.createQuery("DELETE FROM Visit WHERE appointment = :reference");
        query.setParameter("reference", reference);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
    /*public boolean isDateValid(Date date){
        if (new java.sql.Date(new java.util.Date().getTime())
    }*/

    /*@SuppressWarnings("unchecked")
    public List<Appointment> getAppointmentsList() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Appointment");
        List list = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return (List<Appointment>) list;
    }*/

    @SuppressWarnings("unchecked")
    public List<Appointment> getAppointmentsListByCustomer(String customer) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Appointment WHERE customer = :customer and status = true");
        query.setParameter("customer", customer);
        List list = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return (List<Appointment>) list;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Appointment> getAllAppointmentsListByCustomer(String customer) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Appointment WHERE customer = :customer");
        query.setParameter("customer", customer);
        List list = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return (List<Appointment>) list;
    }

    /*public List<Appointment> createAppointmentList() {
        List<Appointment> list = new ArrayList<Appointment>();
        Date date =  new java.sql.Date(new java.util.Date().getTime());
        int hour = Integer.parseInt(("" + new java.sql.Time(new java.util.Date().getTime())).substring(0,2));
        int start = 0;
        if(hour >= 0 && hour < 8)
            start = 8;
        else if(hour >= 8 && hour <9)
            start = 9;
        else if(hour >= 9 && hour <10)
            start = 10;
        else if(hour >= 10 && hour <11)
            start = 11;
        else if(hour >= 11 && hour <13)
            start = 13;
        else if(hour >= 13 && hour <14)
            start = 14;
        else if(hour >= 14 && hour <15)
            start = 15;
        else
            start = 0;
        for(int i=1; i < 7; i++){
            if(start != 0)
                list.add(new Appointment("RDV"+date+"/"+start+":00:00"+"L"+i, date, java.sql.Time.valueOf(start+":00:00"), null, "locality"+i, null));
        }
        Calendar calendar = Calendar.getInstance();
        for(int j = 0; j < 7; j++){
            calendar.setTime(new Date(calendar.getTime().getTime()  + 86400000));
            for(int i = 1; i < 7; i++){
                list.add(new Appointment("RDV"+date+"/"+"8:00:00"+"L"+i, new Date(calendar.getTime().getTime()), java.sql.Time.valueOf("08:00:00"), null, "locality"+i, null));
                list.add(new Appointment("RDV"+date+"/"+"9:00:00"+"L"+i, new Date(calendar.getTime().getTime()), java.sql.Time.valueOf("09:00:00"), null, "locality"+i, null));
                list.add(new Appointment("RDV"+date+"/"+"10:00:00"+"L"+i, new Date(calendar.getTime().getTime()), java.sql.Time.valueOf("10:00:00"), null, "locality"+i, null));
                list.add(new Appointment("RDV"+date+"/"+"11:00:00"+"L"+i, new Date(calendar.getTime().getTime()), java.sql.Time.valueOf("11:00:00"), null, "locality"+i, null));
                list.add(new Appointment("RDV"+date+"/"+"13:00:00"+"L"+i, new Date(calendar.getTime().getTime()), java.sql.Time.valueOf("13:00:00"), null, "locality"+i, null));
                list.add(new Appointment("RDV"+date+"/"+"14:00:00"+"L"+i, new Date(calendar.getTime().getTime()), java.sql.Time.valueOf("14:00:00"), null, "locality"+i, null));
                list.add(new Appointment("RDV"+date+"/"+"15:00:00"+"L"+i, new Date(calendar.getTime().getTime()), java.sql.Time.valueOf("15:00:00"), null, "locality"+i, null));
            }
        }
        List<Appointment> list2 = new ArrayList<Appointment>();
        for (Appointment p : list) {
            if(!getAppointmentsList().stream().map(Appointment::getReference).collect(Collectors.toList()).contains(p.getReference())){
                list2.add(p);
            }
        }
        return list2;
    }*/
}