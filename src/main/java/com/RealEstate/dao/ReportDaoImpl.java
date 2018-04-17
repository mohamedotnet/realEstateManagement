package com.RealEstate.dao;

import com.RealEstate.model.Agent;
import com.RealEstate.model.Report;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class ReportDaoImpl implements ReportDao {

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void storeReport(Report r) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(r);
        Query query = session.createQuery("FROM Agent AS a WHERE a.username = :username");
        query.setParameter("username", r.getAgent());
        int nbr = ((Agent) query.getResultList().get(0)).getNbrAffectation();
        query = session.createQuery("update Agent set nbrAffectation = :nbrAffectation where username = :username");
        query.setParameter("nbrAffectation", ++nbr);
        query.setParameter("username", r.getAgent());
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public Report readReportById(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Report report = session.get(Report.class, id);
        session.getTransaction().commit();
        session.close();
        return report;
    }

    public void updateReport(Report report) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("update Report SET type = :type , date = :date ," +
                "time = :time , content = :content , appointment = :appointment , agent = :agent " +
                "WHERE id = :id");
        query.setParameter("id", report.getId());
        query.setParameter("type", report.getType());
        query.setParameter("date", report.getDate());
        query.setParameter("time", report.getTime());
        query.setParameter("content", report.getContent());
        query.setParameter("appointment", report.getAppointment());
        query.setParameter("agent", report.getAgent());
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public void deleteReport(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("delete from Report where id= :id");
        query.setParameter("id", id);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @SuppressWarnings("unchecked")
    public List<Report> getReportsList() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Report");
        List list = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return (List<Report>) list;
    }
}
