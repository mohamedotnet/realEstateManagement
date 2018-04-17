package com.RealEstate.dao;

import com.RealEstate.model.Apartment;
import com.RealEstate.model.Locality;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;


@Repository
public class LocalityDaoImpl implements LocalityDao {
    
    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void storeLocality(Locality l) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(l);
        session.getTransaction().commit();
        session.close();
    }

    public Locality readLocalityById(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Locality locality = session.get(Locality.class, id);
        session.getTransaction().commit();
        session.close();
        return locality;
    }

    public Locality readLocalityByReference(String name) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Locality AS l WHERE l.name = :name");
        query.setParameter("name", name);
        List list = query.getResultList();
        session.getTransaction().commit();
        session.close();
        if(list.size() > 0)
            return (Locality) list.get(0);
        return null;
    }

    public void updateLocality(String  query) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createQuery(query).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public void deleteLocality(Locality l) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Locality locality = session.get(Locality.class, l.getId());
        session.delete(locality);
        session.getTransaction().commit();
        session.close();
    }

    @SuppressWarnings("unchecked")
    public List<Locality> getLocalitiesList() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Locality");
        List list = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return (List<Locality>) list;
    }
}
