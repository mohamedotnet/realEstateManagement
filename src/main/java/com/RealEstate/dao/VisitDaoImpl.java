package com.RealEstate.dao;

import com.RealEstate.model.Visit;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class VisitDaoImpl implements VisitDao {

    @Autowired
    private SessionFactory sessionFactory ;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void storeVisit(Visit visit) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(visit);
        session.getTransaction().commit();
        session.close();
    }

    public Visit readVisitById(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Visit visit = session.get(Visit.class, id);
        session.getTransaction().commit();
        session.close();
        return visit;
    }

    public void updateVisit(Visit visit) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("update Visit SET date = :date , time = :time ," +
                "agent = :agent , customer = :customer , appointment = :appointment WHERE id = :id");
        query.setParameter("id", visit.getId());
        query.setParameter("date", visit.getDate());
        query.setParameter("time", visit.getTime());
        query.setParameter("customer", visit.getCustomer());
        query.setParameter("agent", visit.getAgent());
        query.setParameter("appointment", visit.getAppointment());
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public void deleteVisit(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("delete from Visit where id= :id");
        query.setParameter("id", id);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @SuppressWarnings("unchecked")
    public List<Visit> getVisitsList() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Visit");
        List list = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return (List<Visit>) list;
    }

    @SuppressWarnings("unchecked")
    public List<Visit> getVisitsListByAgent(String username) {
        System.out.println(username);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Visit where agent = :agent");
        query.setParameter("agent", username);
        List list = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return (List<Visit>) list;
    }


}
