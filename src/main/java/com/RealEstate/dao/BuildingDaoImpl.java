package com.RealEstate.dao;

import com.RealEstate.model.Building;
import com.RealEstate.model.Locality;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class BuildingDaoImpl implements BuildingDao {

    @Autowired
    private SessionFactory sessionFactory ;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void storeBuilding(Building building) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(building);
        session.getTransaction().commit();
        session.close();
    }

    public Building readBuildingById(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Building building = session.get(Building.class, id);
        session.getTransaction().commit();
        session.close();
        return building;
    }

    public Building readBuildingByReference(String reference) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Building AS b WHERE b.reference = :reference");
        query.setParameter("reference", reference);
        List list = query.getResultList();
        session.getTransaction().commit();
        session.close();
        if(list.size() > 0)
            return (Building) list.get(0);
        return null;
    }

    public void updateBuilding(String query) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createQuery(query).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public void deleteBuilding(Building bl) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Building building = session.get(Building.class, bl.getId());
        session.delete(building);
        session.getTransaction().commit();
        session.close();
    }

    @SuppressWarnings("unchecked")
    public List<Building> getBuildingsList() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Building");
        List list = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return (List<Building>) list;
    }
}
